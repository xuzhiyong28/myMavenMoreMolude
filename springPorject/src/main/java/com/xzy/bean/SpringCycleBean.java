package com.xzy.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class SpringCycleBean implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
    private BeanFactory factory;
    private String beanName;
    private String phone;
    private String address;
    @Autowired
    public BeanA beanA;

    @Resource
    public BeanService beanService;



    public SpringCycleBean(){

    }

    public SpringCycleBean( String phone , String address){
        this.phone = phone;
        this.address = address;
    }


    public void doSing(){
        System.out.println(beanService.doSing());
    }


    public void initMethod(){
        System.out.println("===========自定义初始化方法==========");
    }



    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware == setBeanFactory");
        this.factory = beanFactory;
    }

    public void setBeanName(String s) {
        System.out.println("BeanNameAware == setBeanName");
        this.beanName = s;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean == afterPropertiesSet");
    }

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
