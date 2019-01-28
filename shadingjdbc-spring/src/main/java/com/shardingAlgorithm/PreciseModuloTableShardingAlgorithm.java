package com.shardingAlgorithm;/**
 * Created by Administrator on 2019-01-28.
 */

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * @author xuzhiyong
 * @createDate 2019-01-28-22:30
 * 按表
 */
public class PreciseModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        //对于库的分片collection存放的是所有的库的列表，这里代表flow_01~flow_12
        //配置的分片的sharding-column对应的值
        String timeValue = preciseShardingValue.getValue();
        //分库时配置的sharding-column
        String time = preciseShardingValue.getColumnName();
        //需要分库的逻辑表
        String table = preciseShardingValue.getLogicTableName();
        if(StringUtils.isBlank(timeValue)){
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //按月路由
        for (String each : collection) {
            String value = StringUtils.substring(timeValue,4,6); //获取到月份
            if(each.endsWith(value)){
                return each;
            }
        }
        return null;
    }
}
