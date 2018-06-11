package com.mvmoremodulePattern.observer;/**
 * Created by Administrator on 2018-04-28.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-15:44
 */
public class Main {
    public static void main(String agrs[]){
        WechatServer wechatServer = new WechatServer();
        Observer user = new User();
        Observer radio = new Radio();
        wechatServer.registerObserver(user);
        wechatServer.registerObserver(radio);
        wechatServer.setInfomation("啊哈哈");
    }
}
