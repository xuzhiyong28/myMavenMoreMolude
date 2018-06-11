package com.mvmoremodulePattern.factory.methodfactory;/**
 * Created by Administrator on 2018-04-26.
 */


import com.mvmoremodulePattern.factory.Pizza;

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-13:47
 * 工厂方法模式和简单工厂模式比较：
   工厂方法模式跟简单工厂模式在结构上的不同是很明显的，工厂方法模式的核心是一个抽象工厂类，
   而简单工厂模式的核心在一个具体类。显而易见工厂方法模式这种结构更好扩展，权力下发，分布式比集中式更具优势。

   如果系统需要加入一个新的产品，那么所需要的就是向系统中加入一个这个产品类以及它所对应的工厂类。
   没有必要修改客户端，也没有必要修改抽象工厂角色或者其他已有的具体工厂角色。对于增加新的产品类而言，这个系统完全支持开闭原则
 */
public class Main {
    public static void main(String agrs[]){
        PizzaFactory pizzaFactory = new CheesePizzaFactory();
        Pizza pizza = pizzaFactory.createPizza();
        pizza.bake();
    }
}
