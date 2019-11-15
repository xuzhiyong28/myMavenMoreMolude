package com.xzy.balance.client;

import com.google.common.collect.Lists;
import com.xzy.balance.impl.ClientImpl;
import com.xzy.balance.impl.DefaultBalanceProvider;
import com.xzy.balance.model.ServerData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2019-11-13-23:16
 */
public class ClientRunnerMain {
    private static final int  CLIENT_QTY = 10;
    private static final String  ZOOKEEPER_SERVER = "localhost:2181";
    private static final String  SERVERS_PATH = "/servers";

    public static void main(String[] args){
        List<Thread> threadList = Lists.newArrayList();
        final List<Client> clientList = Lists.newArrayList();
        final BalanceProvider<ServerData> balanceProvider = new DefaultBalanceProvider(ZOOKEEPER_SERVER, SERVERS_PATH);
        try{
            for(int i = 0 ; i < CLIENT_QTY ; i++){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Client client = new ClientImpl(balanceProvider);
                        clientList.add(client);
                        try {
                            client.connect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                threadList.add(thread);
                thread.start();
                //延迟启动
                TimeUnit.SECONDS.sleep(2);
            }
            System.out.println("敲回车键退出！\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            for(int i = 0 ; i < clientList.size() ; i++){
                try {
                    clientList.get(i).disConnect();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //关闭线程
            for (int i = 0; i < threadList.size(); i++){
                threadList.get(i).interrupt();
                try{
                    threadList.get(i).join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
