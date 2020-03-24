package com.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class BeanModel implements BeanFactoryAware, BeanNameAware , InitializingBean, DisposableBean {

    private String name;
    private String age;

    static {
        System.out.println("=====执行static里面的东西=====");
    }


    public BeanModel(){
        System.out.println("=======初始化类实例=====");
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("======name属性注入=====");
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        System.out.println("=====age属性注入=====");
        this.age = age;
    }



    public void myInit(){
        System.out.println("====myInit=====");
    }

    public void myDestory(){
        System.out.println("===myDestory===");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("=====执行 BeanFactoryAware 类的 setBeanFactory 方法====");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("=====执行 BeanNameAware 类的 setBeanName 方法==== 拿到bean的名称：" + name );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("=====执行 InitializingBean 类的 afterPropertiesSet 方法====");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("=====执行 DisposableBean 类的 destroy 方法====");
    }
}
