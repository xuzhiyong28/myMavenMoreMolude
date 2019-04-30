package com.xzy.elasticjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * @author xuzhiyong
 * @createDate 2019-04-24-21:33
 */
public class FastDemo {
    private static CoordinatorRegistryCenter createRegistryCenter(){
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("10.8.144.223:2181","elastic-job-demo"));
        registryCenter.init();
        return registryCenter;
    }

    private static LiteJobConfiguration createJobConfiguration() {
        //看清楚 JobCoreConfiguration JobTypeConfiguration LiteJobConfiguration 三者的关系
        //JobCoreConfiguration
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration
                .newBuilder("demoSimpleJob", "0/20 * * * * ?", 4)
                .shardingItemParameters("0=A,1=B,2=C,3=D") //分片以及对应的参数，分片序列号从0开始，大于总片数要报错
                .jobParameter("xuzy")
                .misfire(true) //是否开启错过任务重新执行 默认是true
                .failover(true) //设置失效转移 开启表示如果作业在一次任务执行中途宕机允许将该次未完成的任务在另一作业节点上补偿执行
                .build();
        // 定义SIMPLE类型配置 JobTypeConfiguration
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyElasticJob.class.getCanonicalName());
        // 定义Lite作业根配置 LiteJobConfiguration
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig)
                //.jobShardingStrategyClass(MyJobShardingStrategy.class.getName()) 自定义分片策略
                .build();
        return simpleJobRootConfig;
    }

    public static void initJob(){
        new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
    }


    public static void main(String[] args){
        //new JobScheduler(createRegistryCenter(), createJobConfiguration(),new MyElasticJobListener()).init();
        new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
    }
}

