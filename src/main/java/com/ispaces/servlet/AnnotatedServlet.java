package com.ispaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet(

    name = "AnnotatedServlet"
    
    , asyncSupported = true
    
    , urlPatterns = {
          "/annotated"
      	, "/annotated/"
    }

)
public class AnnotatedServlet extends HttpServlet {
 
    private static final Logger logger = LogManager.getLogger();
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("doGet(request, response)");
 
		    PrintWriter printWriter = response.getWriter();

		    printWriter.println(getClass().getName());
 
    }

}