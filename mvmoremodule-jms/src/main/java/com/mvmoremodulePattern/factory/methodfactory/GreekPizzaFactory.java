package com.mvmoremodulePattern.factory.methodfactory;/**
 * Created by Administrator on 2018-04-26.
 */


import com.mvmoremodulePattern.factory.Pizza;

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-13:46
 * 具体工厂角色：担任这个角色的是实现了抽象工厂接口的具体Java类，具体工厂角色含有与应用密切相关的逻辑，并且受到应用程序的调用以创建产品对象
 */
public class GreekPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza() {
        return new GreekPizza();
    }
}
