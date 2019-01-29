package com.shardingAlgorithm;/**
 * Created by Administrator on 2019-01-28.
 */

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;

/**
 * @author xuzhiyong
 * @createDate 2019-01-28-21:54
 * RangeShardingAlgorithm
 */
public class XzyCustomShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<Integer>, RangeShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        if(preciseShardingValue.getValue() == null){
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //根据order_id的奇偶来路由不同的数据库
        for (String each : collection) {
            if(each.endsWith(isOdd(Long.valueOf(preciseShardingValue.getValue())))){
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }


    /***
     * 判断是奇数还是偶数
     * @param value
     * @return 1|奇数 0偶数
     */
    public String isOdd(long value) {
        if ((value & 1) != 1) {
            return "0";
        } else {
            return "1";
        }
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        //todo
        return null;
    }
}
