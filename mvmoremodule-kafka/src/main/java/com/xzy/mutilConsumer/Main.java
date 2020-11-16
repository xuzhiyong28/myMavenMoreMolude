package com.xzy.mutilConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        KafkaMultiConsumer kafkaMultiConsumer = new KafkaMultiConsumer("test001", new MessageHandler() {
            @Override
            public void execute(ConsumerRecord<String, String> record) {
                //System.out.printf("offset = %d, key = %s, partition = %s, value = %s%n", record.offset(), record.key(), record.partition(), record.value());
            }
        });
        kafkaMultiConsumer.setConsumerThreadNum(1);
        kafkaMultiConsumer.setGroupId("customer001");
        kafkaMultiConsumer.startUp();
        TimeUnit.HOURS.sleep(1);
    }
}
