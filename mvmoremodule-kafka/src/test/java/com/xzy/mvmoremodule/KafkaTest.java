package com.xzy.mvmoremodule;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;

public class KafkaTest {
    /***
     * 单线程消费 自动提交偏移量
     */
    @Test
    public void singleCusumerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.157.128:9092");
        props.put("group.id", "singleCusumerTest" + System.currentTimeMillis());
        //自动提交
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("max.poll.records", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList("beatlog"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

    /***
     * 手动提交
     */
    @Test
    public void singleCusumerTest2() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.157.128:9092");
        props.put("group.id", "singleCusumerTest" + System.currentTimeMillis());
        //关闭自动提交
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("max.poll.records", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList("beatlog"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, timestamp = %d , value = %s%n", record.offset(), record.key(), record.timestamp(), record.value());
            }
            //同步提交，当前线程会阻塞直到 offset 提交成功
            consumer.commitSync();
        }
    }

    /***
     * 异步提交
     * 无论是同步提交还是异步提交 offset，都有可能会造成数据的漏消费或者重复消费。先
     * 提交 offset 后消费，有可能造成数据的漏消费；而先消费后提交 offset，有可能会造成数据
     * 的重复消费
     */
    @Test
    public void singleCusumerTest3() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.157.128:9092");
        props.put("group.id", "singleCusumerTest" + System.currentTimeMillis());
        //关闭自动提交
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("max.poll.records", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList("beatlog"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, timestamp = %d , value = %s%n", record.offset(), record.key(), record.timestamp(), record.value());
            }
            //异步提交
            consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                    if (e != null) {
                        System.out.println("map = " + map);
                    }
                }
            });
        }
    }
}
