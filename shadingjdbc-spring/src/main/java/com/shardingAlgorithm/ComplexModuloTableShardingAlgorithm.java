package com.shardingAlgorithm;

import com.google.common.collect.Lists;
import com.util.AlgorithmUtil;
import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

/***
 * 表复合分片
 */
public class ComplexModuloTableShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        List<String> databaseList = Lists.newArrayList();
        List<String> flowTimes = AlgorithmUtil.getShardingValue(shardingValues,"flowtime");
        List<String> dataTypes = AlgorithmUtil.getShardingValue(shardingValues,"dataType");
        if(flowTimes == null || flowTimes.size() == 0){
            throw new UnsupportedOperationException("ShardingValue is null");
        }
        for(String timeValue : flowTimes){
            String value = StringUtils.substring(timeValue,4,6); //获取到月份
            for(String availableTargetName : availableTargetNames){
                if(availableTargetName.endsWith(value)){
                    databaseList.add(availableTargetName);
                }
            }
        }
        return databaseList;
    }


}
