package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.geekidea.springboot.assembly.demo.model.NeedRadarReqDTO;
import io.geekidea.springboot.assembly.demo.model.RestResponse;
import io.geekidea.springboot.assembly.demo.model.Son;
import io.geekidea.springboot.assembly.demo.utils.MD5Util;
import com.google.common.base.Splitter;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Test8 {
    public static void main(String[] args) throws ParseException {

//        String reqParamPost = "{\"cid1\":737,\"cid2\":1276,\"cid3\":14380,\"cardType\":\"post\",\"time\":1,\"priceType\":\"全价格带\",\"needType\":[],\"userGroup\":-1}";
//        NeedRadarReqDTO dtoPost = JSON.parseObject(reqParamPost, NeedRadarReqDTO.class);

//        System.out.println("dtoPost: "+dtoPost);

//        String brand=null;
//        List<String> strings = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(brand);
//        System.out.println(strings);

//        Son son = new Son();
//        son.setName("name");
//        son.setAge("age");
//        System.out.println(son.toString());
//        System.out.println(JSON.toJSONString(son));

        String s = "{\"价格带3\":\"3000-5000\",\"价格带2\":\"1000-2000\",\"价格带1\":\"6000-7000\"}\t";
        List<String> resList = new ArrayList();
        Map<String, String> map1 = JSONObject.parseObject(s, Map.class);
        // 价格区间按小到大顺序排列
        Map<String, String> resultMap1 = new TreeMap<>((str1, str2) -> str1.compareTo(str2));
        resultMap1.putAll(map1);
        for (Map.Entry<String, String> entry : resultMap1.entrySet()) {
            resList.add(entry.getValue());
        }
        System.out.println(resList);


        Map<String, Integer> map = new HashMap();
        map.put("价格带1", 12);
        map.put("价格带4", 13);
        map.put("价格带3", 14);
        map.put("价格带2", 14);
        map.put("价格带10", 14);

        Map<String, Integer> resultMap = new TreeMap<>((str1, str2) -> str1.compareTo(str2));
        resultMap.putAll(map);

        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey());
        }


        System.out.println(JSON.toJSONString(RestResponse.ok(resList)));

        testMap2List();

    }


    public static void testMap2List() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("价格带5", "AA");
        map.put("价格带1", "AA");
        map.put("价格带2", "BB");
        map.put("价格带3", "CC");
        map.put("价格带4", "DD");
        map.put("价格带7", "DD");
        map.put("价格带11", "eeee");
        System.out.println(JSON.toJSONString(map));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("--" + entry.getKey());
        }

        System.out.println( UUID.randomUUID().toString().replaceAll("-",""));

    }


}
