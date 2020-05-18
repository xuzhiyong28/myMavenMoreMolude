package com.disruptor.generate3;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * 第三个消费者，sms短信服务，告知司机你已经进入停车场，计费开始。
 */
public class MyParkingDataSmsHandler implements EventHandler<Car>, WorkHandler<Car> {
    @Override
    public void onEvent(Car car, long l, boolean b) throws Exception {
        long threadId = Thread.currentThread().getId(); // 获取当前线程id
        String carLicense = car.getCarLicense(); // 获取车牌号
        System.out.println(String.format("Thread Id %s 给  %s 的车主发送一条短信，并告知他计费开始了 .... 时间 : %s", threadId, carLicense , DateFormatUtils.format(new Date(),"yyyy-MM-dd yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    public void onEvent(Car car) throws Exception {

    }
}
