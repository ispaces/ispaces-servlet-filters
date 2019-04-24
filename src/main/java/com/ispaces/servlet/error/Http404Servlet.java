package com.ispaces.servlet.error;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.annotation.WebServlet;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@WebServlet(
      name = "Http404Servlet"
    //, asyncSupported = true
    , urlPatterns = {
          "/error/404"
        , "/error/404/*"
    }
)
public class Http404Servlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    public void init(ServletConfig config) throws ServletException {
        logger.debug("init("+config+")");

        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("doGet(request, response)");

        logger.debug("request.getHeader('Host')   = " + request.getHeader("Host"));
        logger.debug("request.getRequestURL()     = " + request.getRequestURL());
        logger.debug("request.getRequestURI()     = " + request.getRequestURI());
        logger.debug("request.getDispatcherType() = " + request.getDispatcherType());
        logger.debug("request.getScheme()         = " + request.getScheme());
        logger.debug("request.getServerName()     = " + request.getServerName());
        logger.debug("request.getServerPort()     = " + request.getServerPort());
        logger.debug("request.getContextPath()    = " + request.getContextPath());
        logger.debug("request.getPathInfo()       = " + request.getPathInfo());
        logger.debug("request.getLocale()         = " + request.getLocale());
        logger.debug("request.getQueryString()    = " + request.getQueryString());
        logger.debug("request.getContentLength()  = " + request.getContentLength());
        logger.debug("request.getRemoteAddr()     = " + request.getRemoteAddr());
        logger.debug("request.getRemoteHost()     = " + request.getRemoteHost());
        logger.debug("request.getRemoteUser()     = " + request.getRemoteUser());
        logger.debug("request.getLocalAddr()      = " + request.getLocalAddr());
        logger.debug("request.getLocalName()      = " + request.getLocalName());

        try {

            httpRequest(request, response);

        } catch(Exception e) {

            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.error(stringWriter.toString());
        }

    }

    public void httpRequest(
        HttpServletRequest request
      , HttpServletResponse response
    ) throws IOException, ServletException {

        logger.debug("httpRequest(request, response)");

        logger.debug("request.getDispatcherType() = " + request.getDispatcherType());

        logger.debug("javax.servlet.error.exception = " + request.getAttribute("javax.servlet.error.exception")); // the exception instance that caused the error (or null)
        logger.debug("javax.servlet.error.exception_type = " + request.getAttribute("javax.servlet.error.exception_type")); // the class name of the exception instance that caused the error (or null)
        logger.debug("javax.servlet.error.message = " + request.getAttribute("javax.servlet.error.message")); // the error message
        logger.debug("javax.servlet.error.request_uri = " + request.getAttribute("javax.servlet.error.request_uri")); // the URI of the request that caused the error
        logger.debug("javax.servlet.error.servlet_name = " + request.getAttribute("javax.servlet.error.servlet_name")); // the name of the servlet that the request was dispatched to
        logger.debug("javax.servlet.error.status_code = " + request.getAttribute("javax.servlet.error.status_code")); // the HTTP status code of the error

        try {

            response.setContentType("text/html");
            //response.setCharacterEncoding("UTF-8");

            response.getWriter().println("<!doctype html>");

            response.getWriter().println("<html>");

            StringBuilder headBuilder = new StringBuilder();

            headBuilder.append("<head>");
            headBuilder.append("<style>");
            headBuilder.append("pre {");
            headBuilder.append("background-color: #eee;");
            headBuilder.append("border: solid 1px blue; ");
            headBuilder.append("font-size: 1.3 em; ");
            headBuilder.append("color: blue; ");
            headBuilder.append("margin: 10px; ");
            headBuilder.append("padding: 10px; ");
            headBuilder.append("}");
            headBuilder.append("code {");
            headBuilder.append("font-size: 1.2em; color: #008099;");
            headBuilder.append("}");
            headBuilder.append("pre, code {");
            headBuilder.append("direction: ltr; text-align: left;");
            headBuilder.append("}");
            headBuilder.append("</style>");
            headBuilder.append("</head>");

            response.getWriter().println(headBuilder.toString());
            response.getWriter().println("<body>");

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("# HTTP 404 Not Found\n\n");
            //response.getWriter().printf("Dispatcher Type: %s%n", request.getDispatcherType());
            stringBuilder.append("\n\nDispatcher Type: **" + request.getDispatcherType()+"**");
            stringBuilder.append("\n\n## Request Attributes:");
            stringBuilder.append("\n\n  ");
            stringBuilder.append("```");
            stringBuilder.append("\njavax.servlet.error.exception = " + request.getAttribute("javax.servlet.error.exception"));
            stringBuilder.append("\njavax.servlet.error.exception_type = " + request.getAttribute("javax.servlet.error.exception_type"));
            stringBuilder.append("\njavax.servlet.error.message = " + request.getAttribute("javax.servlet.error.message"));
            stringBuilder.append("\njavax.servlet.error.request_uri = " + request.getAttribute("javax.servlet.error.request_uri"));
            stringBuilder.append("\njavax.servlet.error.servlet_name = " + request.getAttribute("javax.servlet.error.servlet_name"));
            stringBuilder.append("\njavax.servlet.error.status_code = " + request.getAttribute("javax.servlet.error.status_code"));
            stringBuilder.append("\n```");


            Parser parser = Parser.builder().build();
            Node document = parser.parse(stringBuilder.toString());
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String bodyHtml = renderer.render(document);

            response.getWriter().print(bodyHtml);

            response.getWriter().println("</body>");
            response.getWriter().println("</html>");

            response.getWriter().flush();

        } catch(Exception e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter( stringWriter );
            e.printStackTrace( printWriter );
            logger.error( stringWriter.toString() );
        }
    }

}
