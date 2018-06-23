package com.mvmoremodulePattern.adapter.springmvc;/**
 * Created by Administrator on 2018-06-18.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-18-17:15
 */
public class AnnotationHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof AnnotationController);
    }

    @Override
    public void handle(Object handler) {
        ((AnnotationController)handler).doAnnotationHandler();
    }
}
