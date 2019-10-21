package com.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-09-11.
 */

import com.xzy.mvmoremodule.model.StockQuotationInfo;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-09-11-21:55
 */
public class QuotationProducer {
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


    public static void main(String[] args) {
        ProducerRecord<String, String> record = null;
        StockQuotationInfo stockQuotationInfo = null;
        try {
            int num = 0;
            for (int i = 0; i < 100; i++) {
                stockQuotationInfo = createQuotationInfo();
                //参数分别是 ： 主题 分区 key value
                //kafka在通过hash(key) % numPartitions的方式将key存在不同的分区，如果不指定key 则按照kafka的方式
                record = new ProducerRecord<String, String>(TOPIC, null, stockQuotationInfo.getTradeTime(), stockQuotationInfo.getStockCode(), stockQuotationInfo.toString());
                //kafkaProducer.send(record); //发送消息
                //有回调的发送
                kafkaProducer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if(null != e){
                            System.out.println(e);
                        }
                        if(null != recordMetadata){
                            System.out.println(String.format("offset:%s,partition:%s", recordMetadata.offset() , recordMetadata.partition()));
                        }
                    }
                });
                Future<RecordMetadata> future = kafkaProducer.send(record);
                //返回一个future，并调用get等待发送成功返回
                future.get();


                if(num % 10 == 0){
                    TimeUnit.SECONDS.sleep(2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            kafkaProducer.close(); //记得关闭
        }
    }

}
