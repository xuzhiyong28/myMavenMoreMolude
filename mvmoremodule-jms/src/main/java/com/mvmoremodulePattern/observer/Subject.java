package com.mvmoremodulePattern.observer;

/**
 * Created by Administrator on 2018-04-28.
 * 抽象主题
 */
public interface Subject {
    public void registerObserver(Observer o); //注册主题
    public void removeObserver(Observer o); //删除主题
    public void notifyObserver(); //通知主题
}
