package com.xzy.mvmoremodule.partitionerImpl;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/****
 * 自定义的分区策略
 * 当分区键是xuzy时，都将其分配到最后一个分区中
 * 如何使用
 * kafkaProps.put("partitioner.class", "com.xzy.mvmoremodule.partitionerImpl.MyPartitioner");
 */
public class MyPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //根据主题名称获取到分区
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numPartition = partitionInfos.size();
        if( (key == null) || !(key instanceof String)){
            throw new InvalidRecordException("key is error");
        }
        if("xuzy".equals(key)){
            return numPartition;
        }
        return (Math.abs(Utils.murmur2(keyBytes))) % (numPartition - 1);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
