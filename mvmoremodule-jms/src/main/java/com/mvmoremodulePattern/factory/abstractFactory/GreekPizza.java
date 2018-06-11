package com.mvmoremodulePattern.factory.abstractFactory;/**
 * Created by Administrator on 2018-04-26.
 */


import com.mvmoremodulePattern.factory.Pizza;

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-13:39
 */
public class GreekPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("准备GreekPizza~");
    }

    @Override
    public void bake() {
        System.out.println("正在烤GreekPizza~");
    }

    @Override
    public void cut() {
        System.out.println("正在切GreekPizza~");
    }

    @Override
    public void box() {
        System.out.println("正在打包GreekPizza~");
    }
}
