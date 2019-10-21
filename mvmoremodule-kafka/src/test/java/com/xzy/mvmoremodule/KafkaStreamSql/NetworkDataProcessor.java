package com.xzy.mvmoremodule.KafkaStreamSql;

import kafka.consumer.KafkaStream;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

/***
 * 这里设置一个处理流，不断的从主题中获取数据然后做业务逻辑处理
 * NetworkDataProcessor::getProcessor是具体的处理逻辑类
 */
public class NetworkDataProcessor implements Runnable {

    private static Processor<byte[], byte[]> getProcessor() {
        return new NetworkDataKafkaProcessor();
    }

    @Override
    public void run() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //kafka集群地址
        properties.put("poll.timeout", 1000);
        properties.put("application.id", "network_data_processor");
        properties.put("num.stream.threads", 1);
        StreamsConfig config = new StreamsConfig(properties);
        TopologyBuilder builder = new TopologyBuilder().addSource("SOURCE", "network-data")
                .addProcessor("PROCESSOR", NetworkDataProcessor::getProcessor, "SOURCE");
        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();
    }
}
