package com.mvmoremodulePattern.handler.handlerOne;/**
 * Created by Administrator on 2018-05-08.
 */

import java.util.Random;

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-8:53
 */
public class Customer {
    private Handler handler;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void doSomething(float discount){
        handler.handler(discount);
    }

    public static void main(String agrs[]){
        Random random = new Random();
        for(int i = 0 ; i < 10 ; i++){
            Customer customer = new Customer();
            customer.setHandler(HandlerFactory.createHandler());
            customer.doSomething(random.nextFloat());
        }
    }

}
