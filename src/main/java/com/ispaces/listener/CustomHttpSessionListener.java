package com.ispaces.listener;

import java.util.HashMap;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class CustomHttpSessionListener implements HttpSessionListener {

    public void init( ServletConfig config ) {
    }

    public void sessionCreated( HttpSessionEvent httpSessionEvent ) {
        HttpSession httpSession = httpSessionEvent.getSession();
        ServletContext servletContext = httpSession.getServletContext();
        HashMap<String, HttpSession> activeUsers = (HashMap<String, HttpSession>) servletContext.getAttribute("activeUsers");
        activeUsers.put( httpSession.getId(), httpSession );
    }

    public void sessionDestroyed( HttpSessionEvent httpSessionEvent ) {
        HttpSession httpSession = httpSessionEvent.getSession();
        ServletContext servletContext = httpSession.getServletContext();
        HashMap<String, HttpSession> activeUsers = (HashMap<String, HttpSession>) servletContext.getAttribute("activeUsers");
        activeUsers.remove( httpSession.getId() );
    }

}