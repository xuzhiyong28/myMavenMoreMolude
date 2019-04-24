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
        switch (shardingContext.getShardingItem()){
            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println(shardingContext.getShardingItem());
        }
    }
}
