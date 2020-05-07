package com.disruptor.secondkill;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.TimeUnit;

public class SeckillEventMain {

    public static void main(String agrs[]) throws InterruptedException {
        //创建disruptor
        Disruptor<SeckillEvent> disruptor = new Disruptor<>(new SeckillEventFactory(), 1024 * 2, new ThreadFactoryBuilder().setNameFormat("Orders-%d")
                .setDaemon(true).build());
        //连接消费事件方法,这里采用多个消费者并发

        disruptor.handleEventsWith(new SeckillEventConsumer("消费者1"),
                new SeckillEventConsumer("消费者2"));

        //启动
        disruptor.start();

        //多线程生产数据
        RingBuffer<SeckillEvent> ringBuffer = disruptor.getRingBuffer();
        SeckillEventProducer producer = new SeckillEventProducer(ringBuffer);
        for (long i = 0; i < 100; i++) {
            producer.seckill(i, i);
        }
        TimeUnit.SECONDS.sleep(10);
        disruptor.shutdown();

    }
}
