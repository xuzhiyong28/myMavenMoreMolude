package com.xzy.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2019-04-24-21:33
 * Elastic-Job会为每一个分片去启动一个线程来执行分片任务
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
        //System.out.println("shardingItem =" + shardingItem + " , shardingTotalCount=" + shardingTotalCount + " , shardingParameter =" + shardingParameter + " ,jobParamter =" + jobParamter);
        switch (shardingItem){
            case  0:
                doLongJob("食品订单");
                break;
            case 1:
                doSmallJob("电脑订单");
                break;
            case 2:
                doLongJob("服装订单");
                break;
            case 3:
                doSmallJob("机械订单");
                break;
        }
    }

    public void doLongJob(String msg){
        logger.info("========长时间任务=======" + msg);
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doSmallJob(String msg){
        logger.info("========短时间任务=======" + msg);
    }
}
