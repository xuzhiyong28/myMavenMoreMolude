package com.xzy.balance.client;

import com.google.common.collect.Lists;
import com.xzy.balance.impl.DefaultBalanceProvider;
import com.xzy.balance.model.ServerData;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2019-11-13-23:16
 */
public class ClientRunnerMain {
    private static final int  CLIENT_QTY = 3;
    private static final String  ZOOKEEPER_SERVER = "localhost:2181";
    private static final String  SERVERS_PATH = "/servers";

    public static void main(String[] args){
        List<Thread> threadList = Lists.newArrayList();
        final List<Client> clientList = Lists.newArrayList();
        final BalanceProvider<ServerData> balanceProvider = new DefaultBalanceProvider(ZOOKEEPER_SERVER, SERVERS_PATH);
        try{

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
