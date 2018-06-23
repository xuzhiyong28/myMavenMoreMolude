package com.mvmoremodulePattern.adapter.springmvc;/**
 * Created by Administrator on 2018-06-18.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-18-16:55
 * 定义一个Adapter接口
 */
public interface HandlerAdapter {
    public boolean supports(Object handler);
    public void handle(Object handler);
}
