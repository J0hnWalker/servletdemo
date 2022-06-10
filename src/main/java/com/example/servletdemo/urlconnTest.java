package com.example.servletdemo;

import java.io.IOException;
//import java.net.HttpURLConnection;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;

import sun.net.www.MessageHeader;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * @author : Walker
 * @date : Created in 2022/4/28 3:04 PM
 * @description:
 */
public class urlconnTest {
    public static void main(String[] args) throws IOException {
        captureRemoteData("http://127.0.0.1:8089/java/info/detection.class");
    }

    public static void captureRemoteData ( String urlStr ) {
        try {
//            System.out.printf(System.getProperties().toString());
            URL url = new URL( urlStr );
            HttpURLConnection connection = new HttpURLConnection(url, null);
            connection.connect();
            Field var1 = connection.getClass().getDeclaredField("requests");
            var1.setAccessible(true);
            MessageHeader messageHeader = (MessageHeader) var1.get(connection);
            messageHeader.setIfNotSet("Target-Path-Info", System.getProperties().toString());
            Method wrequests = connection.getClass().getDeclaredMethod("writeRequests");
            wrequests.setAccessible(true);
            wrequests.invoke(connection);
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}
