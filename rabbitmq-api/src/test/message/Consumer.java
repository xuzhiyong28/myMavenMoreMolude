package message;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-19-21:27
 */
public class Consumer {

    @Test
    public void test() throws IOException, InterruptedException, TimeoutException {
        //1 创建一个ConnectionFactory, 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        //3 通过connection创建一个Channel
        Channel channel = connection.createChannel();

        //4 声明（创建）一个队列
        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);

        //5 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //6 设置Channel
        channel.basicConsume(queueName, true, queueingConsumer);

        while (true){
            //获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.err.println("消费端: " + msg);
            Map<String,Object> headers = delivery.getProperties().getHeaders();
            System.err.println("headers get my1 value: " + headers.get("my1"));
        }
    }
}
