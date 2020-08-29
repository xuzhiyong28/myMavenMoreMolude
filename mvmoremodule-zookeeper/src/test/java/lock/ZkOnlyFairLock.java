package lock;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/***
 * 分布式共享锁
 */
public class ZkOnlyFairLock {
    private static String lockNameSpace = "/mylock02";
    public static String CONNECT_ADDR = "localhost:2181";
    private CuratorFramework cf;
    private String locakPath;
    private String currentLockPath;

    /***
     *
     * @param lockPath 锁路径
     */
    public ZkOnlyFairLock(String lockPath) {
        this.locakPath = lockNameSpace + "/" + lockPath;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000000, 3);
        cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        cf.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                if (connectionState == ConnectionState.LOST) {
                    System.out.println("连接丢失");//连接丢失
                } else if (connectionState == ConnectionState.CONNECTED) {
                    System.out.println("成功连接");
                } else if (connectionState == ConnectionState.RECONNECTED) {
                    System.out.println("重连成功");
                }
            }
        });
        cf.start();
    }

    /***
     * 设置watch+等待锁
     * @param millisToWait
     * @param unit
     * @param path
     * @return
     * @throws Exception
     */
    public boolean waiteLock(long millisToWait, TimeUnit unit, String path) {
        boolean haveTheLock = false;
        boolean doDeleteOurPath = false;
        try {

            while (!haveTheLock) {
                List<String> childNodeList = getChildrenPath();//获取到所有的节点 [lock0000000005, lock0000000006, lock0000000007]
                String pathName = path.substring(lockNameSpace.length() + 1);
                int nodeIndex = childNodeList.indexOf(pathName);
                if (nodeIndex < 0) {
                    //节点不存在，抛出异常
                    throw new RuntimeException("顺序节点不存在");
                }

                if (nodeIndex == 0) {
                    haveTheLock = true; //获得锁
                    System.out.println("path = " + path + " 获得锁");
                } else {
                    //如果不是第一个节点则对上一个节点做监听
                    String preNodePath = lockNameSpace.concat("/").concat(childNodeList.get(nodeIndex - 1));
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    //可能对上一个节点做监听时，这个节点已经被删除了
                    NodeCache cache = new NodeCache(cf, preNodePath);
                    //对上一个节点做监听
                    cache.getListenable().addListener(new NodeCacheListener() {
                        @Override
                        public void nodeChanged() throws Exception {
                            ChildData childData = cache.getCurrentData();
                            if (childData == null) {
                                countDownLatch.countDown();
                            }
                        }
                    });
                    cache.start();
                    //再次判断下上个节点存不存在，如何存在才阻塞，防止出现节点删除后才建立监控导致一直阻塞
                    if (isExistNode(preNodePath)) {
                        System.out.println("====对path=" + preNodePath + " 设置watch监听，并开始阻塞 -" + path);
                        countDownLatch.await();
                    } else {
                        CloseableUtils.closeQuietly(cache);
                    }
                }
            }
        } catch (Exception e) {
            //异常了要删除原来的节点
            doDeleteOurPath = true;
        } finally {
            if (doDeleteOurPath) {
                deleteNode(path);
            }
        }
        return haveTheLock;
    }

    public void lock() throws Exception {
        this.lock(0, null);
    }

    /***
     * 加锁
     */
    public void lock(long millisToWait, TimeUnit unit) throws Exception {
        boolean isDone = false;
        //加锁重试次数
        while (!isDone) {
            String path = createNode(); //创建顺序临时节点 path = /mylock02/lock0000000005
            this.currentLockPath = path; //记录当前的锁节点，后面解锁要用的
            if (StringUtils.isBlank(path)) {
                throw new RuntimeException("创建顺序节点失败");
            }
            if (waiteLock(millisToWait, unit, path)) {
                isDone = true;
            }
        }
    }

    /***
     * 解锁
     */
    public void unlock() {
        deleteNode(this.currentLockPath);
        CloseableUtils.closeQuietly(cf);
    }

    /***
     * 创建顺序临时节点
     * @return
     * @throws Exception
     */
    public String createNode() {
        String s = null;
        try {
            s = cf.create()
                    .creatingParentsIfNeeded() //自动创建父节点
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(locakPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /***
     * 删除节点
     * @param path
     */
    public void deleteNode(String path) {
        try {
            if (isExistNode(path)) {
                cf.delete().forPath(path);
                System.out.println("====" + path + " 删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 判断节点是否存在
     * @param path
     * @return
     */
    public boolean isExistNode(String path) {
        try {
            Stat stat = cf.checkExists().forPath(path);
            return stat == null ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 获取子节点并排序
     * @return
     */
    public List<String> getChildrenPath() {
        try {
            List<String> strings = cf.getChildren().forPath(lockNameSpace);
            return strings.stream().sorted().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Lists.newArrayList();
        }
    }

}
