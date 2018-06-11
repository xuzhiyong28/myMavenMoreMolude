package com.mvmoremodulePattern.handler.handlerTwo;/**
 * Created by Administrator on 2018-05-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-9:12
 */
public class CEO implements Handler {
    @Override
    public void handler(float discount, HanderChain chain) {
        if (discount < 0.5f) {
            System.out.println("CEO批准折扣:" + discount);
        } else {
            System.out.println("CEO拒绝了折扣:" + discount);
        }
    }
}
