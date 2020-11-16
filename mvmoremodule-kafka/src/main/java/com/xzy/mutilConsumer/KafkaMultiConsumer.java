package com.xzy.mutilConsumer;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 负责启动消费者线程MsgReceiver, 保存消费者线程MsgReceiver, 保存处理任务和线程RecordProcessor, 以及销毁这些线程
 * Created by xuzy
 */
public class KafkaMultiConsumer {

    //订阅的topic
    String alarmTopic;
    //消费者线程数组
    Thread[] threads;
    //消息处理器
    MessageHandler messageHandler;
    //kafka消费者进程数,默认是1个
    int consumerThreadNum = 1;

    private String groupId;

    //保存处理任务和线程的map
    ConcurrentHashMap<TopicPartition, AbstractRecordProcessor> recordProcessorTasks = new ConcurrentHashMap<>();
    ConcurrentHashMap<TopicPartition, Thread> recordProcessorThreads = new ConcurrentHashMap<>();
    //偏移量队列
    BlockingQueue<Map<TopicPartition, OffsetAndMetadata>> commitQueue = new LinkedBlockingQueue<>();

    public KafkaMultiConsumer(String topic , MessageHandler messageHandler){
        this.alarmTopic = topic;
        this.messageHandler = messageHandler;
    }

    /***
     * 启动消费者
     */
    public void startUp() {
        init();
        initGracefullyShutdown();
    }


    /***
     * 优雅关闭
     */
    public void initGracefullyShutdown(){
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("优雅关闭！！！！");
                //提交由于不正常关闭的偏移量
            }
        });
    }


    private void init() {
        Map<String, Object> consumerConfig = ImmutableMap.<String, Object>builder()
                .put("bootstrap.servers", "localhost:9092")
                .put("group.id", groupId)
                .put("enable.auto.commit", "false")
                .put("session.timeout.ms", "30000")
                .put("auto.offset.reset", "earliest")
                .put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
                .put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
                .put("max.poll.records", 10)
                .build();

        threads = new Thread[consumerThreadNum];
        for (int i = 0; i < consumerThreadNum; i++) {
            MsgReceiver msgReceiver = new MsgReceiver(consumerConfig, alarmTopic, recordProcessorTasks, recordProcessorThreads, messageHandler ,commitQueue);
            Thread thread = new Thread(msgReceiver);
            threads[i] = thread;
        }
        for (int i = 0; i < consumerThreadNum; i++) {
            threads[i].start();
        }
    }

    public String getAlarmTopic() {
        return alarmTopic;
    }

    public void setAlarmTopic(String alarmTopic) {
        this.alarmTopic = alarmTopic;
    }

    public Thread[] getThreads() {
        return threads;
    }

    public void setThreads(Thread[] threads) {
        this.threads = threads;
    }

    public int getConsumerThreadNum() {
        return consumerThreadNum;
    }

    public void setConsumerThreadNum(int consumerThreadNum) {
        this.consumerThreadNum = consumerThreadNum;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
