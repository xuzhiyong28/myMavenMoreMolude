package com.mvmoremodulePattern.observer;/**
 * Created by Administrator on 2018-04-28.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-15:43
 */
public class Radio implements Observer {
    @Override
    public void update(String message) {
        System.out.println("收音机接收到信息:" + message);
    }
}
