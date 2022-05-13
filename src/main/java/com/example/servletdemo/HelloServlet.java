package com.example.servletdemo;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
//        new Tomcat78EchoTest();
        String p = request.getParameter("name");
        try {
            Class.forName(p);
            out.println(p);
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e);
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            InputStream is = req.getInputStream();
            ObjectInputStream oss = new ObjectInputStream(new BASE64DecoderStream(is));
//            ObjectInputStream oss = new ObjectInputStream(is);
            oss.readObject();
        } catch (ClassNotFoundException e){
            PrintWriter out = res.getWriter();
            out.println(e.getMessage());
        }
    }

    public void destroy() {
    }
}