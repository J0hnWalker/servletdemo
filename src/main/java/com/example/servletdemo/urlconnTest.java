package com.example.servletdemo;

import java.io.IOException;
//import java.net.HttpURLConnection;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.util.Map;

import sun.net.www.MessageHeader;
import sun.net.www.protocol.http.HttpURLConnection;
import sun.security.action.GetPropertyAction;

/**
 * @author : Walker
 * @date : Created in 2022/4/28 3:04 PM
 * @description:
 */
public class urlconnTest {
    public static void main(String[] args) throws IOException {
        captureRemoteData("http://127.0.0.1:8082/");
    }

    public static void captureRemoteData ( String urlStr ) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL( urlStr );
            connection = new HttpURLConnection(url, null);
            connection.connect();
            Field var1 = connection.getClass().getDeclaredField("requests");
            var1.setAccessible(true);
            MessageHeader messageHeader = (MessageHeader) var1.get(connection);
            messageHeader.setIfNotSet("ClassPath", (String) AccessController.doPrivileged(new GetPropertyAction("java.class.path")));
            Method wrequests = connection.getClass().getDeclaredMethod("writeRequests");
            wrequests.setAccessible(true);
            wrequests.invoke(connection);
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}
