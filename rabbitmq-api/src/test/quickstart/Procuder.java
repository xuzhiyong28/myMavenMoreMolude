package quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-19-13:30
 */
public class Procuder {

    @Test
    public void test() throws IOException, TimeoutException {
        //1 创建一个ConnectionFactory, 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //通过connection创建一个Channel
        Channel channel = connection.createChannel();
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ!";
            channel.basicPublish("", "test001", null, msg.getBytes());
        }
        //记得关闭连接
        channel.close();
        connection.close();
    }
}
