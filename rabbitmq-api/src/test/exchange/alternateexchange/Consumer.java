package exchange.alternateexchange;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/***
 * 备份交换机
 */
public class Consumer {

    @Test
    public void test() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //创建Connection
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        Map<String, Object> args = Maps.newHashMap();
        args.put("alternate-exchange", "myAe"); //定义normalExchange的备份交换机，当找不到对应队列时，消息发给备份交换机
        channel.exchangeDeclare("normalExchange", "direct", true, false, args);
        channel.exchangeDeclare("myAe", "fanout", true, false, null);

        //声明队列
        channel.queueDeclare("normalQueue", true, false, false, null);
        channel.queueBind("normalQueue","normalExchange","normalKey");

        channel.queueDeclare("unroutedQueue",true,false,false,null);
        channel.queueBind("unroutedQueue","myAe","");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume("unroutedQueue", true, consumer);
        //循环获取消息
        while(true){
            //获取消息，如果没有消息，这一步将会一直阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息：" + msg);
        }
    }

}
