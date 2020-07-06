package com.xzy.annotation;

import com.xzy.bean.BeanA;
import com.xzy.bean.SpringCycleBean;
import com.xzy.postProcessor.MyInstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.xzy.annotation.bean")
public class AppConfig {

    @Bean
    public MyInstantiationAwareBeanPostProcessor myInstantiationAwareBeanPostProcessor(){
        return new MyInstantiationAwareBeanPostProcessor();
    }

    @Bean
    public SpringCycleBean springCycleBean(){
        SpringCycleBean springCycleBean = new SpringCycleBean();
        springCycleBean.setAddress("wangsu");
        springCycleBean.setPhone("123");
        return springCycleBean;
    }

    @Bean
    public BeanA beanA(){
        return new BeanA();
    }


}
