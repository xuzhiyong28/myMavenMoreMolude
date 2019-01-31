package com.shardingAlgorithm;

import com.google.common.collect.Lists;
import com.util.AlgorithmUtil;
import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

/***
 * 库复合分片策略
 * 分库策略：还是按照日期，dataType先不用
 */
public class ComplexModuloDatabaseShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        List<String> databaseList = Lists.newArrayList();
        List<String> flowTimes = AlgorithmUtil.getShardingValue(shardingValues,"flowtime");
        List<String> dataTypes = AlgorithmUtil.getShardingValue(shardingValues,"dataType");
        if(flowTimes == null || flowTimes.size() == 0){
            throw new UnsupportedOperationException("ShardingValue is null");
        }
        for(String timeValue : flowTimes){
            String value = StringUtils.substring(timeValue,0,4); //获取到年份
            for(String availableTargetName : availableTargetNames){
                if(availableTargetName.endsWith(value)){
                    databaseList.add(availableTargetName);
                }
            }
        }
        return databaseList;
    }
}
