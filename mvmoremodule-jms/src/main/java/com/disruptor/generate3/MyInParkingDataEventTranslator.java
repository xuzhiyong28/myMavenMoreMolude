package com.disruptor.generate3;

import com.lmax.disruptor.EventTranslator;

/****
 * 具体的生产任务
 */
public class MyInParkingDataEventTranslator implements EventTranslator<Car> {
    @Override
    public void translateTo(Car car, long l) {
        this.generateData(car);
    }

    /***
     * 初始化车辆信息
     * @param car
     * @return
     */
    private Car generateData(Car car) {
        car.setCarLicense("车牌号： 闽D-" + (int) (Math.random() * 100000)); // 随机生成一个车牌号
        System.out.println("Thread Id " + Thread.currentThread().getId() + " 写完一个event");
        return car;
    }
}
