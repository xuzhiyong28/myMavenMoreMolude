package com.xzy.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CuratorBase {

    public static String CONNECT_ADDR = "localhost:2181";

    public static final int SESSION_OUTTIME = 10000;//ms

    public static void main(String[] args) throws Exception {
        //RetryPolicy，重试连接策略，有四种实现
        //ExponentialBackoffRetry、RetryNTimes、RetryOneTimes、RetryUntilElapsed
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10);
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_OUTTIME)
                .retryPolicy(retryPolicy)
                .build();
        //开启连接
        cf.start();


        //创建节点 有重复节点会报错
        //cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/super/c1","c1内容".getBytes());
        //删除节点，没有会报错
        //cf.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super");

        //读取节点
        //String ret1 = new String(cf.getData().forPath("/super/c1"));
        //System.out.println(ret1);
        //修改节点
        //cf.setData().forPath("/super/c1","内容11".getBytes());
        //System.out.println(new String(cf.getData().forPath("/super/c1")));


        //绑定回掉函数
        /*cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework cf, CuratorEvent ce) throws Exception {
                System.out.println("code:" + ce.getResultCode());
                System.out.println("type:" + ce.getType());
                System.out.println("线程为:" + Thread.currentThread().getName());
            }
        }).forPath("/super/c2", "c2c2".getBytes());
        Thread.sleep(Integer.MAX_VALUE);*/

        //读取子节点
        /*List<String> list = cf.getChildren().forPath("/super");
        for(String p : list){
            System.out.println(p);
        }*/

        //获取节点状态
        Stat stat = cf.checkExists().forPath("/elastic-job-demo");
        System.out.println(stat);


    }
}
