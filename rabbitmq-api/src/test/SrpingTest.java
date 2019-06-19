import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.xzy.conf.RabbitSender;
import com.xzy.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SrpingTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private RabbitSender rabbitSender;


    @Test
    public void initQueue() {

        //声明direct类型交换机和队列
        rabbitAdmin.declareExchange(new DirectExchange("test.direct", false, false));
        rabbitAdmin.declareQueue(new Queue("test.direct.queue", false));
        rabbitAdmin.declareBinding(new Binding("test.direct.queue",
                Binding.DestinationType.QUEUE, "test.direct", "directKey", new HashMap<String, Object>()));


        //声明topic类型交换机和队列
        rabbitAdmin.declareExchange(new TopicExchange("test.topic", false, false));
        rabbitAdmin.declareQueue(new Queue("test.topic.queue", false));
        rabbitAdmin.declareBinding(new Binding("test.topic.queue",
                Binding.DestinationType.QUEUE, "test.topic", "topicKey.*", new HashMap<String, Object>()));


        //声明fanout类型的交换机和队列
        rabbitAdmin.declareExchange(new FanoutExchange("test.fanout", false, false));
        rabbitAdmin.declareQueue(new Queue("test.fanout.queue", false));
        rabbitAdmin.declareBinding(new Binding("test.fanout.queue",
                Binding.DestinationType.QUEUE, "test.fanout", "", new HashMap<String, Object>()));
    }


    @Test
    public void testSendMessage() {
        //创建消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc", "信息描述..");
        messageProperties.getHeaders().put("type", "自定义消息类型..");
        Message message = new Message("Hello RabbitMQ".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("test.direct", "directKey", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.err.println("------添加额外的设置---------");
                message.getMessageProperties().getHeaders().put("desc", "额外修改的信息描述");
                message.getMessageProperties().getHeaders().put("attr", "额外新加的属性");
                return message;
            }
        });
    }

    @Test
    public void testSendMessage2() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plan");
        Message message = new Message("mg 消息1234".getBytes(), messageProperties);
        rabbitTemplate.send("test.direct", "directKey", message);
        rabbitTemplate.convertAndSend("test.direct", "directKey", "message other");
    }


    @Test
    public void testSendMessage3() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plan");
        Message message = new Message("mg 消息1234".getBytes(), messageProperties);
        rabbitTemplate.send("test.direct", "directKey", message);
    }


    @Test
    public void testSendJsonMessage() throws JsonProcessingException {
        Order order = new Order();
        order.setId("001");
        order.setName("消息订单");
        order.setContent("描述信息");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(order);
        System.err.println("order 4 json: " + json);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(json.getBytes(), messageProperties);
        rabbitTemplate.send("test.direct", "directKey", message);
    }

    @Test
    public void testRabbitSender() throws InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Map<String, Object> properties = Maps.newHashMap();
        properties.put("number", "12345");
        properties.put("send_time", simpleDateFormat.format(new Date()));
        rabbitSender.send("xuzhiyong.....",properties);
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void testRabbitSenderOrder(){
        Order order = new Order("001", "第一个订单","content");
        rabbitSender.sendOrder(order);
    }

}
