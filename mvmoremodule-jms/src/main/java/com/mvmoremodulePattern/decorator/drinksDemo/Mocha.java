package com.mvmoremodulePattern.decorator.drinksDemo;/**
 * Created by Administrator on 2018-06-10.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-10-10:55
 */
public class Mocha extends Decorator {

    public Mocha(BeverageComponent beverageComponent){
        this.beverageComponent = beverageComponent;
    }

    @Override
    public double cost(double price) {
        double mochaPrice = beverageComponent.cost(price) + 100;
        System.out.println("饮料加入摩卡后的价格变成了:" + mochaPrice);
        return mochaPrice;
    }
}
