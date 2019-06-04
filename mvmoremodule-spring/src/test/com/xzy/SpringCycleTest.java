package com.xzy;

import com.xzy.spring.SpringCycleBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCycleTest {
    @Test
    public void test(){
        ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringCycleBean springCycleBean = (SpringCycleBean)factory.getBean("springCycleBean");
        System.out.println("phone = " + springCycleBean.getPhone());
    }
}
