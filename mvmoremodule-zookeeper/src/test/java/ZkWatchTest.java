import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2020-07-15-22:33
 */
public class ZkWatchTest {
    public ZooKeeper zk;

    @Before
    public void before() throws IOException {
        zk = new ZooKeeper("127.0.0.1:2181", 30000, watchedEvent -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                System.out.println("======成功连接到zookeeper====");
            }
        });
    }


    /***
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void testCreateWatch() throws KeeperException, InterruptedException {
        zk.exists("/xuzy", watchedEvent -> {
            // create /xuzy "my first" 命令被执行时触发事件
            // 时触发NodeCreated事件
            if(watchedEvent.getType() == Watcher.Event.EventType.NodeCreated){
                System.out.println("节点被创建,创建的节点 ：" + watchedEvent.getPath());
            }
        });
        sleepLong();
    }


    public void testDeleteWatch(){

    }







    public void sleepLong(){
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @After
    public void after() throws InterruptedException {
        zk.close();
        TimeUnit.SECONDS.sleep(5);
    }



}
