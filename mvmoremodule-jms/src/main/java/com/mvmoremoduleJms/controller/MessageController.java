package com.mvmoremoduleJms.controller;/**
 * Created by Administrator on 2018-04-01.
 */

import com.mvmoremoduleJms.activemqJms.ConsumerService;
import com.mvmoremoduleJms.activemqJms.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * @author xuzhiyong
 * @createDate 2018-04-01-13:05
 */
@Controller
public class MessageController {
    @Resource(name = "demoQueueDestination")
    private Destination destination;

    //队列消息生产者
    @Resource(name = "producerService")
    private ProducerService producer;

    //队列消息消费者
    @Resource(name = "consumerService")
    private ConsumerService consumer;

    @RequestMapping(value = "/SendMessage", method = RequestMethod.POST)
    @ResponseBody
    public void send(String msg) {
        producer.sendMessage(msg);
    }

    @RequestMapping(value= "/ReceiveMessage",method = RequestMethod.GET)
    @ResponseBody
    public Object receive(){
        TextMessage tm = consumer.receive(destination);
        return tm;
    }

}
