package com.ispaces.servlet.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BasicFilter implements javax.servlet.Filter {

    public FilterConfig filterConfig;

    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, FilterChain chain) throws java.io.IOException, javax.servlet.ServletException {

        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(final FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

}
