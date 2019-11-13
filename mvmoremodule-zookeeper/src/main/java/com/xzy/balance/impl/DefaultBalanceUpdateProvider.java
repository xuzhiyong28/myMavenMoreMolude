package com.xzy.balance.impl;

import com.xzy.balance.server.BalanceUpdateProvider;
import com.xzy.balance.model.ServerData;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

public class DefaultBalanceUpdateProvider implements BalanceUpdateProvider {

    /***
     * 需要修改的服务器在zk对应的节点
     */
    private String serverPath;

    private CuratorFramework cf;

    public DefaultBalanceUpdateProvider(String serverPath, CuratorFramework cf) {
        this.serverPath = serverPath;
        this.cf = cf;
    }

    //增加负载
    @Override
    public boolean addBalance(Integer step) {
        while (true) {
            try {
                Stat stat = new Stat();
                ServerData serverData = SerializationUtils.deserialize(cf.getData().storingStatIn(stat).forPath(serverPath));
                if (serverData == null || stat == null) {
                    System.out.println("查询服务器数据为空，path : " + serverPath);
                    return false;
                }
                serverData.setBalance(serverData.getBalance() + step);
                cf.setData().withVersion(stat.getVersion()).forPath(serverPath, SerializationUtils.serialize(serverData));
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    //减少负载
    @Override
    public boolean reduceBalance(Integer step) {
        while (true) {
            try {
                Stat stat = new Stat();
                ServerData serverData = SerializationUtils.deserialize(cf.getData().storingStatIn(stat).forPath(serverPath));
                if (serverData == null || stat == null) {
                    System.out.println("查询服务器数据为空，path : " + serverPath);
                    return false;
                }
                final Integer currBalance = serverData.getBalance();
                serverData.setBalance(currBalance > step ? currBalance - step : 0);
                cf.setData().withVersion(stat.getVersion()).forPath(serverPath, SerializationUtils.serialize(serverData));
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
