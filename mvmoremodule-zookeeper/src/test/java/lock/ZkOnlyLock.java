package lock;

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
import org.apache.zookeeper.KeeperException;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/***
 * 使用zk实现分布式独占锁
 */
public class ZkOnlyLock {
    private static String lockNameSpace = "/mylock";
    public static String CONNECT_ADDR = "localhost:2181";
    private static final ThreadLocal<String> threadUuid = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return UUID.randomUUID().toString();
        }
    };
    private CuratorFramework cf;
    private String locakPath;

    /***
     *
     * @param lockPath 锁路径
     */
    public ZkOnlyLock(String lockPath) {
        this.locakPath = lockNameSpace + "/" + lockPath;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
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

    public void lock() {
        this.lock(0, null);
    }

    public void lock(long millisToWait, TimeUnit unit) {
        boolean doDeleteOurPath = false;
        for (;doDeleteOurPath == false;) {
            try {
                String path = cf.create()
                        .creatingParentsIfNeeded() //自动创建父节点
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(locakPath, threadUuid.get().getBytes());
                if (StringUtils.isNoneBlank(path)) {
                    System.out.println("线程" + Thread.currentThread().getId() + "获得锁");
                    break;
                }
            } catch (Exception e) {
                if (e instanceof KeeperException.NodeExistsException) {
                    //如果是已经存在节点了，则设置watch并阻塞
                    NodeCache cache = new NodeCache(cf, locakPath);
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    cache.getListenable().addListener(new NodeCacheListener() {
                        @Override
                        public void nodeChanged() throws Exception {
                            ChildData childData = cache.getCurrentData();
                            if (childData == null) {
                                System.out.println("===节点被删除，可以开始尝试创建===");
                                countDownLatch.countDown();
                            }
                        }
                    });
                    try {
                        cache.start();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    try {
                        if (unit != null) {
                            countDownLatch.await(millisToWait, unit);
                            doDeleteOurPath = true;
                        } else {
                            countDownLatch.await();
                        }
                        CloseableUtils.closeQuietly(cache);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }
    }

    /***
     * 解锁
     */
    public void unlock() {
        try {
            String uuidData = new String(cf.getData().forPath(locakPath), "UTF-8");
            //如果存的是同一个uuid则进行删除，否则不是自己加锁的
            if (StringUtils.isNoneBlank(uuidData) && uuidData.equals(threadUuid.get())) {
                cf.delete().forPath(locakPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadUuid.remove();
        }
    }
}
