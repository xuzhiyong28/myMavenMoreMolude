package com.xzy.zkClient;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: xuzy
 * @Date: 2018/7/28 21:18
 * @Description:
 */
public class Demo2 {
    private static final String ZK_URL = "127.0.0.1:2181";
    private static final int SESSION_TIMEOUT = 5000;
    private ZooKeeper zooKeeper;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        Demo2 demo2 = new Demo2();
        demo2.connect(ZK_URL);
    }

    /***
     * 链接Zookeeper
     * @param host
     * @return
     */
    public ZooKeeper connect(String host) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(ZK_URL, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected){ //如果客户端已经链接上了
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await(); //主进程阻塞直到返回监听器执行到链接上了客户端
        return zooKeeper;
    }

    /***
     * 关闭客户端
     * @throws InterruptedException
     */
    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}
