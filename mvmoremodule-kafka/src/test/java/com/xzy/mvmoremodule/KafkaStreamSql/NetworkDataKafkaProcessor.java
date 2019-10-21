package com.xzy.mvmoremodule.KafkaStreamSql;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class NetworkDataKafkaProcessor implements Processor<byte[], byte[]> {

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
        this.context.schedule(1000);

    }

    /****
     * 具体处理逻辑
     * @param key
     * @param value
     */
    @Override
    public void process(byte[] key, byte[] value) {
        String valueStr = new String(value);
        String keyStr = new String(key);
        System.out.println(valueStr + "..." + keyStr);
    }

    @Override
    public void punctuate(long l) {
        context.commit();
    }

    @Override
    public void close() {

    }
}
