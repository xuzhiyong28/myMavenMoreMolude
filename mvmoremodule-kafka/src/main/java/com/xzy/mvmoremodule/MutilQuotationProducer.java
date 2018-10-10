package com.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-09-12.
 */

import com.xzy.mvmoremodule.model.StockQuotationInfo;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-09-12-21:00
 * 多线程的方式发送
 */
public class MutilQuotationProducer {
    private static final String TOPIC = "stock-quotation"; //topic name
    private static final String BROKER_LIST = "192.168.199.128:9092,192.168.199.129:9092,192.168.199.130:9092";
    private static KafkaProducer<String, String> kafkaProducer = null;

    static {
        Properties properties = intiConfig();
        kafkaProducer = new KafkaProducer<String, String>(properties);
    }

    /***
     * 初始化kafka配置
     * @return
     */
    private static Properties intiConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST); //kafka集群地址
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key的序列化类
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value的序列化类
        return properties;
    }

    /***
     * 产生消息
     * @return
     */
    private static StockQuotationInfo createQuotationInfo() {
        StockQuotationInfo stockQuotationInfo = new StockQuotationInfo();
        Random r = new Random();
        Integer stockCode = 600100 + r.nextInt(10);
        float random = (float) Math.random();
        stockQuotationInfo.setCurrentPrice(random);
        stockQuotationInfo.setHighPrice(random + 1);
        stockQuotationInfo.setLowPrice(random - 1);
        stockQuotationInfo.setTradeTime(System.currentTimeMillis());
        stockQuotationInfo.setStockCode(stockCode.toString()); //股票代码
        stockQuotationInfo.setStockName("股票_" + stockCode);
        return stockQuotationInfo;
    }


    public static void main(String[] agrs) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        ProducerRecord<String, String> record;
        try {
            for (int i = 0; i < 100; i++) {
                StockQuotationInfo stockQuotationInfo = createQuotationInfo();
                record = new ProducerRecord<String, String>(TOPIC, null, stockQuotationInfo.getTradeTime(), stockQuotationInfo.getStockCode(), stockQuotationInfo.toString());
                executorService.submit(new ProducerThead(kafkaProducer, record));
            }
        } catch (Exception e) {

        } finally {
            kafkaProducer.close();
            executorService.shutdown();
            System.out.println("close");
        }
    }

    static class ProducerThead implements Runnable {
        private KafkaProducer<String, String> kafkaProducer;
        private ProducerRecord<String, String> producerRecord;

        public ProducerThead(KafkaProducer<String, String> kafkaProducer, ProducerRecord<String, String> producerRecord) {
            this.kafkaProducer = kafkaProducer;
            this.producerRecord = producerRecord;
        }

        @Override
        public void run() {
            kafkaProducer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (null != e) {
                        System.out.println(e);
                    }
                    if (null != recordMetadata) {
                        System.out.println(String.format("offset:%s,partition:%s", recordMetadata.offset(), recordMetadata.partition()));
                    }
                }
            });
        }
    }


}
