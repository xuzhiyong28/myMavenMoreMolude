package com.mvmoremoduleJms.activemqJms;/**
 * Created by Administrator on 2018-04-01.
 */

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Iterator;

/**
 * @author xuzhiyong
 * @createDate 2018-04-01-13:01
 * 消费者，实际上都是用一个监听器来监听。手动去获取的比较少
 */
@Service("consumerService")
public class ConsumerService {
    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination){
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        try{
            System.out.println("从队列" + destination.toString() + "收到了消息：\t"
                    + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return textMessage;
    }

}
