package com.disruptor.generate3;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

public class MyInParkingDataEventPublisher implements Runnable {
    private static final Integer NUM = 1; // 1,10,100,1000
    private CountDownLatch countDownLatch;
    private Disruptor<Car> disruptor;

    public MyInParkingDataEventPublisher(CountDownLatch countDownLatch, Disruptor<Car> disruptor) {
        this.countDownLatch = countDownLatch;
        this.disruptor = disruptor;
    }

    @Override
    public void run() {
        MyInParkingDataEventTranslator eventTranslator = new MyInParkingDataEventTranslator();
        try{
            //模拟车辆入库
            for(int i = 0 ; i < NUM ; i++){
                disruptor.publishEvent(eventTranslator);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}
