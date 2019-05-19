package message;

import com.google.common.collect.Maps;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-19-21:20
 */
public class Procuder {
    @Test
    public void test() throws IOException, TimeoutException {
        //1 创建一个ConnectionFactory, 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        //3 通过connection创建一个Channel
        Channel channel = connection.createChannel();


        Map<String, Object> headers = Maps.newHashMap();
        headers.put("my1", "111");
        headers.put("my2", "222");
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .expiration("10000") //设置消息的过期时间 10秒后如果没有被消费就被会清除
                .headers(headers)
                .build();

        //通过channel发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ!";
            channel.basicPublish("", "test001", properties, msg.getBytes());
        }
        //关闭连接
        channel.close();
        connection.close();

    }
}
