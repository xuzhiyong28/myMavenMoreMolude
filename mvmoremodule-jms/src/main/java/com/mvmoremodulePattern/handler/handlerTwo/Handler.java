package com.mvmoremodulePattern.handler.handlerTwo;/**
 * Created by Administrator on 2018-05-08.
 */


/**
 * @author xuzhiyong
 * @createDate 2018-05-08-9:04
 * 抽象职责处理者
 */
public interface Handler{
    public void handler(float discount , HanderChain chain);
}
