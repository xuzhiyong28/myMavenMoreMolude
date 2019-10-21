package com.xzy.mvmoremodule.KafkaStreamSql;

import com.xzy.mvmoremodule.KafkaStreamSql.model.NetworkData;
import com.xzy.mvmoremodule.KafkaStreamSql.model.NetworkSignal;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNetworkDataProducer implements Runnable {


    @Override
    public void run() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //kafka集群地址
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key的序列化类
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value的序列化类
        Producer<String, String> producer = new KafkaProducer<>(properties);
        Random random = new Random();
        final int deviceCount = 100;
        List<String> deviceIds = IntStream.range(0, deviceCount).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.toList());
        //无线循环持续发送消息
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            NetworkData networkData = initNetworkData(deviceIds, random);
            //采用时间戳作为key值 kafka是根据key的hash值与分区数取模来决定数据存储到那个分区
            String key = "key-" + System.currentTimeMillis();
            String value = networkData.toString();
            ProducerRecord<String,String> record = new ProducerRecord<>("network-data",key,value);
            producer.send(record);
            try {
                TimeUnit.MICROSECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }

    /***
     * 随机构造数据
     * @param deviceIds
     * @param random
     * @return
     */
    public NetworkData initNetworkData(List<String> deviceIds, Random random) {
        final int deviceCount = 100;
        NetworkData networkData = new NetworkData();
        networkData.setDeviceId(deviceIds.get(random.nextInt(deviceCount - 1)));
        networkData.setSignals(new ArrayList<>());
        for (int j = 0; j < random.nextInt(4) + 1; j++) {
            NetworkSignal networkSignal = new NetworkSignal();
            networkSignal.setNetworkType(j % 2 == 0 ? "4G" : "wifi");
            networkSignal.setRxData((long) random.nextInt(1000));
            networkSignal.setTxData((long) random.nextInt(1000));
            networkSignal.setRxSpeed((double) random.nextInt(100));
            networkSignal.setTxSpeed((double) random.nextInt(100));
            networkSignal.setTime(System.currentTimeMillis());
            networkData.getSignals().add(networkSignal);
        }
        return networkData;
    }
}
