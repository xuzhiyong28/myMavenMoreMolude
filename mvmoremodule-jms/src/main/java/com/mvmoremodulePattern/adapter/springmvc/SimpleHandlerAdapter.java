package com.mvmoremodulePattern.adapter.springmvc;/**
 * Created by Administrator on 2018-06-18.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-18-17:10
 */
public class SimpleHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof SimpleController);
    }

    @Override
    public void handle(Object handler) {
        ((SimpleController)handler).doSimplerHandler();
    }
}
