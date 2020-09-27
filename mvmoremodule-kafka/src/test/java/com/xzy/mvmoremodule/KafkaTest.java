package com.xzy.mvmoremodule;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.sql.Time;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaTest {


    @Test
    public void sendMessage() throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //kafka集群地址
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key的序列化类
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value的序列化类
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        int i = 0;
        while (true) {
            ProducerRecord<String, String> record = new ProducerRecord<>("httplog", "{,\"id\" : \"" + i + "\" ,\"message\" : \"消息_" + System.currentTimeMillis() + "\" }\n");
            kafkaProducer.send(record);
            i++;
            TimeUnit.MICROSECONDS.sleep(200);
            break;
        }
        kafkaProducer.close();
    }


    /***
     * 单线程消费 自动提交偏移量
     */
    @Test
    public void singleCusumerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.199.128:9092,192.168.199.129:9092,192.168.199.130:9092");
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
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "singleCusumerTest");
        //关闭自动提交
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("max.poll.records", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //consumer.subscribe(Lists.newArrayList("beatlog"));
        //指定分区消费并从分区0的开头处开始消费数据
        consumer.assign(Lists.newArrayList(new TopicPartition("httplog", 0)));
        consumer.seekToBeginning(Lists.newArrayList(new TopicPartition("httplog", 0)));
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
        //consumer.subscribe(Lists.newArrayList("beatlog"));
        //重平衡监听器
        //当有新的消费者加入消费者组、已有的消费者推出消费者组或者所订阅的主题的分区发生变化，就会触发到分区的重新分配，重新分配的过程叫做 Rebalance
        //消费者发生 Rebalance 之后，每个消费者消费的分区就会发生变化。因此消费者要首先获取到自己被重新分配到的分区，并且定位到每个分区最近提交的 offset 位置继续消费
        consumer.subscribe(Lists.newArrayList("beatlog"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                //此方法会在消费者停止消费消费后，在重平衡开始前调用
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //此方法在分区分配给消费者后，在消费者开始读取消息前调用
            }
        });
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

    /***
     * 提交特定位移
     * commitSync()和commitAsync()会提交上一次poll()的最大位移，
     * 但如果poll()返回了批量消息，而且消息数量非常多，我们可能会希望在处理这些批量消息过程中提交位移，
     * 以免重平衡导致从头开始消费和处理。幸运的是，commitSync()和commitAsync()允许我们指定特定的位移参数，参数为一个分区与位移的map
     * 有问题的程序
     */
    @Test
    public void singleCusumerTest4() {
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
        int count = 0;
        consumer.subscribe(Lists.newArrayList("beatlog"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            Map<TopicPartition, OffsetAndMetadata> currentOffsets = Maps.newHashMap();
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, timestamp = %d , value = %s%n", record.offset(), record.key(), record.timestamp(), record.value());
                currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                //当处理200条数据的时候就做一下提交偏移量操作
                if (count % 200 == 0) {
                    consumer.commitAsync(currentOffsets, null);
                }
            }
            currentOffsets = null;
        }
    }

}
