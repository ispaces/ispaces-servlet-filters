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
	String description = "",
	WebInitParam[] initParams = {},
	String filterName = "",
	String[] servletNames = {},
	String[] urlPatterns = {},
	DispatcherType[] dispatcherTypes = {DispatcherType.REQUEST},
	boolean asyncSupported = false
}

Possible dispatcher types:
	REQUEST: 	A standard HTTP request
	ASYNC:   	An asynchronous HTTP request
	FORWARD: 	A request that has been forwarded
	INCLUDE: 	A request that has been included
	ERROR:   	A request processed with the error page mechanism
*/

@WebFilter(
	initParams = {
  		@WebInitParam(name = "param1", value = "value1"),
  		@WebInitParam(name = "param2", value = "value2")
	}
	//, servletNames = { "HelloWorldServlet" }
	, urlPatterns = { "/annotated" }
	, dispatcherTypes = { DispatcherType.REQUEST }
	, asyncSupported = false
)
public class AnnotatedFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private ServletContext servletContext;

    private int x = 0;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		logger.debug("doFilter(servletRequest, servletResponse, filterChain)");

		servletResponse.getWriter().println(getClass().getName());

		filterChain.doFilter(servletRequest, servletResponse);
		
		servletResponse.getWriter().println(getClass().getName());
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