package com.ispaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
 
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(

    name = "HeadersServlet"
    
    , asyncSupported = true
    
    , urlPatterns = {
          "/headers"
      	, "/headers/"
    }

)
public class HeadersServlet extends HttpServlet {
 
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
  		  PrintWriter printWriter = response.getWriter();

        printWriter.println(getClass().getName());
        String name = null;
        Enumeration e = request.getHeaderNames();
        if (e.hasMoreElements()) {
            printWriter.println("Request headers:");
            while (e.hasMoreElements()) {
                name = (String)e.nextElement();
                printWriter.println(new StringBuilder().append(name).append(" = ").append(request.getHeader(name)).toString());
            }
        }
   
  	}

}