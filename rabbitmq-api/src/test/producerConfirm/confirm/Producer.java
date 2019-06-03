package producerConfirm.confirm;


import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Producer {
    @Test
    public void test() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //创建Connection
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "test002";
        String routingKey = "test002.direct";

        channel.confirmSelect(); //开启确认模式
        channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, "confirm".getBytes());
        if (!channel.waitForConfirms()) {
            System.out.println("send message fail!");
        }
        channel.close();
        connection.close();
    }

    @Test
    public void test2() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //创建Connection
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "test002";
        String routingKey = "test002.direct";
        List<String> msgList = new ArrayList<String>();
        channel.confirmSelect(); //开启确认模式
        int msgCount = 0 ;
        for (int i = 0; i < 10; i++) {
            String msg = "confirm_" + i;
            msgList.add(msg);
            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
            msgCount++;
            if(msgCount == 5){
                msgCount = 0;
                try {
                    if(channel.waitForConfirms()){
                        //将缓存信息清空
                        msgList.clear();
                    }else{
                        //将缓存的信息重新发送
                        System.out.println(msgList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test3() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //创建Connection
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "test002";
        String routingKey = "test002.direct";
        channel.confirmSelect(); //开启确认模式
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("Ack , SeqNo:" + deliveryTag + ", multiple = " + multiple);
            }
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                //返回报错，需要重新发送
                System.out.println("Nack , SeqNo:" + deliveryTag + ", multiple = " + multiple);
            }
        });
        for(int i = 0 ; i < 100 ; i++){
            Thread.sleep(1000);
            String msg = "confirm_" + i;
            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        }
    }
}
