package com.mvmoremodulePattern.handler.handlerTwo;/**
 * Created by Administrator on 2018-05-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-9:00
 * 非纯职责链:职责定义在职责链上
 */
public interface HanderChain {
    public void doHandler(float discount);
}
