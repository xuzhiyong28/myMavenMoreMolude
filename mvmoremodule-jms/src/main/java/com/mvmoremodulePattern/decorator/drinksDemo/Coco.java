package com.mvmoremodulePattern.decorator.drinksDemo;/**
 * Created by Administrator on 2018-06-10.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-10-10:49
 */
public class Coco implements BeverageComponent {
    @Override
    public double cost(double price) {
        System.out.println("一杯可可的价格是:" + price);
        return price;
    }
}
