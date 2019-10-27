package com.xzy.mutilConsumer;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 负责启动消费者线程MsgReceiver, 保存消费者线程MsgReceiver, 保存处理任务和线程RecordProcessor, 以及销毁这些线程
 * Created by xuzy
 */
public class KafkaMultiProcessorMain {

    // 消费者参数
    Properties consumerProps = new Properties();
    Map<String, Object> consumerConfig;
    //存放topic的配置
    Map<String, Object> topicConfig;
    //订阅的topic
    String alarmTopic;
    //消费者线程数组
    Thread[] threads;

    //保存处理任务和线程的map
    ConcurrentHashMap<TopicPartition, RecordProcessor> recordProcessorTasks = new ConcurrentHashMap<>();
    ConcurrentHashMap<TopicPartition, Thread> recordProcessorThreads = new ConcurrentHashMap<>();


    public void main(String[] agrs) {
        KafkaMultiProcessorMain kafkaMultiProcessor = new KafkaMultiProcessorMain();
        kafkaMultiProcessor.setAlarmTopic("picrecord");
        kafkaMultiProcessor.init(null);
    }

    private void init(String consumerPropPath) {
        consumerConfig = ImmutableMap.<String, Object>builder()
                .put("bootstrap.servers", "localhost:9092")
                .put("group.id", "group.id")
                .put("enable.auto.commit", "false")
                .put("session.timeout.ms", "30000")
                .put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
                .put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
                .put("max.poll.records", 1000)
                .build();

        int threadsNum = 3;
        threads = new Thread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            MsgReceiver msgReceiver = new MsgReceiver(consumerConfig, alarmTopic, recordProcessorTasks, recordProcessorThreads);
            Thread thread = new Thread(msgReceiver);
            threads[i] = thread;
        }
        for (int i = 0; i < threadsNum; i++) {
            threads[i].start();
        }
    }

    public String getAlarmTopic() {
        return alarmTopic;
    }

    public void setAlarmTopic(String alarmTopic) {
        this.alarmTopic = alarmTopic;
    }
}
