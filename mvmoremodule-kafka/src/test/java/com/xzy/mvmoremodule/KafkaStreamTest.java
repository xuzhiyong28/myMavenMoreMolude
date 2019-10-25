package com.xzy.mvmoremodule;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.processor.TopologyBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaStreamTest {

    /****
     * 统计流主题中单词出现频率
     */
    @Test
    public void test() throws InterruptedException {
        Properties prop = new Properties();
        //配置一个应用ID
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG, "kstreams_wordcount");
        //集群地址
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //设置序列化和反序列类键值属性
        prop.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        prop.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        //设置偏移量属性为最近
        //prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //实例化流对象
        KStreamBuilder builder = new KStreamBuilder();
        //消费的主题
        KStream<String, String> source = builder.stream("streams_wordcount_input");
        //执行统计单词逻辑
        KTable<String, Long> counts = source.flatMapValues(new ValueMapper<String, Iterable<String>>() {
            @Override
            public Iterable<String> apply(String value) {
                List<String> words = Arrays.asList(value.toLowerCase(Locale.getDefault()).split(" "));
                return words;
            }
        }).map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
            @Override
            public KeyValue<String, String> apply(String key, String value) {
                return new KeyValue<>(value, value);
            }
        }).groupByKey().count("counts");

        //输出统计结果
        counts.print();
        //实例化一个流处理对象
        KafkaStreams streams = new KafkaStreams(builder, prop);
        //开始执行
        streams.start();
        TimeUnit.SECONDS.sleep(90);
    }


    /***
     * 进行单词的统计
     */
    @Test
    public void wordcountTest(){
        String kk = "{\"startdate\":\"2019-10-10\",\"enddate\":\"2019-10-10\"}";
        System.out.println(StringUtils.startsWith(kk,"{"));
    }


    /***
     * 从kafka主题中获取访问日志，通过流的方式，10秒钟内访问超过100次的做业务逻辑处理
     */
    @Test
    public void kafkaStreamProcessor() {
        Properties prop = new Properties();
        //执行流处理应用的ID
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG, "kstreams_ip");
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        prop.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        //选择最近的消费进行消费
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //设置保存处理器保存当前的频率
        prop.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "50");
        //设置轮询kafka主题获取数据源的等待时间间隔
        prop.put(StreamsConfig.POLL_MS_CONFIG, "10");

        KStreamBuilder kStreamBuilder = new KStreamBuilder();
        //拿到数据
        KStream<String, String> access = kStreamBuilder.stream("acc-log");

        //map函数将访问记录映射成key-value
        //groupByKey + count 根据key值进行分组,统计出现次数（分组需要窗口的概念，在一段时间内的分组,这里切分成10秒钟一个窗口）
        //filter 进行过滤，这里是过滤窗口时间内次数超过100的
        access
                .map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
                    @Override
                    public KeyValue<String, String> apply(String key, String value) {
                        return new KeyValue<>(value, value);
                    }
                })
                .groupByKey()
                .count(TimeWindows.of(10 * 1000L).advanceBy(10 * 1000L), "acc-log")
                .toStream()
                .filter(new Predicate<Windowed<String>, Long>() {
                    @Override
                    public boolean test(Windowed<String> stringWindowed, Long value) {
                        if (null != value && value.longValue() >= 100) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .process(new ProcessorSupplier<Windowed<String>, Long>() {
                    @Override
                    public Processor<Windowed<String>, Long> get() {
                        return new BlackListProcessor();
                    }
                }, "acc-log");

        access.print();
        KafkaStreams streams = new KafkaStreams(kStreamBuilder, prop);
        streams.start();

        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        streams.close();
    }


    /***
     * 初始化数据
     */
    @Test
    public void initTopicMessage() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //kafka集群地址
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key的序列化类
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value的序列化类
        Producer<String, String> producer = new KafkaProducer<>(properties);
        String[] names = {"xuzhiyong", "chenyixiang", "gaoyongshun", "liangxianhui"};
        for (int i = 0; i < 10000; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("acc-log", names[RandomUtils.nextInt(0, 3)]);
            producer.send(record);
        }
        producer.close();
    }

    /***
     * 简单流处理
     * 程序 ----发送消息-----> topic1 ------流处理----->  topic2
     */
    @Test
    public void easyStreamTest() {
        // 定义输入的 topic
        String from = "firstTopic";
        // 定义输出的 topic
        String to = "secondTopic";
        Properties settings = new Properties();
        //定义这个流处理的应用ID
        settings.put(StreamsConfig.APPLICATION_ID_CONFIG, "logFilter");
        settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        StreamsConfig config = new StreamsConfig(settings);
        TopologyBuilder builder = new TopologyBuilder();
        builder.addSource("SOURCE",from)
               .addProcessor("PROCESS", new ProcessorSupplier() {
                   @Override
                   public Processor get() {
                       return new Processor<byte[], byte[]>() {
                           private ProcessorContext processorContext;
                           @Override
                           public void init(ProcessorContext processorContext) {
                               this.processorContext = processorContext;
                           }

                           @Override
                           public void process(byte[] keyByte, byte[] valueByte) {
                                String line = new String(valueByte) + "_xuzy";
                                processorContext.forward(keyByte, line.getBytes());
                           }

                           @Override
                           public void punctuate(long l) {

                           }

                           @Override
                           public void close() {

                           }
                       };
                   }
               },"SOURCE")
               .addSink("SINK", to , "PROCESS");

        //创建kafka stream
        KafkaStreams streams = new KafkaStreams(builder,config);
        streams.start();
    }


}
