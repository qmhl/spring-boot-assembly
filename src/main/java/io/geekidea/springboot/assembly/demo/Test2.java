package io.geekidea.springboot.assembly.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Test2 {

    private static Object Map;

    public static void main(String[] args) {
        String featureStr = "{\"product_word\":{\"双卡双待手机\":0.5},\"brand_code\":{\"14026\":1},\"gender\":{\"general\":1,\"female\":0,\"male\":0},\"buy_level\":{\"3\":1},\"modifier_word\":{\"移动\":\"0.20\",\"电信\":\"0.20\",\"联通\":\"0.20\",\"绿色\":\"0.20\",\"iphone\":\"0.20\"},\"season\":{\"spring\":\"0.25\",\"winter\":\"0.25\",\"autumn\":\"0.25\",\"summer\":\"0.25\"},\"cid3\":{\"655\":1},\"keyword\":{\"iphone\":1},\"spu_id\":{\"100026667910\":1},\"cid1\":{\"9987\":1},\"brand_word\":{\"apple\":\"1.00\"}}";
        List<String> needKeys = new ArrayList<>();
        needKeys.add("brand_word");
        int topnum = 10;
        getDataByKeys(featureStr, needKeys, topnum);
    }

    public static TreeMap getDataByKeys(String featureStr, List<String> needKeys, int topnum) {
        TreeMap map = new TreeMap();
        if (StringUtils.isEmpty(featureStr)) {
            return map;
        }
        JSONObject feature = JSON.parseObject(featureStr);
        for (String key : needKeys) {
            Map<String, BigDecimal> allmap = (Map) feature.get(key);
//            new BigDecimal("123123");
            System.out.println(allmap);
            Map<String, BigDecimal> newMap = new HashMap<>();

            for (Map.Entry<String, BigDecimal> entry : allmap.entrySet()) {
                newMap.put(entry.getKey(), new BigDecimal(String.valueOf(entry.getValue())));
            }
            System.out.println(newMap);

            List<String> topList = newMap.entrySet().stream().sorted((Map.Entry<String, BigDecimal> o1, Map.Entry<String, BigDecimal> o2) -> o2.getValue().compareTo(o1.getValue()))
                    .map(entry -> entry.getKey()).collect(Collectors.toList())
                    .subList(0, Math.min(allmap.size(), topnum));
            System.out.println(JSON.toJSONString(topList));


        }
        return map;
    }
}