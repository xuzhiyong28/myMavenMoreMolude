package com.disruptor.base;

/***
 * 数据消息 --- 相当于数据对象 要通过工厂类放到RingBuffer
 */
public class LongEvent {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
