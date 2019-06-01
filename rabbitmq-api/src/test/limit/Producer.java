package limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-06-01-20:07
 */
public class Producer {

    @Test
    public void test() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.11.76");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchange = "test_qos_exchange";
        String routingKey = "qos.save";

        String msg = "Hello RabbitMQ QOS Message";

        for(int i =0; i<5; i ++){
            channel.basicPublish(exchange, routingKey, true, null, msg.getBytes());
        }
    }
}
