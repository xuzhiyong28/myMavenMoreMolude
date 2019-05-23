package ttl.queuettl;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Consumer {

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

        //4 声明
        String exchangeName = "ttl_exchange";
        String exchangeType = "direct";
        String queueName = "ttl_queue";
        String routingKey = "ttl_routingKey";

        //表示声明了一个交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        //表示声明了一个队列
        Map<String,Object> args = Maps.newHashMap();
        //设置当前队列是过期队列，过期时间为10秒
        args.put("x-message-ttl",10000);
        channel.queueDeclare(queueName, false, false, false, args);
        //建立一个绑定关系:
        channel.queueBind(queueName, exchangeName, routingKey);

        channel.close();
        connection.close();
    }
}
