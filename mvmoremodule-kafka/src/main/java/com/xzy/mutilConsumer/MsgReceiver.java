package com.xzy.mutilConsumer;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xuzhiyong
 * @createDate 2019-10-26-21:50
 */
public class MsgReceiver implements Runnable {


    private BlockingQueue<Map<TopicPartition, OffsetAndMetadata>> commitQueue = new LinkedBlockingQueue<>();

    private ConcurrentHashMap<TopicPartition, Thread> recordProcessorThreads;
    private ConcurrentHashMap<TopicPartition, RecordProcessor> recordProcessorTasks;
    private String alarmTopic;
    private Map<String, Object> consumerConfig;


    public MsgReceiver(Map<String, Object> consumerConfig, String alarmTopic,
                       ConcurrentHashMap<TopicPartition, RecordProcessor> recordProcessorTasks,
                       ConcurrentHashMap<TopicPartition, Thread> recordProcessorThreads) {

        this.consumerConfig = consumerConfig;
        this.alarmTopic = alarmTopic;
        this.recordProcessorTasks = recordProcessorTasks;
        this.recordProcessorThreads = recordProcessorThreads;
    }

    @Override
    public void run() {
        //kafka Consumer是非线程安全的,所以需要每个线程建立一个consumer
        KafkaConsumer kafkaConsumer = new KafkaConsumer(consumerConfig);
        kafkaConsumer.subscribe(Arrays.asList(alarmTopic));
        try {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    //看commitQueue里面是非有需要提交的offest， 这样查看好频繁啊！！！
                    //查看该消费者是否有需要提交的偏移信息, 使用非阻塞读取
                    Map<TopicPartition, OffsetAndMetadata> offestToCommit = commitQueue.poll();
                    if (offestToCommit != null) {
                        kafkaConsumer.commitAsync();
                    }
                    //最多轮询1000ms
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                    for (ConsumerRecord record : records) {
                        String topic = record.topic();
                        int partition = record.partition();
                        TopicPartition topicPartition = new TopicPartition(topic, partition);
                        //这里知道为什么可以循环把topicPartition放进去map里面而不担心内存大了
                        //topicPartition有重写了equal方法，相同主题和分区的是同一个，所以这里只存了几个分区就几个
                        RecordProcessor processTask = recordProcessorTasks.get(topicPartition);
                        //每条消息都去检查
                        //如果当前分区还没有开始消费, 则就没有消费任务在map中
                        if (processTask == null) {
                            //生成新的处理任务和线程, 然后将其放入对应的map中进行保存
                            processTask = new RecordProcessor(commitQueue);
                            recordProcessorTasks.put(topicPartition, processTask);

                            Thread processTaskThread = new Thread(processTask);
                            processTaskThread.setName("Thread-for " + topicPartition.toString());
                            processTaskThread.start();
                            recordProcessorThreads.put(topicPartition, processTaskThread);
                        }
                        //有 processor 可以处理该分区的 record了
                        processTask.addRecordToQueue(record);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            kafkaConsumer.close();
        }
    }
}
