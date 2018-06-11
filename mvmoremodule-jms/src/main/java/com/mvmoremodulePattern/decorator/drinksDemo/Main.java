package com.mvmoremodulePattern.decorator.drinksDemo;/**
 * Created by Administrator on 2018-06-10.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-10-11:07
 * 本来一杯可可的价格是100， 通过加入摩卡 加入糖的装饰 就变了价格，最后输出最后的价格。如果有第二种饮料也一样的方式去做
 */
public class Main {
    public static void main(String agrs[]){
        Coco coco = new Coco();
        Mocha mocha = new Mocha(coco);
        Sugar sugar = new Sugar(mocha);
        System.out.println("最终价格:" + sugar.cost(100));
    }
}
