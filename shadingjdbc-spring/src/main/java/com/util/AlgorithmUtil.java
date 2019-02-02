package com.util;

import io.shardingsphere.core.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/***
 * 获取分片的值
 */
public class AlgorithmUtil {
    public static List<String> getShardingValue(Collection<ShardingValue> shardingValues, final String key){
        List<String> valueSet = new ArrayList<>();
        Iterator<ShardingValue> iterator = shardingValues.iterator();
        while(iterator.hasNext()){
            ShardingValue next = iterator.next();
            if(next instanceof ListShardingValue){
                ListShardingValue value = (ListShardingValue) next;
                if(value.getColumnName().equals(key)){
                    for(Object shard : value.getValues()){
                        valueSet.add(shard.toString());
                    }
                    return valueSet;
                }
            }
        }
        return null;
    }
}
