package com.xzy.conf;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author xuzhiyong
 * @createDate 2019-06-16-15:18
 * 数据转换
 */
public class TextMessageConverter implements MessageConverter {
    //java对象转换成message对象
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(o.toString().getBytes(), messageProperties);
    }


    //message对象转换成java对象
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String contentType = message.getMessageProperties().getContentType();
        if(null != contentType && contentType.contains("text")){
            return new String(message.getBody());
        }
        return message.getBody();
    }
}
