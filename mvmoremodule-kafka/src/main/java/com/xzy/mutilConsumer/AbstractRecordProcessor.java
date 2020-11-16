package com.xzy.mutilConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/***
 * 抽象消息处理器
 */
public abstract class AbstractRecordProcessor implements Runnable{

    protected MessageHandler messageHandler;

    //保存MsgReceiver线程发送过来的消息
    private BlockingQueue<ConsumerRecord<String, String>> queue = new LinkedBlockingQueue<>();
    //用于向consumer线程提交消费偏移的队列
    private BlockingQueue<Map<TopicPartition, OffsetAndMetadata>> commitQueue;
    //消费了20条数据, 就进行一次提交
    private long commitLength = 20L;
    //当前该线程消费的数据条数
    private int completeTask = 0;
    //保存上一条消费的数据
    private ConsumerRecord<String, String> lastUncommittedRecord;

    public AbstractRecordProcessor(BlockingQueue<Map<TopicPartition, OffsetAndMetadata>> commitQueue , MessageHandler messageHandler) {
        this.commitQueue = commitQueue;
        this.messageHandler = messageHandler;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            ConsumerRecord<String, String> record = null;
            try {
                record = queue.poll(100, TimeUnit.MICROSECONDS);
                if (record != null) {
                    process(record);
                    //完成任务数加1
                    this.completeTask++;
                    //保存上一条处理记录
                    lastUncommittedRecord = record;
                }
                //提交偏移给queue中
                commitTOQueue();
            } catch (Exception e) {
                //线程被interrupt,直接退出
                System.out.println("记录record失败 ：" + record);
                //加入到redis进行补偿
            }

        }

    }

    //将当前的消费偏移量放到queue中, 由MsgReceiver进行提交
    private void commitTOQueue() {
        if (lastUncommittedRecord == null) {
            return;
        }
        //如果消费了设定的条数, 比如又消费了commitLength消息
        boolean arrivedCommitLength = this.completeTask % commitLength == 0;
        if (arrivedCommitLength) {
            long offset = lastUncommittedRecord.offset();
            int partition = lastUncommittedRecord.partition();
            String topic = lastUncommittedRecord.topic();
            TopicPartition topicPartition = new TopicPartition(topic, partition);
            Map<TopicPartition, OffsetAndMetadata> map = Collections.singletonMap(topicPartition, new OffsetAndMetadata(offset + 1L));
            commitQueue.add(map);
            //置空
            lastUncommittedRecord = null;
        }
    }

    //consumer线程向处理线程的队列中添加record
    public void addRecordToQueue(ConsumerRecord<String, String> record) {
        try {
            queue.put(record);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * 消息处理器
     * @param record
     */
    protected abstract void process(ConsumerRecord<String, String> record);
}
