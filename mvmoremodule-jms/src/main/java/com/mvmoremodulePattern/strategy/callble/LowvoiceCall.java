package com.mvmoremodulePattern.strategy.callble;

import com.mvmoremodulePattern.strategy.Callable;

/**
 * Created by Administrator on 2018-04-29.
 */
public class LowvoiceCall  implements Callable{
    @Override
    public void call() {
        System.out.println("小声叫...");
    }
}
