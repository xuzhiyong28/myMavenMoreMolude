package com.mvmoremodulePattern.handler.handlerTwo;/**
 * Created by Administrator on 2018-05-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-9:10
 * 处理自己的事，如果职责不是自己的就发给职责链上的成员去处理
 * 具体职责处理者
 */
public class Saler implements  Handler {
    @Override
    public void handler(float discount, HanderChain chain) {
        if (discount < 0.05f) {
            System.out.println("Saler批准折扣:" + discount);
        } else {
            chain.doHandler(discount);
        }
    }
}
