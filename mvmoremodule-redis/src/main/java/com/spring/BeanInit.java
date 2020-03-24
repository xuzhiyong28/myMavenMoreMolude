package com.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class BeanInit {
    public static void main(String[] args){
        ClassPathResource resource = new ClassPathResource("bean.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //BeanDefinitionReader 读取、解析 Resource 资源，也就是将用户定义的 Bean 表示成 IOC 容器的内部数据结构
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        factory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //第一次从容器中获取时候触发实例化，使用BeanFactory时候才需要这样，如果是Application的话加载的时候就实例化了
        BeanModel beanModel = (BeanModel)factory.getBean("beanModel");

    }
}
