package com.xzy.balance.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.xzy.balance.model.ServerData;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.kafka.common.utils.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2019-11-13-22:53
 */
public class DefaultBalanceProvider extends AbstractBalanceProvider<ServerData> {

    private final String zkServer; // zookeeper服务器地址
    private final String serversPath; // servers节点路径
    private CuratorFramework curatorFramework;
    private static final Integer SESSION_TIME_OUT = 10000;
    private static final Integer CONNECT_TIME_OUT = 10000;

    public DefaultBalanceProvider(String zkServer, String serversPath) {
        this.serversPath = serversPath;
        this.zkServer = zkServer;
        this.curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(this.zkServer)
                .sessionTimeoutMs(SESSION_TIME_OUT)
                .connectionTimeoutMs(CONNECT_TIME_OUT)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        this.curatorFramework.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                if (connectionState == ConnectionState.LOST) {
                    System.out.println("连接丢失");//连接丢失
                } else if (connectionState == ConnectionState.CONNECTED) {
                    System.out.println("成功连接");
                } else if (connectionState == ConnectionState.RECONNECTED) {
                    System.out.println("重连成功");
                }
            }
        });
        this.curatorFramework.start();
    }

    /***
     *
     * @param items
     * @return
     */
    @Override
    protected ServerData balanceAlgorithm(List<ServerData> items) {
        if(items.size() > 0){
            //对服务器进行排序，并返回负载最小的一个
            Collections.sort(items);
            return items.get(0);
        }
        return null;
    }

    /***
     * 获取全部服务器的信息
     * @return
     */
    @Override
    protected List<ServerData> getBalanceItems() {
        List<ServerData> serverDataList = Lists.newArrayList();
        try {
            List<String> childrenList = curatorFramework.getChildren().forPath(this.serversPath);
            System.out.println("childrenList : " + JSON.toJSONString(childrenList));
            for(String children : childrenList){
                ServerData serverData = SerializationUtils.deserialize(curatorFramework.getData().forPath(this.serversPath + "/" + children));
                System.out.println("serverData:" + serverData.toString());
                serverDataList.add(serverData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverDataList;
    }
}
