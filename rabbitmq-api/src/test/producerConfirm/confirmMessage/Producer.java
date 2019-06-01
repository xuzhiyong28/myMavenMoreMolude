package producerConfirm.confirmMessage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-06-01-17:13
 */
public class Producer {
    @Test
    public void test() throws IOException, TimeoutException {
        //1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2 获取C	onnection
        Connection connection = connectionFactory.newConnection();

        //3 通过Connection创建一个新的Channel
        Channel channel = connection.createChannel();


        //4 指定我们的消息投递模式: 消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.save";

        //5 发送一条消息
        String msg = "Hello RabbitMQ Send confirm message!";
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        //添加监听
        channel.addConfirmListener(new ConfirmListener() {
            /***
             * 消息发送成功
             * @param deliveryTag 消息的唯一ID
             * @param multiple
             * @throws IOException
             */
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("=============ACK===============");
            }

            /***
             * 发送失败的时候 - 磁盘满了 or 网络问题 or
             * @param deliveryTag
             * @param multiple
             * @throws IOException
             */
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("================NO ACK===========");
            }
        });

        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
