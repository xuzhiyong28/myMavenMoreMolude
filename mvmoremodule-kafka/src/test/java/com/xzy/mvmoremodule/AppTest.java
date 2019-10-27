package com.xzy.mvmoremodule;

import static org.junit.Assert.assertTrue;

import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue(){
        TopicPartition topicPartition1 = new TopicPartition("acc-log", 1);
        TopicPartition topicPartition2 = new TopicPartition("acc-log", 2);
        ConcurrentHashMap<TopicPartition, String> recordProcessorTasks = new ConcurrentHashMap<>();
        recordProcessorTasks.put(topicPartition1,"1");
        recordProcessorTasks.put(topicPartition2,"2");
        System.out.println(recordProcessorTasks);
    }
}
