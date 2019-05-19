package exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-19-18:55
 */
public class Producer4DirectExchange {

    @Test
    public void test() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //创建Connection
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "test_direct_exchange";
        String routingKey = "test.direct";

        //发送
        String msg = "Hello World RabbitMQ 4  Direct Exchange Message 111 ... ";
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

    }
}
