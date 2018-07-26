package com.xzy;/**
 * Created by Administrator on 2018-07-10.
 */

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2018-07-10-9:30
 */
public class CreateNodeASync implements Watcher{
    private static ZooKeeper zooKeeper;
    public static void main(String agrs[]) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.31.226",5000,new CreateNodeASync());
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
        zooKeeper.create("/node_3", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new IStringCallback(),"创建...");
    }

    static class IStringCallback implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            StringBuilder sb = new StringBuilder();
            sb.append("rc=" + rc).append("\n");
            sb.append("path=" + path).append("\n");
            sb.append("ctx=" + ctx).append("\n");
            sb.append("name=" + name).append("\n");
            System.out.println(sb.toString());
        }
    }
}
