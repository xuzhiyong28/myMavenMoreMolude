package com.mvmoremodulePattern.handler.handlerTwo;/**
 * Created by Administrator on 2018-05-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-9:12
 */
public class Manager implements  Handler {
    @Override
    public void handler(float discount, HanderChain chain) {
        if (discount < 0.1f) {
            System.out.println("Saler批准折扣:" + discount);
        } else {
            chain.doHandler(discount);
        }
    }
}
