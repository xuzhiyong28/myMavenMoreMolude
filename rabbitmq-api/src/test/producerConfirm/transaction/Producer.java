package producerConfirm.transaction;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuzhiyong
 * @createDate 2019-05-23-22:13
 * 事务的机制是发送一条消息之后会使发送端阻塞以等待RabbitMQ的回应
 * 事务的机制性能比较低，更好性能的是采用确认机制
 */
public class Producer {

    @Test
    public void test() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //创建Connection
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "test002";
        String routingKey = "test002.direct";

        try{
            //开启事务
            channel.txSelect();
            channel.basicPublish(exchangeName,routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,"transaction".getBytes());
            //提交事务
            int result = 1 / 0 ; //此处会出现异常
            channel.txCommit();
        }catch (Exception e){
            //回滚
            channel.txRollback();
        }


        channel.txSelect();
        for(int i = 0 ; i < 10 ; i++){
            try{
                channel.basicPublish(exchangeName,routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,"transaction".getBytes());
                channel.txCommit();
            }catch (Exception e){
                channel.txRollback();
            }
        }



    }
}
