package com.ispaces.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebFilter(

    initParams = {
        @WebInitParam(name = "customTokenName", value = "CSRF_TOKEN")
    }
    , urlPatterns = { "/authenticate", "/helloworld" }
    , dispatcherTypes = { DispatcherType.REQUEST }
    , asyncSupported = false
)
public class CustomTokenAuthenticationFilter implements Filter {

    Logger logger = LogManager.getLogger();

    FilterConfig filterConfig;
    ServletContext servletContext;
    String customTokenName = null;

    public void init(FilterConfig filterConfig) {
        logger.debug("init(filterConfig:"+filterConfig+")");

        this.filterConfig = filterConfig;
        this.servletContext = filterConfig.getServletContext();  // Save a copy of the servlet context, so we can get the servlet map.

        String customTokenNameInitParam = filterConfig.getInitParameter("customTokenName");
        if (customTokenNameInitParam != null) this.customTokenName = customTokenNameInitParam;

        logger.debug("this.customTokenName = "+this.customTokenName);
    }

    public void destroy() {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("doFilter(request, response, filterChain)");

        boolean authenticated = authenticate( (HttpServletRequest) servletRequest );

        logger.debug("authenticated = "+(authenticated));

        if(authenticated) {

            filterChain.doFilter(servletRequest, servletResponse);

        } else {

            //HttpSession session = ((HttpServletRequest)servletRequest).getSession(false);
            //if(session != null) session.invalidate();
            //((HttpServletResponse)servletResponse).sendRedirect("/login");
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_NOT_FOUND, "Custom Security Token Missing from Request");
        }

    }

    public boolean authenticate(HttpServletRequest request) throws IOException, ServletException {
        logger.debug("authenticate(request)");

        HttpSession session = request.getSession(false);
        logger.debug("session = "+session);

        if(session != null) {

            //String csrfToken = (String)session.getAttribute(customTokenName);
            String customSessionToken = (String)session.getAttribute(customTokenName);
            logger.debug("customSessionToken = "+customSessionToken);

            if(customSessionToken != null) {

                String customHeaderToken = request.getHeader(customTokenName);

                if(customHeaderToken == null) customHeaderToken = request.getParameter(customTokenName);  // If the hash does not exists in the headers, check the parameters.
                logger.debug("customHeaderToken = "+ customHeaderToken);

                if(customSessionToken.equals(customHeaderToken)) return true;
            }
        }

        return false;
    }

}
