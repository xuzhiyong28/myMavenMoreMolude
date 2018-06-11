package com.mvmoremodulePattern.observer;/**
 * Created by Administrator on 2018-04-28.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-15:39
 * 具体主题
 */
public class WechatServer implements Subject {
    private List<Observer> list;
    private String message;

    public WechatServer(){
        list = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if(!list.isEmpty())
            list.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(int i = 0 ; i < list.size() ; i++){
            Observer o = list.get(i);
            o.update(message);
        }
    }

    public void setInfomation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        notifyObserver();
    }

}
