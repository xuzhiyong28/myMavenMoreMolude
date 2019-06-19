package com.xzy.conf;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.UUID;

/**
 * @author xuzhiyong
 * @createDate 2019-06-15-11:32
 */
@Configuration
@EnableRabbit
public class RabbitConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    //配置消费者监听的容器
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }


    //简单消息监听容器
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        //当前的消费者数量
        container.setConcurrentConsumers(1);
        //最大的消费者数量
        container.setMaxConcurrentConsumers(5);
        container.setDefaultRequeueRejected(false);
        //设置自动签收
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String queue) {
                return queue + "_" + UUID.randomUUID().toString();
            }
        });
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String queue) {
                return queue + "_" + UUID.randomUUID().toString();
            }
        });

        //设置需要监听的队列
        //container.setQueueNames("test.direct.queue", "test.topic.queue", "test.fanout.queue");
        //设置消息监听
        /*container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String msg = new String(message.getBody(),"UTF-8");
                System.err.println("-----消费者:" + msg);
            }
        });*/

        //消息监听的适配器
       /* MessageListenerAdapter adaper = new MessageListenerAdapter(new MessageDelegate());
        container.setMessageListener(adaper);*/


        /*
        //可以设置适配器监听的具体方法的名字
        MessageListenerAdapter adaper = new MessageListenerAdapter(new MessageDelegate());
        adaper.setDefaultListenerMethod("consumeMessage");
        container.setMessageListener(adaper);
        */

        /*
        //可以添加一个转换器
        MessageListenerAdapter adaper = new MessageListenerAdapter(new MessageDelegate());
        adaper.setDefaultListenerMethod("consumeMessage");
        adaper.setMessageConverter(new TextMessageConverter());
        container.setMessageListener(adaper);
        */


        //可以指定哪个队列对应那个监听的方法
        /*MessageListenerAdapter adaper = new MessageListenerAdapter(new MessageDelegate());
        adaper.setMessageConverter(new TextMessageConverter());
        Map<String,String> queueOrTagToMethodName = Maps.newHashMap();
        queueOrTagToMethodName.put("test.direct.queue","method1");
        adaper.setQueueOrTagToMethodName(queueOrTagToMethodName);
        container.setMessageListener(adaper);*/

        //支持json转换器
        /*MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        adapter.setDefaultListenerMethod("consumeMessage");
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        adapter.setMessageConverter(jackson2JsonMessageConverter);
        container.setMessageListener(adapter);*/
        return container;
    }




}
