package com.mvmoremodulePattern.decorator.drinksDemo;/**
 * Created by Administrator on 2018-06-10.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-10-11:07
 */
public class Sugar extends Decorator {
    public Sugar(BeverageComponent beverageComponent){
        this.beverageComponent = beverageComponent;
    }
    @Override
    public double cost(double price) {
        double sugarPrice = beverageComponent.cost(price) + 200;
        System.out.println("饮料加入摩卡后的价格变成了:" + sugarPrice);
        return sugarPrice;
    }
}
