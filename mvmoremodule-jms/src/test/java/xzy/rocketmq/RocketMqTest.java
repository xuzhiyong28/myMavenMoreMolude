package xzy.rocketmq;

import com.google.common.collect.Sets;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.testng.collections.Lists;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RocketMqTest {
    /***
     * 同步发送消息
     * @throws Exception
     */
    @Test
    public void syncProducerTest() throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("syncProducer_1");
        // 设置NameServer的地址
        producer.setNamesrvAddr("192.168.50.234:9876");
        // 启动
        producer.start();

        for (int i = 0; i < 800; i++) {
            //创建消息 并指定topic tag 和 消息体
            Message msg = new Message("topic_2", null, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown(); //关闭实例
    }


    /***
     * 异步发送消息
     * @throws Exception
     */
    @Test
    public void asyncProducerTest() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0); //是否重试
        int messageCount = 100;
        final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            Message msg = new Message(
                    "TopicTest",
                    "TagA",
                    "OrderID188",   // keys 索引建 用来快速找到消息
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        // 等待5s
        countDownLatch.await(5, TimeUnit.SECONDS);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

    /***
     * 单项消息
     * @throws Exception
     */
    @Test
    public void onewayProducerTest() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(msg);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }


    /***
     * 消费消息
     */
    @Test
    public void consumerTest() throws Exception {
        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerTest_2");
        // 设置NameServer的地址
        consumer.setNamesrvAddr("192.168.50.234:9876");
        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe("topic_2", "*");
        // 设置从queue的第一条消息开始消费
        // CONSUME_FROM_LAST_OFFSET  从queue的当前最后一条消息开始消费
        // CONSUME_FROM_FIRST_OFFSET  从queue的第一条消息开始消费
        // CONSUME_FROM_TIMESTAMP 从指定的具体时间戳位置的消息开始消费。这个具体时间戳 是通过另外一个语句指定的 consumer.setConsumeTimestamp(“20210701080000”) yyyyMMddHHmmss
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 批量消费，每次批量消费10条，最多不能超过32条。List<MessageExt> msgs的值就是10条
        consumer.setConsumeMessageBatchMaxSize(10);
        // 注册回调实现类来处理从broker拉取回来的消息 MessageListenerConcurrently表示并发消费，代码里面会启多个线程获取数据
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                Set<Integer> queueids = Sets.newHashSet();
                for(MessageExt msg : msgs) {
                    queueids.add(msg.getQueueId());
                }
                System.out.println("当前线程 :" + Thread.currentThread().getName() + ", msgsSize :" + msgs.size() + ", queueids : " + queueids);
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start(); // 启动实例
        System.out.printf("Consumer Started.%n");
        TimeUnit.SECONDS.sleep(1000);
    }


    @Test
    public void producerInOrderTest() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producerInOrder");
        producer.setNamesrvAddr("192.168.50.234:9876");
        producer.start();
        String[] tags = new String[]{"TagA", "TagC", "TagD"};
        List<OrderStep> orderList = buildOrders();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        for (int i = 0; i < 10; i++) {
            String body = dateStr + " Hello RocketMQ " + orderList.get(i).toString();
            Message msg = new Message("topic_1", tags[i % tags.length], "KEY" + i, body.getBytes());
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message message, Object arg) {
                    Long id = (Long) arg;  //根据订单id选择发送queue
                    long index = id % mqs.size();
                    return mqs.get((int) index);
                }
            }, orderList.get(i).getOrderId());
            System.out.println(String.format("SendResult status:%s, queueId:%d, body:%s",
                    sendResult.getSendStatus(),
                    sendResult.getMessageQueue().getQueueId(),
                    body));
        }
        producer.shutdown();
    }

    @Test
    public void consumerInOrderTest() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerInOrder001");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTest", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext consumeOrderlyContext) {
                consumeOrderlyContext.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    // 可以看到每个queue有唯一的consume线程来消费, 订单对每个queue(分区)有序
                    System.out.println("consumeThread=" + Thread.currentThread().getName() + "queueId=" + msg.getQueueId() + ", content:" + new String(msg.getBody()));
                }
                try {
                    //模拟业务逻辑处理中...
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
        TimeUnit.SECONDS.sleep(1000);
    }


    /***
     * 延时消息
     */
    @Test
    public void scheduledMessageProducerTest() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("scheduledMessageProducer01");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        int totalMessagesToSend = 100;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic_delay", ("Hello scheduled message " + i).getBytes());
            // 设置延时等级3,这个消息将在10s之后发送(现在只支持固定的几个时间,详看delayTimeLevel)
            message.setDelayTimeLevel(3);
            producer.send(message);
        }
        producer.shutdown();
    }

    @Test
    public void scheduledMessageConsumerTest() throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("scheduledMessageConsumer_01");
        consumer.subscribe("TestTopic_delay","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                for (MessageExt message : messages) {
                    System.out.println("Receive message[msgId=" + message.getMsgId() + "] " + (System.currentTimeMillis() - message.getBornTimestamp()) + "ms later");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }


    /***
     * 批量发送消息
     */
    @Test
    public void producerBatchTest() throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producerBatch01");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        String topic = "Topic_batch";
        List<Message> messages = Lists.newArrayList();
        messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
        producer.send(messages);
        producer.shutdown();
    }


    /***
     * 生成模拟订单
     * @return
     */
    public List<OrderStep> buildOrders() {
        List<OrderStep> orderList = new ArrayList<OrderStep>();
        OrderStep orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("推送");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        return orderList;
    }
}
