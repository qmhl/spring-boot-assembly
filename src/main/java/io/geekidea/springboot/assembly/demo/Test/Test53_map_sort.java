package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * map排序
 * https://www.cnblogs.com/zhaoyijunjava/p/16015967.html
 *
 * http://www.manongjc.com/detail/24-pefsyxndssljagq.html
 *
 * https://blog.csdn.net/weixin_43203539/article/details/119388874?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-119388874-blog-105204485.pc_relevant_3mothn_strategy_and_data_recovery&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-119388874-blog-105204485.pc_relevant_3mothn_strategy_and_data_recovery&utm_relevant_index=1
 */
@Slf4j
public class Test53_map_sort {
    public static void main(String[] args) {

        String json = "[{\"dt\":\"2022-10-26\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":12179,\"business_type_2\":\"厂直\",\"business_type_1\":\"POP\"},{\"dt\":\"2022-10-26\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":12356,\"business_type_2\":\"标准自营\",\"business_type_1\":\"自营\"},{\"dt\":\"2022-10-25\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":10718,\"business_type_2\":\"标准自营\",\"business_type_1\":\"自营\"},{\"dt\":\"2022-10-25\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":10596,\"business_type_2\":\"厂直\",\"business_type_1\":\"自营\"},{\"dt\":\"2022-10-28\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":12217,\"business_type_2\":\"厂直\",\"business_type_1\":\"自营\"},{\"dt\":\"2022-10-28\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":12486,\"business_type_2\":\"标准自营\",\"business_type_1\":\"自营\"},{\"dt\":\"2022-10-29\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":14685,\"business_type_2\":\"标准自营\",\"business_type_1\":\"自营\"},{\"dt\":\"2022-10-29\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":12440,\"business_type_2\":\"厂直\",\"business_type_1\":\"自营\"},{\"dt\":\"2022-10-24\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":16697,\"business_type_2\":\"标准自营\",\"business_type_1\":\"自营\"},{\"dt\":\"2022-10-24\",\"order_completeness_numerator\":0,\"order_completeness_denominator\":12207,\"business_type_2\":\"厂直\",\"business_type_1\":\"自营\"}]";


        List<Map<String, Object>> list = toListMap(json);
//        System.out.println(list);

//        Map<String, List<Map<String, Object>>> collect = list.stream().collect(Collectors.groupingBy(o -> o.get("business_type_1") + "_" + o.get("dt")));
//        System.out.println(JSON.toJSONString(collect));
//        List<String> dataX = new LinkedList<>();
//        List<Integer> dataY = new LinkedList<>();
//        for (String key : collect.keySet()) {
//            String[] split = key.split("_");
//            List<Map<String, Object>> maps = collect.get(key);
//            int value = maps.stream().mapToInt(o -> (int) o.get("order_completeness_numerator")).sum();
//            int value2 = maps.stream().mapToInt(o -> (int) o.get("order_completeness_denominator")).sum();
//            log.info("key :{} value:{} value2: {}", key, value, value2);
//            dataX.add(split[1]);
//            dataY.add(value2);
//        }
//        System.out.println(dataX);
//        System.out.println(dataY);


//        Map<String, List<Map<String, Object>>> collect = list.stream().collect(Collectors.groupingBy(o -> (String) o.get("dt")));
//        Map<String, List<Map<String, Object>>> collect1 = collect.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new));
//        System.out.println(collect1);

//        Map<String, List<Map<String, Object>>> collect = list.stream().collect(Collectors.groupingBy(o -> (String) o.get("dt")));
//        Map<String, List<Map<String, Object>>> result =new HashMap<>();
//        collect.entrySet().stream().sorted(Map.Entry.comparingByKey()).
//                forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
//        System.out.println(JSON.toJSONString(result));

        Map<String, List<Map<String, Object>>> collect1 = list.stream().collect(Collectors.groupingBy(o -> (String) o.get("dt")));
        Map<String, List<Map<String, Object>>> result1 =new LinkedHashMap<>();
        collect1.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                forEachOrdered(x -> {
                    System.out.println(x);
                    result1.put(x.getKey(), x.getValue());
                });
        System.out.println(JSON.toJSONString(result1));
//
//        Map<String, List<Map<String, Object>>> collect = list.stream().collect(Collectors.groupingBy(o -> (String) o.get("dt")));
//        Map<String, List<Map<String, Object>>> collect1 = collect.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        System.out.println(JSON.toJSONString(collect1));

//        List<Map<String, Object>> sortAllList = list.stream().sorted(Comparator.comparing(h -> ((String) h.get("dt")))).collect(Collectors.toList());
//        log.info("handleAllBusinessTypeForIndexTrend sortAllList is {}",JSON.toJSONString(sortAllList));
//        System.out.println(JSON.toJSONString(sortAllList));




        //        List<Map<String, Object>> dtList = list.stream().sorted(Comparator.comparing(h -> ((String) h.get("dt")))).collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(dtList));
    }


    public static List<Map<String, Object>> toListMap(String json) {
        List<Object> list = JSON.parseArray(json);

        List<Map<String, Object>> listw = new ArrayList<Map<String, Object>>();
        for (Object object : list) {
            Map<String, Object> ageMap = new HashMap<String, Object>();
            Map<String, Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
            listw.add(ret);
        }
        return listw;

    }


}



