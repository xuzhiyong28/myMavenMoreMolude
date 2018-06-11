package com.mvmoremodulePattern.handler.handlerOne;/**
 * Created by Administrator on 2018-05-08.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-8:51
 * 职责链的工厂， 负责将职责连接起来并生产
 */
public class HandlerFactory {
    public static Handler createHandler(){
        Saler saler = new Saler();
        Manager manager = new Manager();
        CEO ceo = new CEO();

        saler.setSuccessor(manager);
        manager.setSuccessor(ceo);
        return saler;
    }
}
