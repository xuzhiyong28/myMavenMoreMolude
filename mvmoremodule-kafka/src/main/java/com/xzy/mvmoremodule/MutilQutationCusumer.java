package com.xzy.mvmoremodule;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * KafkaConsumer 是非线程安全的，所以不能直接复用
 */
public class MutilQutationCusumer {

    public static void main(String[] args){
        final List<KafkaCusumerThread> consumers = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i = 0 ; i < 3; i++){
            KafkaCusumerThread kafkaCusumerThread = new KafkaCusumerThread();
            consumers.add(kafkaCusumerThread);
            executor.submit(kafkaCusumerThread);
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for(KafkaCusumerThread kafkaCusumerThread : consumers){
                    kafkaCusumerThread.shutdown();
                }
            }
        });
    }



    /****
     * 消费者线程
     */
    static class KafkaCusumerThread implements Runnable {
        private static final String TOPIC = "stock-quotation"; //topic name
        private static final String BROKER_LIST = "192.168.199.128:9092,192.168.199.129:9092,192.168.199.130:9092";
        private static KafkaConsumer<String, String> kafkaConsumer;

        public KafkaCusumerThread() {
            Properties properties = new Properties();
            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST); //kafka集群地址
            properties.put("group.id", "kafka-log4j-1");
            properties.put("enable.auto.commit", true); //显示偏移量自动提交
            properties.put("auto.commit.interval.ms", 1000); //设置偏移量提交时间间隔
            properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            properties.put("auto.offset.reset", "earliest");
            this.kafkaConsumer = new KafkaConsumer<String, String>(properties);
        }

        @Override
        public void run() {
            kafkaConsumer.subscribe(Arrays.asList(TOPIC));
            try {
                while (true) {
                    //长轮训拉取消息
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("partition = %d , offset = %d , key = %s  , value =%s",
                                record.partition(), record.offset(), record.key(), record.value()
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

        public void shutdown(){
            kafkaConsumer.wakeup();
        }
    }
}
