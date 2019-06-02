package com.xzy.rabbitmq.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;

/**
 * @author xuzhiyong
 * @createDate 2019-06-02-22:06
 */
@Configuration
public class DirectExchangeConfig {

    /***
     * 声明一个交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        DirectExchange directExchange = new DirectExchange("spring_direct");
        return directExchange;
    }

    @Bean
    public Queue directQueue1(){
        Queue queue = new Queue("directqueue1");
        return queue;
    }

    @Bean
    public Queue directQueue2(){
        Queue queue = new Queue("directqueue2");
        return queue;
    }


    /***
     * 队列directqueue1绑定交换机spring_direct
     * @return
     */
    @Bean
    public Binding bindingOrange(){
        Binding binding = BindingBuilder.bind(directQueue1()).to(directExchange()).with("orange");
        return binding;
    }

    @Bean
    public Binding bindingblack(){
        Binding binding = BindingBuilder.bind(directQueue2()).to(directExchange()).with("black");
        return binding;
    }


}
