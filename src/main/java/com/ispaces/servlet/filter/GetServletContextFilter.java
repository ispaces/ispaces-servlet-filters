package com.ispaces.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class GetServletContextFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private ServletContext servletContext;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		logger.debug("doFilter(servletRequest, servletResponse, filterChain)");

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("init("+filterConfig+")");

		this.servletContext = filterConfig.getServletContext();
		logger.debug("this.servletContext = " + this.servletContext);
	}

	public void destroy() {
		// nothing todo
	}

}