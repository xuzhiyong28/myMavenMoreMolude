package com.shardingAlgorithm;

import io.shardingsphere.core.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithm;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomMasterSlaveLoadBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {

    private static int MAX_INDEX = 100000;

    private static AtomicInteger index = new AtomicInteger(0);
    /***
     * 采用用计数器%2来轮询两台服务器上的路由
     * @param name
     * @param masterDataSourceName 主库
     * @param slaveDataSourceNames 从库列表
     * @return
     */
    @Override
    public String getDataSource(String name, String masterDataSourceName, List<String> slaveDataSourceNames) {
        if(index.get() > MAX_INDEX){
            synchronized (RandomMasterSlaveLoadBalanceAlgorithm.class){
                if(index.get() > MAX_INDEX){
                    index = new AtomicInteger(0);
                }
            }
        }
        return slaveDataSourceNames.get(index.incrementAndGet() % 2);
    }
}
