package com.xzy.masterDemo;/**
 * Created by Administrator on 2018-10-14.
 */

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-10-14-12:58
 * 模拟一个master选举的zookeeper过程
 * 过程:在zookeeper节点上注册一个瞬时的节点，第一个节点注册成功后其他节点包括注册成功的节点都注册一个节点删除监听器，当节点意外
 * 删除后又开始继续选举
 */
public class LeaderSelectorZkClient {
    //启动的服务个数
    private static final int  CLIENT_QTY = 10;
    //zookeeper服务器的地址
    private static final String  ZOOKEEPER_SERVER = "192.168.135.131:2181";


    public static void main(String[] args) throws Exception{
        LeaderSelectorZkClient leaderSelectorZkClient = new LeaderSelectorZkClient();
        //leaderSelectorZkClient.test1();
        leaderSelectorZkClient.test2();
    }

    public void test2() throws Exception {
        ZkClient client = new ZkClient(ZOOKEEPER_SERVER,10000,10000,new SerializableSerializer());
        RunningData runningData = new RunningData();
        runningData.setCid(1);
        runningData.setName("Client #1");
        WorkServer workServer = new WorkServer(runningData);
        workServer.setZkClient(client);
        workServer.start();
    }


    public void test1() throws Exception {
        //保存所有zkClient的列表
        List<ZkClient> clients = new ArrayList<ZkClient>();
        //保存所有服务的列表
        List<WorkServer> workServers = new ArrayList<WorkServer>();

        try{
            for ( int i = 0; i < CLIENT_QTY; ++i ){
                //创建zkClient
                ZkClient client = new ZkClient(ZOOKEEPER_SERVER, 5000, 5000, new SerializableSerializer());
                clients.add(client);
                //创建serverData
                RunningData runningData = new RunningData();
                runningData.setCid(Long.valueOf(i));
                runningData.setName("Client #" + i);
                //创建服务
                WorkServer  workServer = new WorkServer(runningData);
                workServer.setZkClient(client);
                workServers.add(workServer);
                workServer.start();
            }

            System.out.println("敲回车键退出！\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }finally{
            System.out.println("Shutting down...");

            for ( WorkServer workServer : workServers ){
                try {
                    workServer.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for ( ZkClient client : clients ){
                try {
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
