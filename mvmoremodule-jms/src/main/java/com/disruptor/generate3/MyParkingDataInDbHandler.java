package com.disruptor.generate3;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/***
 * Handler 第一个消费者，负责保存进场汽车的信息
 */
public class MyParkingDataInDbHandler implements EventHandler<Car> , WorkHandler<Car> {
    @Override
    public void onEvent(Car car, long l, boolean b) throws Exception {
        long threadId = Thread.currentThread().getId(); // 获取当前线程id
        String carLicense = car.getCarLicense(); // 获取车牌号
        System.out.println(String.format("Thread Id %s 保存 %s 到数据库中 .... 时间 : %s", threadId, carLicense, DateFormatUtils.format(new Date(),"yyyy-MM-dd yyyy-MM-dd HH:mm:ss")));
        TimeUnit.SECONDS.sleep(1);
    }

    @Override
    public void onEvent(Car car) throws Exception {
        this.onEvent(car);
    }
}
