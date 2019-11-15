package com.xzy.balance.server;

import com.google.common.collect.Lists;
import com.xzy.balance.impl.ServerImpl;
import com.xzy.balance.model.ServerData;

import java.util.List;

public class ServerRunnerMain {
    private static final int  SERVER_QTY = 5;
    private static final String  ZOOKEEPER_SERVER = "localhost:2181";
    private static final String  SERVERS_PATH = "/servers";

    public static void main(String[] args){
        List<Thread> threadList = Lists.newArrayList();
        for(int i = 0; i < SERVER_QTY; i++){
            final Integer count = i;
            Thread thread = new Thread(() -> {
                ServerData serverData = new ServerData();
                serverData.setBalance(0);
                serverData.setHost("127.0.0.1");
                serverData.setPort(6000 + count);
                Server server = new ServerImpl(ZOOKEEPER_SERVER , SERVERS_PATH , serverData);
                server.bind();
            });
            threadList.add(thread);
            thread.start();
        }
    }
}
