import com.alibaba.fastjson.JSON;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        cf.start();
    }

    @Test
    public void getStat() throws Exception {
        Stat stat = cf.checkExists().forPath("/elastic-job-demo");
        System.out.println(JSON.toJSON(stat));
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
                .forPath("/testPath/xuzy", "init".getBytes());
        //创建的是临时节点的话 30秒后客户端断开后会消失
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
        cf.setData().forPath("/testPath/xuzy","data".getBytes());
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
                .create().withMode(CreateMode.PERSISTENT).forPath("/xuzyPath","data".getBytes())
                .and()
                .setData().forPath("/xuzyPath","data2".getBytes())
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
                .inBackground(new BackgroundCallback(){
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println(String.format("eventType:%s,resultCode:%s",curatorEvent.getType(),curatorEvent.getResultCode()));
                    }
                },executor)
                .forPath("/path");
        //因为是异步，这里需要延迟一下，不然没法看到回调
        TimeUnit.SECONDS.sleep(10);
    }

}
