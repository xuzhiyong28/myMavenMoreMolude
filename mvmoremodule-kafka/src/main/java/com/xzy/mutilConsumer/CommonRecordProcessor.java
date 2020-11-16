package com.xzy.mutilConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

/***
 * 主题:消息处理器
 */
public class CommonRecordProcessor extends AbstractRecordProcessor{

    public CommonRecordProcessor(BlockingQueue<Map<TopicPartition, OffsetAndMetadata>> commitQueue, MessageHandler messageHandler) {
        super(commitQueue, messageHandler);
    }

    @Override
    protected void process(ConsumerRecord<String, String> record) {
        messageHandler.execute(record);
    }
}
