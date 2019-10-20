package com.xzy.mvmoremodule;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {

    Properties properties = new Properties();
    private static KafkaProducer<String, String> kafkaProducer = null;

    @Before
    public void beforeProperties() {
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //kafka集群地址
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key的序列化类
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value的序列化类
        //设置应答机制
        properties.put("acks", "1");
        //批量提交大小
        properties.put("batch.size", 16384);
        //设置延迟提交
        properties.put("linger.ms", 1);
        //缓冲大小
        properties.put("buffer.memory", 33554432);
        kafkaProducer = new KafkaProducer<>(properties);
    }

    @Test
    public void testSendBySingle() {
        ProducerRecord<String, String> record = null;
        for (int i = 0; i < 100; i++) {
            record = new ProducerRecord<>("topic20191019", "消息_" + i);
            kafkaProducer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println(recordMetadata);
                }
            });
        }
        kafkaProducer.close();
    }

    @Test
    public void testSendByMutil() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0 ; i < 100 ; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    ProducerRecord<String, String> record = null;
                    for (int i = 0; i < 100; i++) {
                        record = new ProducerRecord<>("topic20191019", "消息_" + Thread.currentThread().getName() + "_" + i);
                        kafkaProducer.send(record, new Callback() {
                            @Override
                            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                                System.out.println(recordMetadata);
                            }
                        });
                    }
                }
            });
        }
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.shutdown();
        kafkaProducer.close();
    }
}
