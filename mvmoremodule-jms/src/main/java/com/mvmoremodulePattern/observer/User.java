package com.mvmoremodulePattern.observer;/**
 * Created by Administrator on 2018-04-28.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-15:42
 * 具体观察者
 */
public class User implements Observer {
    @Override
    public void update(String message) {
        System.out.println("用户接收到信息:" + message);
    }
}
