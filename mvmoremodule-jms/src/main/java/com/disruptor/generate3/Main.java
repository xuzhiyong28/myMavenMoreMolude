package com.disruptor.generate3;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String agrs[]){
        ExecutorService executor;
        Disruptor<Car> disruptor;
        int bufferSize = 2048; // 2的N次方
        try{
            executor = Executors.newFixedThreadPool(4);
            disruptor = new Disruptor<Car>(new EventFactory<Car>(){
                @Override
                public Car newInstance() {
                    return new Car();
                }
            },bufferSize,executor, ProducerType.SINGLE,new YieldingWaitStrategy());

            EventHandlerGroup<Car> handlerGroup = disruptor.handleEventsWith(new MyParkingDataInDbHandler(), new MyParkingDataToKafkaHandler());
            // 当上面两个消费者处理结束后在消耗 smsHandler
            handlerGroup.then(new MyParkingDataSmsHandler());
            //启动disruptor
            disruptor.start();

            //模拟车辆入库 这里可以直接执行run
            /*CountDownLatch countDownLatch = new CountDownLatch(1);
            executor.submit(new MyInParkingDataEventPublisher(countDownLatch,disruptor));
            countDownLatch.await();*/

            MyInParkingDataEventTranslator eventTranslator = new MyInParkingDataEventTranslator();
            for(int i = 0 ; i < 100000 ; i++){
                disruptor.publishEvent(eventTranslator);
            }
            //disruptor.shutdown();
            //executor.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
