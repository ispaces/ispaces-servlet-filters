package com.ispaces.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CustomServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("activeUsers", new java.util.concurrent.ConcurrentHashMap<String, HttpSession>());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // empty implemenatation
    }
    
}