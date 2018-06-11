package com.mvmoremodulePattern.handler.handlerOne;/**
 * Created by Administrator on 2018-05-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-8:45
 * 纯职责: 职责接口，每个职责都有一个直接后继。后继由实现类直接指定
 */
public abstract class Handler {
    protected  Handler successor; //后继

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handler(float discount);
}
