package producerConfirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-23-22:11
 */
public class Customer {
    @Test
    public void initQueue() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory() ;
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //4 声明
        String exchangeName = "test002";
        String exchangeType = "direct";
        String queueName = "test002_queue";
        String routingKey = "test002.direct";

        //表示声明了一个交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        //表示声明了一个队列
        channel.queueDeclare(queueName, false, false, false, null);
        //建立一个绑定关系:
        channel.queueBind(queueName, exchangeName, routingKey);
    }
}
