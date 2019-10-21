package com.xzy.mvmoremodule;

import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @author xuzhiyong
 * @createDate 2019-10-21-22:12
 * 自定义用来处理黑名单的Processor实现
 */
public class BlackListProcessor implements Processor<Windowed<String>, Long> {

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
    }

    /***
     * 做处理的方法
     * @param stringWindowed
     * @param aLong
     */
    @Override
    public void process(Windowed<String> stringWindowed, Long aLong) {
        System.out.println("===========开始打印出黑名单操作的key和value==============");
        System.out.println("key = " + stringWindowed.key());
        System.out.println("value = " + aLong.longValue());
        System.out.println("时间窗口 = " + stringWindowed.window().start());

    }

    @Override
    public void punctuate(long timestamp) {

    }

    @Override
    public void close() {

    }
}
