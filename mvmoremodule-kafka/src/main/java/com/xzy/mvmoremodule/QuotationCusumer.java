package com.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-09-12.
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author xuzhiyong
 * @createDate 2018-09-12-21:32
 */
public class QuotationCusumer {
    private static final String TOPIC = "stock-quotation"; //topic name
    private static final String BROKER_LIST = "192.168.199.128:9092,192.168.199.129:9092,192.168.199.130:9092";
    private static KafkaConsumer<String, String> kafkaConsumer;

    static {
        kafkaConsumer = new KafkaConsumer<String, String>(initConfig());
    }


    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST); //kafka集群地址
        properties.put("group.id", "test-reset2");
        properties.put("client.id", "test-reset2");
        properties.put("enable.auto.commit", true); //显示偏移量自动提交
        properties.put("auto.commit.interval.ms", 1000); //设置偏移量提交时间间隔
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("auto.offset.reset","earliest");
        return properties;
    }

    public static void main(String[] agrs) {
        kafkaConsumer.subscribe(Arrays.asList(TOPIC));
        try {
            while (true) {
                //长轮训拉取消息
                ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("partition = %d , offset = %d , key = %s  , value =%s",
                            record.partition() , record.offset() , record.key() , record.value()
                            );
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }
}
