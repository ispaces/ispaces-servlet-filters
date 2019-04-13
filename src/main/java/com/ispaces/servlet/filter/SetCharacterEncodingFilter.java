package com.ispaces.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncodingFilter implements Filter {

    private static String characterEncoding = null;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if(this.characterEncoding != null) request.setCharacterEncoding(this.characterEncoding);

        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        this.characterEncoding = filterConfig.getInitParameter("encoding");
    }

    public void destroy() {
        // nothing to destroy
    }

}
