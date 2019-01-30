package com.shardingAlgorithm;

import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

import java.util.Collection;

/***
 * 库复合分片策略
 */
public class ComplexModuloDatabaseShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        System.out.println(availableTargetNames);
        System.out.println(shardingValues);
        return null;
    }
}
