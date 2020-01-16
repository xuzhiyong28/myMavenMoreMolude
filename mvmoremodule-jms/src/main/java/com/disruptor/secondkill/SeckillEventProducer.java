package com.disruptor.secondkill;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

public class SeckillEventProducer {

    private RingBuffer<SeckillEvent> ringBuffer;

    public void seckill(long seckillId, long userId) {
        this.ringBuffer.publishEvent(new EventTranslatorVararg<SeckillEvent>() {
            @Override
            public void translateTo(SeckillEvent seckillEvent, long seq, Object... objects) {
                seckillEvent.setSeckillId((Long) objects[0]);
                seckillEvent.setUserId((Long) objects[1]);
            }
        }, seckillId, userId);
    }

    public SeckillEventProducer(RingBuffer<SeckillEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    public RingBuffer<SeckillEvent> getRingBuffer() {
        return ringBuffer;
    }

    public void setRingBuffer(RingBuffer<SeckillEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
}
