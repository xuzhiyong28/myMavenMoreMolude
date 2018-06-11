package com.mvmoremoduleJms.activemqJms;/**
 * Created by Administrator on 2018-04-01.
 */

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author xuzhiyong
 * @createDate 2018-04-01-12:58
 * 生产者
 */
@Service("producerService")
public class ProducerService {
    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String msg) {
        System.out.println(Thread.currentThread().getName() + " 向队列" + destination.toString() + "发送消息---------------------->" + msg);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    public void sendMessage(final String msg) {
        String destination = jmsTemplate.getDefaultDestinationName();
        System.out.println(Thread.currentThread().getName() + " 向队列" + destination + "发送消息---------------------->" + msg);
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

}
