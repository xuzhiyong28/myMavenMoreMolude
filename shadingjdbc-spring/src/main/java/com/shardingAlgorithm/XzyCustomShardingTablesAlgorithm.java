package com.shardingAlgorithm;/**
 * Created by Administrator on 2019-01-28.
 */

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * @author xuzhiyong
 * @createDate 2019-01-28-21:53
 * 表分片策略
 */
public class XzyCustomShardingTablesAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        if(StringUtils.isBlank(preciseShardingValue.getValue())){
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //根据月份路由
        for (String each : collection) {
            String value = StringUtils.substring(preciseShardingValue.getValue(),0,6);
            if(each.endsWith(value)){
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
