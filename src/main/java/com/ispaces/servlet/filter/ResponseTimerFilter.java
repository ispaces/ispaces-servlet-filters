package com.ispaces.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * This filter can be used for measuring server response times
 */
public class ResponseTimerFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    //protected FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        // Save the filterConfig object
        //this.filterConfig = filterConfig;
    }

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        long startTimeMillis = System.currentTimeMillis();                      // get the time before processing the request
        chain.doFilter(request, response);                                      // process the request by invoking the next filter/servlet in the chain
        long timeElapsedMillis = System.currentTimeMillis() - startTimeMillis;  // capture the time elapsed

            logger.debug("took " + timeElapsedMillis + " ms");
    }

}
