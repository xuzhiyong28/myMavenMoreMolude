package com.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class BeanModel implements BeanFactoryAware,BeanNameAware,InitializingBean,DisposableBean{

     private String name;
     private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("====BeanFactoryAware===");
        System.out.println(beanFactory);

    }


    @Override
    public void setBeanName(String s) {
        System.out.println("====BeanNameAware===");
        System.out.println(s);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("====InitializingBean===");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("====DisposableBean===");
    }

    public void myInit(){
        System.out.println("===myInit===");
    }

    public void myDestory(){
        System.out.println("===myDestory===");
    }

}
