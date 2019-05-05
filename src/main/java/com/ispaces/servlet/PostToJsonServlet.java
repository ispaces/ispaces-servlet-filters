package com.ispaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet("/post-to-json") 
public class PostToJsonServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet(request, response)");


        PrintWriter printWriter = response.getWriter();

        printWriter.write("<html>");
        printWriter.write("<body>");

        printWriter.write("<form method=\"post\">");
        printWriter.write("<input type=\"text\" name=\"test\">");
        printWriter.write("<input type=\"submit\">");
        printWriter.write("</form>");

        printWriter.write("</body>");
        printWriter.write("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doPost(request, response)");

        try {
        
            logger.debug("\n");
            logger.debug("request: " + request);
            logger.debug("request param map: " + request.getParameterMap());
            logger.debug("\n");

            org.json.JSONObject jsonObj = new org.json.JSONObject();
            Map<String, String[]> params = request.getParameterMap();
            for (Map.Entry<String,String[]> entry : params.entrySet()) {
                String v[] = entry.getValue();
                Object o = (v.length == 1) ? v[0] : v;
                jsonObj.put(entry.getKey(), o);
            }

            jsonObj.toString();
            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to
            // the output stream
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObj**, perform the following, it will
            // return your json object
            out.print(jsonObj);
            out.flush();
            
        } catch (Exception e) {
            logger.error(e);
        }

    }
}
