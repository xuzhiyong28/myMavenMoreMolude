package com.mvmoremodulePattern.handler.handlerTwo;/**
 * Created by Administrator on 2018-05-08.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-9:07
 * 职责链工厂
 */
public class HandlerFactory {
    public static HanderChain createHandler(){
        List<Handler> handlers = new ArrayList<Handler>();

        //注意添加职责的顺序
        handlers.add(new Saler());
        handlers.add(new Manager());
        handlers.add(new CEO());

        HandlerChainImpl handlerChain = new HandlerChainImpl(handlers);
        return handlerChain;
    }
}
