package com.mvmoremodulePattern.factory.simplefactory;/**
 * Created by Administrator on 2018-04-26.
 */


import com.mvmoremodulePattern.factory.Pizza;

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-13:41
 * 简单工厂模式的优点：
   模式的核心是工厂类。这个类含有必要的判断逻辑，可以决定在什么时候创建哪一个产品类的实例。
   而客户端则可以免除直接创建对象的责任（比如那个服务员）。简单工厂模式通过这种做法实现了对责任的分割。
   简单工厂模式的缺点：
   这个工厂类集中了所以的创建逻辑，当有复杂的多层次等级结构时，所有的业务逻辑都在这个工厂类中实现。
   什么时候它不能工作了，整个系统都会受到影响。并且简单工厂模式违背了开闭原则（对扩展的开放，对修改的关闭）。
 */
public class SimpleFactory {
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if("greekPizza".equals(type)) pizza = new GreekPizza();
        if("cheesePizza".equals(type)) pizza = new CheesePizza();
        return pizza;
    }
}
