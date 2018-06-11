package com.mvmoremodulePattern.strategy.callble;/**
 * Created by Administrator on 2018-04-29.
 */

import com.mvmoremodulePattern.strategy.Callable;

/**
 * @author xuzhiyong
 * @createDate 2018-04-29-9:30
 */
public class BigCall implements Callable {
    @Override
    public void call() {
        System.out.println("大声叫...");
    }
}
