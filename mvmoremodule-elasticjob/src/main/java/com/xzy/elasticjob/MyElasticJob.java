package com.xzy.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuzhiyong
 * @createDate 2019-04-24-21:33
 */
public class MyElasticJob implements SimpleJob {
    public final static Logger logger = LoggerFactory.getLogger(MyElasticJob.class);
    @Override
    public void execute(ShardingContext shardingContext) {
        int shardingTotalCount = shardingContext.getShardingTotalCount();
        String shardingParameter = shardingContext.getShardingParameter();
        int shardingItem = shardingContext.getShardingItem();
        String jobParamter = shardingContext.getJobParameter();
        logger.info("shardingItem =" + shardingItem + " , shardingTotalCount=" + shardingTotalCount + " , shardingParameter =" + shardingParameter + " ,jobParamter =" + jobParamter);
        System.out.println("shardingItem =" + shardingItem + " , shardingTotalCount=" + shardingTotalCount + " , shardingParameter =" + shardingParameter + " ,jobParamter =" + jobParamter);

    }
}
