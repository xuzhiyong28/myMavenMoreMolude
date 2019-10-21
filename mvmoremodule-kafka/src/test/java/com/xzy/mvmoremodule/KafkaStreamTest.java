package com.xzy.mvmoremodule;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaStreamTest {

    /****
     * 统计流主题中单词出现频率
     */
    @Test
    public void test() throws InterruptedException {
        Properties prop = new Properties();
        //配置一个应用ID
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG, "kstreams_wordcount");
        //集群地址
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //设置序列化和反序列类键值属性
        prop.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        prop.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        //设置偏移量属性为最近
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //实例化流对象
        KStreamBuilder builder = new KStreamBuilder();
        //消费的主题
        KStream<String, String> source = builder.stream("streams_wordcount_input");

        //执行统计单词逻辑
        KTable<String, Long> counts = source.flatMapValues(new ValueMapper<String, Iterable<String>>() {
            @Override
            public Iterable<String> apply(String value) {
                List<String> words = Arrays.asList(value.toLowerCase(Locale.getDefault()).split(" "));
                return words;
            }
        }).map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
            @Override
            public KeyValue<String, String> apply(String key, String value) {
                return new KeyValue<>(value, value);
            }
        }).groupByKey().count("counts");

        //输出统计结果
        counts.print();
        //实例化一个流处理对象
        KafkaStreams streams = new KafkaStreams(builder, prop);
        //开始执行
        streams.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
