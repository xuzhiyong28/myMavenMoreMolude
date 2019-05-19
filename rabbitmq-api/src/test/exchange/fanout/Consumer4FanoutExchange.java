package exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-19-20:11
 */
public class Consumer4FanoutExchange {

    @Test
    public void test() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory() ;

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        String exchangeName = "test_fanout_exchange";
        String exchangeType = "fanout";
        String quauaName = "test_fanout_queue";
        //fanout全路由模式 routingkey没用，随便填或者不填
        String routingKey = "";
        channel.exchangeDeclare(exchangeName,exchangeType,true, false, false, null);
        channel.queueDeclare(quauaName,false,false,false,null);
        channel.queueBind(quauaName,exchangeName,routingKey);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(quauaName,true,consumer);
        //循环获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息:" + msg);
        }
    }
}
