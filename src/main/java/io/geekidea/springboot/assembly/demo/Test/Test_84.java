package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import io.geekidea.springboot.assembly.demo.utils.ProcessUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Test_84 {
    public static void main(String[] args) {
       String configStr="{\\\"select\\\":[[{\\\"code\\\":\\\"l2InnerItemUv7d31903\\\",\\\"name\\\":\\\"近7天\\\"},{\\\"code\\\":\\\"l2InnerItemUv15d31909\\\",\\\"name\\\":\\\"近15天\\\"},{\\\"code\\\":\\\"l2InnerItemUv30d31908\\\",\\\"name\\\":\\\"近30天\\\"}]],\\\"selectAndInput\\\":{\\\"unit\\\":\\\"\\\",\\\"dropList\\\":[{\\\"flag\\\":\\\"3\\\",\\\"label\\\":\\\"大于\\\"},{\\\"flag\\\":\\\"6\\\",\\\"label\\\":\\\"大于等于\\\"},{\\\"flag\\\":\\\"2\\\",\\\"label\\\":\\\"小于\\\"},{\\\"flag\\\":\\\"5\\\",\\\"label\\\":\\\"小于等于\\\"},{\\\"flag\\\":\\\"4\\\",\\\"label\\\":\\\"开区间\\\"},{\\\"flag\\\":\\\"7\\\",\\\"label\\\":\\\"前闭后开\\\"},{\\\"flag\\\":\\\"8\\\",\\\"label\\\":\\\"前开后闭\\\"}],\\\"isInputDecimal\\\":true,\\\"decimalNum\\\":1}}";
        // 需要去掉转义符
        String config = StringEscapeUtils.unescapeJava(configStr);
        Map map = JSON.parseObject(config, Map.class);
        List<List<Map<String,String>>> selectMapList = (List<List<Map<String,String>>>) map.get("select");
        for (List<Map<String, String>> innerMapList : selectMapList) {
            for (Map<String, String> innerMap : innerMapList) {
                String realCode = (String) innerMap.get("code");
                System.out.println(realCode);
            }


        }
        

    }


}



