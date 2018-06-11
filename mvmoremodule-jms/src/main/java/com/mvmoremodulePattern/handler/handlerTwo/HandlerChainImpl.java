package com.mvmoremodulePattern.handler.handlerTwo;/**
 * Created by Administrator on 2018-05-08.
 */

import java.util.Iterator;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-05-08-9:02
 */
public class HandlerChainImpl implements HanderChain {

    private Iterator<Handler> iterator;
    public HandlerChainImpl(List<Handler> list){
        this.iterator = list.iterator();
    }

    @Override
    public void doHandler(float discount) {
        if(iterator.hasNext()){
            Handler handler = iterator.next();
            handler.handler(discount,this);
        }
    }
}
