package dlxexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerDLXExchange {

    @Test
    public void test() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory() ;

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "exchange.dlx.normal";
        String routingKey = "normal_routingKey";

        for (int i = 0; i < 10; i++) {
            String msg = "消息过期后发到死信队列";
            //生产者发送消息是不会直接发给quaua,而是需要通过路由
            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        }
    }
}
