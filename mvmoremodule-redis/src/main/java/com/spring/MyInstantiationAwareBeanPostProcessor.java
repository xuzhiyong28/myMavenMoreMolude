package com.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * @author xuzhiyong
 * @createDate 2020-03-24-21:48
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {


    //实例化bean前调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("== postProcessBeforeInstantiation -- 实例化bean前调用");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    //实例化bean后调用
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("== postProcessAfterInstantiation -- 实例化bean后调用");
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    //初始化前调用
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("== postProcessBeforeInitialization -- 初始化前调用");
        return super.postProcessBeforeInitialization(bean, beanName);
    }

    //初始化后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("== postProcessAfterInitialization -- 初始化后调用");
        return super.postProcessAfterInitialization(bean, beanName);
    }

    /***
     * getBean时 当设置某个属性时调用。自动装配就是通过这个方法进行装配属性
     * @param pvs
     * @param pds
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
