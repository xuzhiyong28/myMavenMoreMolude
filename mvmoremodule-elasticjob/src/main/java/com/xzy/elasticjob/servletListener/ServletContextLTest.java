package com.xzy.elasticjob.servletListener;

import com.xzy.elasticjob.FastDemo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextLTest implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FastDemo.initJob();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
