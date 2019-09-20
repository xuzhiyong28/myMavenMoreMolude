import com.alibaba.fastjson.JSON;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CuratorTest {

    public CuratorFramework cf = null;
    public static String CONNECT_ADDR = "localhost:2181";
    public static final int SESSION_OUTTIME = 10000;//ms

    @Before
    public void before() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        //监控客户端的状态
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


    public void close(){
        //关闭cf客户端
        CloseableUtils.closeQuietly(cf);
    }


    @Test
    public void getStat() throws Exception {
        Stat stat = cf.checkExists().forPath("/elastic-job-demo");
        System.out.println(JSON.toJSON(stat));
        cf.close();
        TimeUnit.MICROSECONDS.sleep(1000);
    }

    @Test
    public void createPath() throws Exception {
        /***
         PERSISTENT：持久化
         PERSISTENT_SEQUENTIAL：持久化并且带序列号
         EPHEMERAL：临时
         EPHEMERAL_SEQUENTIAL：临时并且带序列号
         */
        cf.create()
                .creatingParentsIfNeeded() //自动创建父节点
                .withMode(CreateMode.PERSISTENT)
                //.withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath("/testPath/xuzy", "init".getBytes());
        //创建的是临时节点的话 30秒后客户端断开后会消失
        TimeUnit.SECONDS.sleep(30);
    }

    @Test
    public void createESPath() throws Exception {
        cf.create()
                .creatingParentsIfNeeded() //自动创建父节点
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath("/testPath/xuzy", "init".getBytes());
        TimeUnit.SECONDS.sleep(30);
    }


    @Test
    public void deletePath() throws Exception {
        //只能删除叶子节点
        //cf.delete().forPath("/testPath");
        cf.delete()
                .guaranteed() //guaranteed()接口是一个保障措施，只要客户端会话有效，那么Curator会在后台持续进行删除操作，直到删除节点成功
                .deletingChildrenIfNeeded() //删除一个节点时会递归删除所有子节点
                .forPath("/testPath");
    }

    @Test
    public void getData() throws Exception {
        byte[] bytes = cf.getData().forPath("/testPath/xuzy");
        System.out.println(new String(bytes));

        //获取节点的值并获取对应的stat
        Stat stat = new Stat();
        byte[] bytes1 = cf.getData().storingStatIn(stat).forPath("/testPath/xuzy");
        System.out.println(JSON.toJSON(stat));
    }

    @Test
    public void setData() throws Exception {
        //设置节点
        cf.setData().forPath("/testPath/xuzy", "data".getBytes());
    }

    @Test
    public void exists() throws Exception {
        Stat stat = cf.checkExists().forPath("/testPath/xuzy");
        Assert.assertNotNull(stat);

        Stat stat1 = cf.checkExists().forPath("/testPath/gaoys");
        Assert.assertNull(stat1);
    }

    @Test
    public void inTransaction() throws Exception {
        //CuratorFramework的实例包含inTransaction( )接口方法，调用此方法开启一个ZooKeeper事务. 可以复合create, setData, check, and/or delete 等操作然后调用commit()作为一个原子操作提交
        cf.inTransaction().check().forPath("/xuzyPath")
                .and()
                .create().withMode(CreateMode.PERSISTENT).forPath("/xuzyPath", "data".getBytes())
                .and()
                .setData().forPath("/xuzyPath", "data2".getBytes())
                .and()
                .commit();
    }

    @Test
    public void asyncZk() throws Exception {
        //上面提到的创建、删除、更新、读取等方法都是同步的，Curator提供异步接口，引入了BackgroundCallback接口用于处理异步接口调用之后服务端返回的结果信息。
        // BackgroundCallback接口中一个重要的回调值为CuratorEvent，里面包含事件类型、响应吗和节点的详细信息
        Executor executor = Executors.newFixedThreadPool(2);
        cf.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println(String.format("eventType:%s,resultCode:%s", curatorEvent.getType(), curatorEvent.getResultCode()));
                    }
                }, executor)
                .forPath("/path");
        //因为是异步，这里需要延迟一下，不然没法看到回调
        TimeUnit.SECONDS.sleep(10);
    }


    /***
     * 监控路径的变化
     * Path Cache用来监控一个ZNode的子节点. 当一个子节点增加， 更新，删除时， Path Cache会改变它的状态， 会包含最新的子节点，
     * 子节点的数据和状态，而状态的更变将通过PathChildrenCacheListener通知。
     * @throws Exception
     */
    @Test
    public void PathCache() throws Exception {
        final String PATH = "/example/pathCache";
        PathChildrenCache cache = new PathChildrenCache(cf, PATH, true);
        cache.start();
        PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                System.out.println("事件类型：" + event.getType());
                if (null != event.getData()) {
                    System.out.println("节点数据：" + event.getData().getPath() + " = " + new String(event.getData().getData()));
                }
            }
        };
        cache.getListenable().addListener(childrenCacheListener);
        cf.create().creatingParentsIfNeeded().forPath("/example/pathCache/test01", "01".getBytes());
        TimeUnit.MICROSECONDS.sleep(100);
        cf.create().creatingParentsIfNeeded().forPath("/example/pathCache/test02", "02".getBytes());
        TimeUnit.MICROSECONDS.sleep(100);
        cf.setData().forPath("/example/pathCache/test01", "01_V2".getBytes());
        TimeUnit.MICROSECONDS.sleep(100);
        for (ChildData data : cache.getCurrentData()) {
            System.out.println("getCurrentData:" + data.getPath() + " = " + new String(data.getData()));
        }
        cf.delete().forPath("/example/pathCache/test01");
        TimeUnit.MICROSECONDS.sleep(100);
        cf.delete().forPath("/example/pathCache/test02");
        TimeUnit.MICROSECONDS.sleep(1000 * 5);
        cache.close();
        cf.close();
        System.out.println("OK!");
    }


    /***
     * 监控节点数据的变化
     * Node Cache与Path Cache类似，Node Cache只是监听某一个特定的节点
     * @throws Exception
     */
    @Test
    public void PathCache2() throws Exception {
        final String PATH = "/example/cache";
        cf.create().creatingParentsIfNeeded().forPath(PATH);
        final NodeCache cache = new NodeCache(cf, PATH);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData data = cache.getCurrentData();
                if (null != data) {
                    System.out.println("节点数据：" + new String(cache.getCurrentData().getData()));
                } else {
                    System.out.println("节点被删除!");
                }
            }
        };
        cache.getListenable().addListener(nodeCacheListener);
        cache.start();
        cf.setData().forPath(PATH, "01".getBytes());
        TimeUnit.MICROSECONDS.sleep(100);
        cf.setData().forPath(PATH, "02".getBytes());
        TimeUnit.MICROSECONDS.sleep(100);
        cf.delete().deletingChildrenIfNeeded().forPath(PATH);
        TimeUnit.MICROSECONDS.sleep(1000 * 2);
        cache.close();
        cf.close();
        System.out.println("OK!");
    }

    /***
     * Tree Cache可以监控整个树上的所有节点，类似于PathCache和NodeCache的组合
     * @throws Exception
     */
    @Test
    public void PathCache3() throws Exception {
        final String PATH = "/example/cache";
        //先创建节点
        Stat stat = cf.checkExists().forPath(PATH);
        if(stat == null){
            cf.create().creatingParentsIfNeeded().forPath(PATH);
        }
        TreeCache cache = new TreeCache(cf, "/example");
        //设置监听
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {
                System.out.println("事件类型：" + event.getType() +
                        " | 路径：" + (null != event.getData() ? event.getData().getPath() : null));
            }
        };
        cache.getListenable().addListener(treeCacheListener);
        cache.start();
        cf.setData().forPath(PATH, "01".getBytes());
        TimeUnit.SECONDS.sleep(5);
        cf.setData().forPath(PATH, "02".getBytes());
        TimeUnit.SECONDS.sleep(5);
        cf.delete().deletingChildrenIfNeeded().forPath(PATH);
        TimeUnit.SECONDS.sleep(5);
        cf.create().creatingParentsIfNeeded().forPath("/example/cache2");
        TimeUnit.SECONDS.sleep(5);
        cf.setData().forPath("/example/cache2", "02".getBytes());
        TimeUnit.SECONDS.sleep(5);
        ChildData cache2 =  cache.getCurrentData("/example/cache2");
        cache.close();
        cf.close();
        System.out.println("OK!");
    }

    @Test
    public void initConfig() throws Exception {
        cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/configuration/jdbc_driver", "com.mysql.jdbc.Driver".getBytes());
        cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/configuration/jdbc_url", "jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8".getBytes());
        cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/configuration/jdbc_username", "root".getBytes());
        cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/configuration/jdbc_password", "123456".getBytes());
    }
}
