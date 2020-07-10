package com.xzy.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            // spring内部事件
            System.out.println("监听到上下文刷新时间！");
        }
        else if(event instanceof ContextClosedEvent){
            System.out.println("监听到上下文关闭！");
        }
        else if (event instanceof MyApplicationEvent) {
            System.out.println("监听到myApplicationEvent！");
        } else {
            System.out.println("有其它事件发生:" + event.getClass().getName());
        }
    }
}
