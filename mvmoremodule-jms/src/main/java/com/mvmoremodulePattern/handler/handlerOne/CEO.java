package com.mvmoremodulePattern.handler.handlerOne;/**
 * Created by Administrator on 2018-05-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-8:50
 */
public class CEO extends Handler {
    @Override
    public void handler(float discount) {
        if (discount < 1) {
            System.out.println("CEO批准折扣:" + discount);
        } else {
            System.out.println("不批准");
        }
    }
}
