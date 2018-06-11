package com.mvmoremodulePattern.factory.abstractFactory;/**
 * Created by Administrator on 2018-04-26.
 */


import com.mvmoremodulePattern.factory.Pizza;

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-13:40
 */
public class CheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("准备CheesePizza~");
    }

    @Override
    public void bake() {
        System.out.println("正在烤CheesePizza~");
    }

    @Override
    public void cut() {
        System.out.println("正在切CheesePizza~");
    }

    @Override
    public void box() {
        System.out.println("正在打包CheesePizza~");
    }
}
