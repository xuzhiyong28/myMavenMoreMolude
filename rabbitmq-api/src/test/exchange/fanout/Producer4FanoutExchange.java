package exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-19-20:54
 */
public class Producer4FanoutExchange {

    @Test
    public void test() throws IOException, TimeoutException {
        //1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2 创建Connection
        Connection connection = connectionFactory.newConnection();
        //3 创建Channel
        Channel channel = connection.createChannel();
        //声明
        String exchangeName = "test_fanout_exchange";
        //发送
        for (int i = 0; i < 10; i++) {
            String msg = "Hello World RabbitMQ 4 FANOUT Exchange Message ...";
            //生产者发送消息是不会直接发给quaua,而是需要通过路由
            channel.basicPublish(exchangeName, "", null, msg.getBytes());
        }
    }
}
