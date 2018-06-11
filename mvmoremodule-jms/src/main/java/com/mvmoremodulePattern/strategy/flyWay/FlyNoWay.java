package com.mvmoremodulePattern.strategy.flyWay;/**
 * Created by Administrator on 2018-04-28.
 */

import com.mvmoremodulePattern.strategy.Flyable;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-19:27
 */
public class FlyNoWay implements Flyable {
    @Override
    public void fly() {
        System.out.println("不能飞行");
    }
}
