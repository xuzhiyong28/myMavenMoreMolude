package com.shardingAlgorithm;

import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

import java.util.Collection;

public class ComplexModuloTableShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        return null;
    }
}
