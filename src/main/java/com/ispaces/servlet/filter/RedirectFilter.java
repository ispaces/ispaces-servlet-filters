package com.ispaces.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebFilter(
    urlPatterns = { "/docs/*" }

    , initParams = {
        @WebInitParam(name = "/docs/redirect-from.html", value = "/docs/redirect-to.html")
    }

    , dispatcherTypes = { DispatcherType.REQUEST }
    , asyncSupported = false
)


public class RedirectFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    //private ServletConfig servletConfig = null;
    private String contextUrl = null;
    private Map<String, String> redirectMap = null;

    public void init(ServletConfig servletConfig) throws ServletException {
        logger.debug("init("+servletConfig+")");

        //this.servletConfig = servletConfig;
        this.contextUrl = (String) servletConfig.getServletContext().getAttribute("contextUrl");

        logger.debug("this.contextUrl = "+this.contextUrl);

        this.redirectMap = new HashMap<String, String>() {{
            put("/docs/Producers-Market-White-Paper-Q1-2019.pdf", "/docs/Producers-Market-White-Paper.pdf");
        }};
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("doFilter(servletRequest, servletResponse, filterChain)");

        String pathInfo = ((javax.servlet.http.HttpServletRequest) servletRequest).getPathInfo();
        logger.debug("pathInfo = "+pathInfo);

        if(pathInfo != null) {

            String redirectPath = redirectMap.get(pathInfo);
            logger.debug("redirectPath = "+redirectPath);

            if(redirectPath != null) {

                String redirectLocation = new StringBuilder().append(this.contextUrl).append(redirectPath).toString();
                logger.debug("redirectLocation = "+redirectLocation);

                ((javax.servlet.http.HttpServletResponse)servletResponse).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                ((javax.servlet.http.HttpServletResponse)servletResponse).setHeader("Location", redirectLocation);
                ((javax.servlet.http.HttpServletResponse)servletResponse).setHeader("Connection", "close");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
