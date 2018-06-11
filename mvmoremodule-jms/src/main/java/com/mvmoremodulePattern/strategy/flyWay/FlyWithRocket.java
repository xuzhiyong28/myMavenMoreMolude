package com.mvmoremodulePattern.strategy.flyWay;/**
 * Created by Administrator on 2018-04-28.
 */

import com.mvmoremodulePattern.strategy.Flyable;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-19:28
 */
public class FlyWithRocket implements Flyable {
    @Override
    public void fly() {
        System.out.println("用火箭飞行");
    }
}
