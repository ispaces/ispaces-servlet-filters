package com.ispaces.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * This filter can be used to add a "Content-Type" header to any response.
 * The content type can be configured in `web.xml`
 */
public class SetContentTypeFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    
    private String contentType = null;

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("doFilter(servletRequest, servletResponse, filterChain)");

        ((javax.servlet.http.HttpServletResponse)servletResponse).setContentType(this.contentType);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("init("+filterConfig+")");

        String contentTypeParam = filterConfig.getInitParameter("contentType");
        if (contentTypeParam != null) this.contentType = contentTypeParam;

        logger.debug("this.contentType = "+this.contentType);
    }

    public void destroy() {
    }

}
