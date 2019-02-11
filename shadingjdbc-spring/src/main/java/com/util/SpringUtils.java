package com.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
    public static Object getBean(String name){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml","classpath:applicationContext-database.xml","classpath:applicationContext-sharding.xml");
        return applicationContext.getBean(name);
    }

    public static Object getBeanMaster(String name){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml","classpath:applicationContext-database-master-slave.xml","classpath:applicationContext-sharding-master-slave.xml");
        return applicationContext.getBean(name);
    }
}
