package com.shardingAlgorithm;

import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

import java.util.Collection;

/***
 * 表复合分片
 */
public class ComplexModuloTableShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        System.out.println(availableTargetNames);
        System.out.println(shardingValues);
        return null;
    }


}
