package com.mvmoremodulePattern.strategy;/**
 * Created by Administrator on 2018-04-28.
 */

import com.mvmoremodulePattern.strategy.callble.BigCall;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-19:20
 */
public abstract class Duck {
    protected Flyable flyable;
    protected Callable callable;


    public void performFly(){
        flyable.fly();
    }
    public void performCall(){
        callable.call();
    }

    /***
     * 这里为什么不采用鸭子实现飞的接口，因为假如有10种鸭子都会飞，但是其中9钟飞行方式是一样的，那9种鸭子都要实现飞的接口，代码就重复写了好多次
     * 这里采用策略模式的方式，将可以变化的方法抽取成接口，并通过组合的方式放到父类中，子类通过插入不同的接口实现，完成类的配置。
     * @param flayable
     */
    public void setFlayable(Flyable flayable){
        this.flyable = flayable;
    }

    public void setCallable(Callable callable){
        this.callable = callable;
    }

    public void swim(){
        System.out.println("鸭子都TM会有用");
    }

    public abstract void display(); // 每只鸭子的外貌都不一样，但都必须要有外貌 所以抽象成方法来重写

}
