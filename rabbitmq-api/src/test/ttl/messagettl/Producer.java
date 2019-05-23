package ttl.messagettl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    @Test
    public void test() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //创建Connection
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "ttl_exchange";
        String routingKey = "ttl_routingKey";
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.deliveryMode(2); //持久化消息
        builder.expiration("5000"); //设置消息过期时间
        AMQP.BasicProperties properties = builder.build();
        //发送
        String msg = "过期队列。。过期时间10秒 ";
        channel.basicPublish(exchangeName, routingKey, properties, msg.getBytes());
    }
}
