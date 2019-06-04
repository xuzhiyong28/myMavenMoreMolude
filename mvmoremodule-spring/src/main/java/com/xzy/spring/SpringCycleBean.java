package com.xzy.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class SpringCycleBean implements BeanFactoryAware,BeanNameAware,InitializingBean,DisposableBean {
    private BeanFactory factory;
    private String beanName;

    private String phone;
    private String address;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware == setBeanFactory");
        this.factory = beanFactory;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware == setBeanName");
        this.beanName = s;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean == afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean == destroy");
    }

    public BeanFactory getFactory() {
        return factory;
    }

    public void setFactory(BeanFactory factory) {
        this.factory = factory;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
