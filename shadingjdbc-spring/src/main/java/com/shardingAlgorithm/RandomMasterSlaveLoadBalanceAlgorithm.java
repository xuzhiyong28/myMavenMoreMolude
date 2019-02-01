package com.shardingAlgorithm;

import io.shardingsphere.core.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithm;

import java.util.List;

public class RandomMasterSlaveLoadBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {

    /***
     *
     * @param name
     * @param masterDataSourceName 主库
     * @param slaveDataSourceNames 从库列表
     * @return
     */
    @Override
    public String getDataSource(String name, String masterDataSourceName, List<String> slaveDataSourceNames) {
        System.out.println("主库 = " + masterDataSourceName);
        System.out.println("从库 = " + slaveDataSourceNames);
        return slaveDataSourceNames.get(0);
    }
}
