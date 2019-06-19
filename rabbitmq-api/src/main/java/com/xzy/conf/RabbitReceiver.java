package com.xzy.conf;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

import com.rabbitmq.client.Channel;

import java.io.IOException;

@Component
public class RabbitReceiver {


    @RabbitListener(bindings = @QueueBinding(
                value = @Queue(value = "test.direct.queue",
                durable = "true"),
                exchange = @Exchange(value = "test.direct",
                durable = "true",
                type = "direct",
                ignoreDeclarationExceptions = "true"),
                key = "directKey"
    ))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
        System.err.println("--------------------------------------");
        System.err.println("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}
