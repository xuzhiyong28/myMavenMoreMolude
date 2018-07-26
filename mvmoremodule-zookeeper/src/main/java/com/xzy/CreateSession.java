package com.xzy;/**
 * Created by Administrator on 2018-07-10.
 */

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2018-07-10-9:30
 */
public class CreateSession implements Watcher{
    private static ZooKeeper zooKeeper;
    public static void main(String agrs[]) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.31.226",5000,new CreateSession());
        System.out.println(zooKeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("收到事件:" + event);
        if(event.getState() == Event.KeeperState.SyncConnected){
            doSomeThing();
        }
    }

    private void doSomeThing(){

    }
}
