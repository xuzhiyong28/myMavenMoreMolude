package com.mvmoremodulePattern.adapter.springmvc;/**
 * Created by Administrator on 2018-06-18.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-18-17:14
 */
public class HttpHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof HttpController);
    }

    @Override
    public void handle(Object handler) {
        ((HttpController)handler).doHttpHandler();
    }
}
