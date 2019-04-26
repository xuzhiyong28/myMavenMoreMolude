package com.xzy.elasticjob;

import com.dangdang.ddframe.job.lite.api.strategy.JobInstance;
import com.dangdang.ddframe.job.lite.api.strategy.JobShardingStrategy;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/***
 * 自定义分片策略
 */
public class MyJobShardingStrategy implements JobShardingStrategy {

    /**
     * 作业分片.
     *
     * @param jobInstances 所有参与分片的单元列表
     * @param jobName 作业名称
     * @param shardingTotalCount 分片总数
     * @return 分片结果
     */
    @Override
    public Map<JobInstance, List<Integer>> sharding(List<JobInstance> jobInstances, String jobName, int shardingTotalCount) {
        Map<JobInstance, List<Integer>> shardingMap = Maps.newHashMap();
        if(jobInstances.size() == 1){
            shardingMap.put(jobInstances.get(0),Lists.newArrayList(0,1,2,3));
            return shardingMap;
        }else{
            for(JobInstance jobInstance : jobInstances){
                String ip = jobInstance.getIp();
                if(ip.equals("192.168.135.131")){
                    shardingMap.put(jobInstance,Lists.<Integer>newArrayList(0,1));
                }
                if(ip.equals("192.168.135.133")){
                    shardingMap.put(jobInstance,Lists.<Integer>newArrayList(2,3));
                }
            }
        }
        return shardingMap;
    }
}
