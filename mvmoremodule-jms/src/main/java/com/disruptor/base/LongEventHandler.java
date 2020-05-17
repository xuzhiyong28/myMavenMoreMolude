package com.disruptor.base;

import com.lmax.disruptor.EventHandler;

/***
 * 事件的处理器
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("执行任务，收到的值为：" + longEvent.getValue());
    }
}
