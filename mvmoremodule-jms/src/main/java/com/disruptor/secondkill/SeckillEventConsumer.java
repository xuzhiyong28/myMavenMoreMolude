package com.disruptor.secondkill;

import com.lmax.disruptor.EventHandler;

/***
 * 消费者
 */
public class SeckillEventConsumer implements EventHandler<SeckillEvent> {

    private String consumerName;

    public SeckillEventConsumer() {
    }

    public SeckillEventConsumer(String consumerName) {
        this.consumerName = consumerName;
    }


    @Override
    public void onEvent(SeckillEvent seckillEvent, long seq, boolean bool) throws Exception {
        System.out.println("消费者名称:" + consumerName + ",消费的ID ：" + seckillEvent.getSeckillId());
    }
}
