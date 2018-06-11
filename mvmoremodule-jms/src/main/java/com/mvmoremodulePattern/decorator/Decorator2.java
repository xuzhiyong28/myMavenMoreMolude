package com.mvmoremodulePattern.decorator;/**
 * Created by Administrator on 2018-05-14.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-14-9:21
 */
public class Decorator2 implements Sourcable {
    private Sourcable sourcable;

    public Decorator2(Sourcable sourcable) {
        this.sourcable = sourcable;
    }

    @Override
    public void operation() {
        System.out.println("第2个装饰器前");
        sourcable.operation();
        System.out.println("第2个装饰器后");
    }


    public static void main(String[] args){
        Sourcable sourcable = new SourcableImpl(); //具体的类
        Decorator2 decorator2 = new Decorator2(sourcable); //装饰器来装饰具体的类
        decorator2.operation();
    }
}
