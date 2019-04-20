package com.ispaces.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Adds a single header to the response.
 * Configurable in web.xml with the init parameters `headerName` and `headerValue`
 */

public class AddHeaderFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private String headerName = null;
    private String headerValue = null;

    protected FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("init("+filterConfig+")");

        this.filterConfig = filterConfig;

        String headerNameParam = filterConfig.getInitParameter("headerName");
        if (headerNameParam != null) this.headerName = headerNameParam;

        String headerValueParam = filterConfig.getInitParameter("headerValue");
        if (headerValueParam != null) this.headerValue = headerValueParam;

        //logger.debug("this.headerName = "+this.headerName);
        //logger.debug("this.headerValue = "+this.headerValue);
        logger.debug(this.headerName+" = "+this.headerValue);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("doFilter(servletRequest, servletResponse, filterChain)");

        if(
            servletResponse instanceof HttpServletResponse
            && this.headerName != null
        ) {

            logger.debug("servletResponse.addHeader('"+this.headerName+"', '"+this.headerValue+"')");

            ((HttpServletResponse)servletResponse).addHeader(this.headerName, this.headerValue);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        this.filterConfig = null;
    }

}
