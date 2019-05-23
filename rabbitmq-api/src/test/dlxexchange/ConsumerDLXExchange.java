package dlxexchange;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class ConsumerDLXExchange {
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

        //声明死信路由
        String DLX_exchangeName = "exchange.dlx.dlx";
        String DLX_exchangeType = "fanout";
        String DLX_queueName = "queue.dlx.dlx";
        String DLX_routingKey = ""; //路由键 fanout类型可以不用路由键
        channel.exchangeDeclare(DLX_exchangeName,DLX_exchangeType,true, false, false, null);
        channel.queueDeclare(DLX_queueName,false, false, false, null);
        channel.queueBind(DLX_queueName,DLX_exchangeName,DLX_routingKey);


        //声明 正常路由
        String exchangeName = "exchange.dlx.normal"; //交换机名称
        String exchangeType = "direct"; //交换机类型
        String queueName = "queue.dlx.normal"; //队列名称
        String routingKey = "normal_routingKey"; //路由键
        //表示声明了一个交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        //表示声明了一个队列,并关联死信
        Map<String,Object> args = Maps.newHashMap();
        args.put("x-message-ttl",10000); //过期后队列里的消息被发送到死信
        args.put("x-dead-letter-exchange","exchange.dlx.dlx"); //设置队列queue.dlx.normal的死信
        channel.queueDeclare(queueName, false, false, false, args);
        //建立一个绑定关系:
        channel.queueBind(queueName, exchangeName, routingKey);


        /*
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息：" + msg);
        }*/

    }
}
