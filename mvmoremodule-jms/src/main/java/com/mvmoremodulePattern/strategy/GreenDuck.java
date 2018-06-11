package com.mvmoremodulePattern.strategy;/**
 * Created by Administrator on 2018-04-28.
 */

import com.mvmoremodulePattern.strategy.flyWay.FlyNoWay;
import com.mvmoremodulePattern.strategy.flyWay.FlyWithWing;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-19:29
 */
public class GreenDuck extends Duck{
    public GreenDuck(){
        this.flyable = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("我是不会飞行的鸭子，我的外貌就是没翅膀");
    }

    public static void main(String agrs[]){
        GreenDuck greenDuck = new GreenDuck();
        greenDuck.display();
        greenDuck.performFly();
        greenDuck.setFlayable(new FlyWithWing());
        greenDuck.performFly();
    }
}
