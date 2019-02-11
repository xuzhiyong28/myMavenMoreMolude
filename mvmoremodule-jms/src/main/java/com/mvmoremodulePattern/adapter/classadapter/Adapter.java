package com.mvmoremodulePattern.adapter.classadapter;/**
 * Created by Administrator on 2018-04-26.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-15:52
 * 适配器
 */
public class Adapter extends Adaptee implements Target {
    /**
     * 由于源类没有方法simpleOperation2
     * 因此适配器类补充上这个方法
     */
    @Override
    public void simpleOperation2() {
        System.out.println("simpleOperation2");
    }
}
