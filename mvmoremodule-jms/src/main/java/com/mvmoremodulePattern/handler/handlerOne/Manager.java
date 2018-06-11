package com.mvmoremodulePattern.handler.handlerOne;/**
 * Created by Administrator on 2018-05-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-8:49
 */
public class Manager extends Handler {
    @Override
    public void handler(float discount) {
        if (discount < 0.1f) {
            System.out.println("Manager批准折扣:" + discount);
        } else {
            successor.handler(discount); //交给后继进行处理
        }
    }
}
