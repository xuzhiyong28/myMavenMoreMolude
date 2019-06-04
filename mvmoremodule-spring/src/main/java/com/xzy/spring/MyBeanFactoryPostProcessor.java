package com.xzy.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Iterator;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        super();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor == postProcessBeanFactory");
        BeanDefinition bd = configurableListableBeanFactory.getBeanDefinition("springCycleBean");
        bd.getPropertyValues().addPropertyValue("phone","110");
        System.out.println("=================输出所有加载的Bean=====================");
        Iterator<String> allBeanName = configurableListableBeanFactory.getBeanNamesIterator();
        while (allBeanName.hasNext()){
            System.out.println(allBeanName.next() + "");
        }
    }
}
