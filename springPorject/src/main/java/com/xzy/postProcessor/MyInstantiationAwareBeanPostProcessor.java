package com.xzy.postProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    //实例化bean前调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("== postProcessBeforeInstantiation -- 实例化bean前调用, beanName =" + beanName);
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    //实例化bean后调用
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("== postProcessAfterInstantiation -- 实例化bean后调用 ,beanName =" + beanName);
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    //初始化前调用
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("== postProcessBeforeInitialization -- 初始化前调用 ,beanName =" + beanName);
        return super.postProcessBeforeInitialization(bean, beanName);
    }

    //初始化后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("== postProcessAfterInitialization -- 初始化后调用 , beanName = "  + beanName);
        return super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return super.postProcessProperties(pvs, bean, beanName);
    }
}
