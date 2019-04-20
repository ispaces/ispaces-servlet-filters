package com.ispaces.servlet.filter;

import java.io.IOException;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.DispatcherType;
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

/*
@WebFilter { 
	description="";               // Descriptive text about the servlet.
	WebInitParam[] initParams={}, // Used to pass filter config parameters.
	filterName="",                // Name of the filter.
	[] servletNames={},           // Array of Servlet names to which this Filter applies.
	[] urlPatterns={},            // Used for multiple URL and other attributes set (Required).
	DispatcherType[] dispatcherTypes=   
     {DispatcherType.REQUEST},  // Look at table below. 
	boolean asyncSupported=false
}   // Specifies asynchronous processing or not.

Possible dispatcher types:
REQUEST: 	Only when the request comes directly from the client. (DEFAULT)
ASYNC:   	Only when the asynchronous request comes from the client
FORWARD: 	Only when the request has been forwarded to a component 
INCLUDE: 	Only when the request is being processed by a component that has been included
ERROR:   	Only when the request is being processed with the error page mechanism
*/

@WebFilter(
	initParams = {
  		@WebInitParam(name = "param1", value = "value1"),
  		@WebInitParam(name = "param2", value = "value2")
	}
	//, servletNames = { "HelloWorldServlet" }
	, urlPatterns = { "/helloworld" }
	, dispatcherTypes = { DispatcherType.REQUEST }
	, asyncSupported = false
)
public class AnnotatedFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private ServletContext servletContext;

    private int x = 0;

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