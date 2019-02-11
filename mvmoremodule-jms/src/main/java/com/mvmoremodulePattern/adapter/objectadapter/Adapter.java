package com.mvmoremodulePattern.adapter.objectadapter;/**
 * Created by Administrator on 2018-04-26.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-15:55
 * 适配器
 */
public class Adapter implements Target{

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void simpleOperation1() {
        adaptee.simpleOperation1();
    }

    @Override
    public void simpleOperation2() {
        System.out.println("simpleOperation2");
    }
}
