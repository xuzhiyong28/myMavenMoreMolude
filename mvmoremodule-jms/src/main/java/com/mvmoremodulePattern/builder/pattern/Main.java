package com.mvmoremodulePattern.builder.pattern;/**
 * Created by Administrator on 2018-04-28.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-9:13
 */
public class Main {

    public static void main(String agrs[]){
        HotDryNoodlesWithBuilder hotDryNoodlesWithBuilderA = new Builder().withChili().withParsley().build();
        System.out.println("Customer C wants: " + hotDryNoodlesWithBuilderA);
    }
}
