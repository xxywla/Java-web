package com.xxyw.myssm.listeners;

import com.xxyw.myssm.ioc.BeanFactory;
import com.xxyw.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();

        String path = application.getInitParameter("contextConfigLocation");

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        application.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
