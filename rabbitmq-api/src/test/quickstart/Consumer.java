package quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-19-13:20
 */
public class Consumer {

    @Test
    public void test() throws IOException, TimeoutException, InterruptedException {
        //创建一个ConnectionFactory, 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        //获取一个信道
        Channel channel = connection.createChannel();

        //声明一个队列
        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);

        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //设置Channel
        channel.basicConsume(queueName, true, queueingConsumer);

        while (true){
            //获取消息
            Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.err.println("消费端: " + msg);
        }
    }

}
