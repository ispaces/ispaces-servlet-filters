package com.ispaces.servlet.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * This filter is used to output the request and response headers.
 *
 * @author  Dermot Doherty
 */

public class DebugHeadersFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("init("+filterConfig+")");

        this.filterConfig = filterConfig; // Set a reference to the FilterConfig object.
    }

    public void destroy() {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        logger.debug("doFilter(request, response, chain)");

        logger.debug("this.filterConfig.getFilterName() = "+this.filterConfig.getFilterName());

        if(servletRequest instanceof HttpServletRequest) {

            HttpServletRequest request = (HttpServletRequest)servletRequest;

            String name = null;
            Enumeration e = request.getHeaderNames();
            if(e.hasMoreElements()) {
                logger.debug("");
                logger.debug("Request Headers:");
                while(e.hasMoreElements()) {
                    name = (String)e.nextElement();
                    logger.debug("    '"+name+"' = '"+request.getHeader(name));
                }
            }

        }

        chain.doFilter(servletRequest, servletResponse);

        if(servletResponse instanceof HttpServletResponse) {

            HttpServletResponse response = (HttpServletResponse)servletResponse;

            java.util.Collection<java.lang.String> headerNames = response.getHeaderNames();
            Iterator<String> iterator = headerNames.iterator();
            logger.debug("");
            logger.debug("Response Headers:");
            while(iterator.hasNext()) {
                String headerName = iterator.next();
                String headerValue = response.getHeader(headerName);
                logger.debug(headerName+" = "+headerValue);
            }
        }

    }

}
