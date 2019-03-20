package com.mvmoremodulePattern.decorator;/**
 * Created by Administrator on 2018-05-14.
 */

/**
 * 装饰器1
 * @author xuzhiyong
 * @createDate 2018-05-14-9:15
 * 装饰器
 * 它必须具有一个装饰的对象。
 * 它必须拥有与被装饰对象相同的接口。
 * 它可以给被装饰对象添加额外的功能。
 */
public class Decorator1 implements Sourcable {

    private Sourcable sourcable;

    public Decorator1(Sourcable sourcable) {
        this.sourcable = sourcable;
    }


    @Override
    public void operation() {
        System.out.println("第1个装饰器前");
        sourcable.operation();
        System.out.println("第1个装饰器后");
    }

    public static void main(String agrs[]){
        Sourcable sourcable = new SourcableImpl();
        Sourcable obj = new Decorator1(new Decorator2(sourcable));
        obj.operation();
    }

}
