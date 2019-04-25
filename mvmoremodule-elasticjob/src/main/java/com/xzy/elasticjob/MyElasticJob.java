package com.xzy.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author xuzhiyong
 * @createDate 2019-04-24-21:33
 */
public class MyElasticJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        int shardingTotalCount = shardingContext.getShardingTotalCount();
        String shardingParameter = shardingContext.getShardingParameter();
        int shardingItem = shardingContext.getShardingItem();
        String jobParamter = shardingContext.getJobParameter();
        System.out.println("shardingItem =" + shardingItem + " , shardingTotalCount=" + shardingTotalCount + " , shardingParameter =" + shardingParameter + " ,jobParamter =" + jobParamter);
    }
}
