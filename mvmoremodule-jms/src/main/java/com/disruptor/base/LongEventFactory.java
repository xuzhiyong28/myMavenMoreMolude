package com.disruptor.base;

import com.lmax.disruptor.EventFactory;

/***
 * 发送数据的工厂类
 */
public class LongEventFactory implements EventFactory{

    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
