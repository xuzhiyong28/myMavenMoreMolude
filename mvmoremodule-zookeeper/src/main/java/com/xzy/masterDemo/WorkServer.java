package com.xzy.masterDemo;/**
 * Created by Administrator on 2018-10-14.
 */

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-10-14-11:03
 */
public class WorkServer {
    private volatile boolean running = false;
    private ZkClient zkClient;
    //zk主节点路径
    private static final String MASTER_PATH = "/mymaster";
    //监听(用于监听主节点删除事件)
    private IZkDataListener dataListener;
    //服务器基本信息
    private RunningData serverData;
    //主节点基本信息
    private RunningData masterData;
    //调度器
    private ScheduledExecutorService delayExector = Executors.newScheduledThreadPool(1);
    //延迟时间5s
    private int delayTime = 5;

    public WorkServer(RunningData runningData) {
        this.serverData = runningData;
        //设置监听器
        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println(serverData.getName() + "监听到master失效了，准备开始抢master");
                takeMaster();
            }
        };
    }


    public void start() throws Exception {
        if (running) {
            throw new Exception("server has startup....");
        }
        running = true;
        zkClient.subscribeDataChanges(MASTER_PATH, dataListener);
        takeMaster();
    }

    public void stop() throws Exception {
        if (!running) {
            throw new Exception("server has stopped.....");
        }
        running = false;
        delayExector.shutdown();
        zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);
        releaseMaster();
    }

    //模拟抢主节点
    public void takeMaster() throws InterruptedException {
        if (!running) return;
        try {
            zkClient.create(MASTER_PATH, serverData, CreateMode.EPHEMERAL);
            masterData = serverData;
            System.out.println(serverData.getName() + " is master");
            //模拟5秒后释放主节点
            delayExector.schedule(new Runnable() {
                @Override
                public void run() {
                    if (checkMaster()) {
                        releaseMaster();
                    }
                }
            }, 5, TimeUnit.SECONDS);
        } catch (ZkNodeExistsException e) { //节点已经存在
            RunningData runningData = zkClient.readData(MASTER_PATH, true);
            if (runningData == null) {
                takeMaster();
            } else {
                masterData = runningData;
                System.out.println(serverData.getName() + "正在尝试获取mas" +
                        "ter,但是现在存在master:" + masterData.getName());
            }
        }
    }

    //检查自己是不是master
    public boolean checkMaster() {
        try {
            RunningData runningData = zkClient.readData(MASTER_PATH);
            masterData = runningData;
            if (masterData.getName().equals(serverData.getName())) {
                return true;
            } else {
                return false;
            }
        } catch (ZkNoNodeException e) {
            //节点不存在
            return false;
        } catch (ZkInterruptedException e) {
            //网络中断的情况下再次执行
            return checkMaster();
        } catch (Exception e) {
            return false;
        }
    }

    //释放主节点
    public void releaseMaster() {
        if (checkMaster()) {
            zkClient.delete(MASTER_PATH);
        }
    }


    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }
}
