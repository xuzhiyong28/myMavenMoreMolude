package com.mvmoremodulePattern.handler.handlerTwo;/**
 * Created by Administrator on 2018-05-08.
 */

import java.util.Random;

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-9:13
 */
public class Customer {
    private HanderChain chain;

    public void setChain(HanderChain chain) {
        this.chain = chain;
    }

    public void doHandler(float discount) {
        chain.doHandler(discount);
    }

    public static void main(String agrs[]) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setChain(HandlerFactory.createHandler());
            customer.doHandler(random.nextFloat());
        }
    }


}
