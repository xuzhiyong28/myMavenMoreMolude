package exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-19-18:59
 */
public class Consumer4TopicExchange {

    @Test
    public void test() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        //4 声明
        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";
        String routingKey = "user.*";

        //声明交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        //声明队列
        channel.queueDeclare(queueName,false,false,false,null);
        //建立关系
        channel.queueBind(queueName,exchangeName,routingKey);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        //参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName, true, consumer);
        while (true){
            //获取消息，如果没有消息，这一步将会一直阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息：" + msg);
        }
    }
}
