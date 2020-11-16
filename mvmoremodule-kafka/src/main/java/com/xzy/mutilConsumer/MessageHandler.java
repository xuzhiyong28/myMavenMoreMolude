package com.xzy.mutilConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/***
 * 消息处理器
 */
public interface MessageHandler {
    public void execute(ConsumerRecord<String, String> record);
}
