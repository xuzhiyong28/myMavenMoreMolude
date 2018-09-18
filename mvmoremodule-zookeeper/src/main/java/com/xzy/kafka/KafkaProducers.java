package com.xzy.kafka;/**
 * Created by Administrator on 2018-08-26.
 */

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * @author xuzhiyong
 * @createDate 2018-08-26-20:33
 */
public class KafkaProducers {
    private static final String KAFKA_URL = "192.168.199.128:9092,192.168.199.129:9092,192.168.199.130:9092";

    private final KafkaProducer<Integer,String> producer;

    private final String topic;


    public KafkaProducers(KafkaProducer<Integer, String> producer, String topic) {
        this.producer = producer;
        this.topic = topic;
        Properties prop = new Properties();
    }
}
