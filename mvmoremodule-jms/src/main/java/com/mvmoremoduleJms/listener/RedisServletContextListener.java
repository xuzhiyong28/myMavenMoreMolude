package com.mvmoremoduleJms.listener;/**
 * Created by Administrator on 2018-04-09.
 */

import com.mvmoremoduleJms.supper.RedisClient;
import com.mvmoremoduleJms.supper.SubThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author xuzhiyong
 * @createDate 2018-04-09-10:00
 */
public class RedisServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //web启动时触发
        System.out.println("RedisServletContextListener...启动");
        RedisClient.initJedisPool(); //初始化redis池
        new SubThread().start(); //启动redis订阅发布线程
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //web销毁时触发
        System.out.println("RedisServletContextListener...销毁");
    }
}
