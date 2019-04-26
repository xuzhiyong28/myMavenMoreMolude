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
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("127.0.0.1:2181","elastic-job-demo"));
        registryCenter.init();
        return registryCenter;
    }

    private static LiteJobConfiguration createJobConfiguration() {
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/15 * * * * ?", 4).shardingItemParameters("0=A,1=B,2=C,3=D").jobParameter("xuzy").build();
        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyElasticJob.class.getCanonicalName());
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
        return simpleJobRootConfig;
    }

    public static void initJob(){
        new JobScheduler(createRegistryCenter(), createJobConfiguration(),new MyElasticJobListener()).init();
    }


    public static void main(String[] args){
        new JobScheduler(createRegistryCenter(), createJobConfiguration(),new MyElasticJobListener()).init();
    }
}

