package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Test50 {


    public static void main(String[] args) throws Exception {
        List<Map<String, Object>> feelOverviews = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("order_completeness_numerator", 0.0);
        map.put("order_completeness_denominator", 110.0);
        map.put("business_type_2", "标准自营");
        map.put("business_type_1", "自营");
        feelOverviews.add(map);
        System.out.println(JSON.toJSONString(map));
        Double sum1 = feelOverviews.stream().mapToDouble(o -> (double) o.get("order_completeness_numerator")).sum();
        Double sum2 = feelOverviews.stream().mapToDouble(o -> (double) o.get("order_completeness_denominator")).sum();
        System.out.println(sum1);
        System.out.println(sum2);

        double d1 = 1.1D;
        double d2 = 1.1D;
        Double aDouble = Double.valueOf(d1);
        Double bDouble = Double.valueOf(d2);
        System.out.println(aDouble);
        System.out.println(bDouble);


        Map<String, Object> map2 = new HashMap<>();
        map2.put("order_completeness_numerator", 0.0);
        map2.put("order_completeness_denominator", 110.0);
        map2.put("dt", "2022-10-29");
        map2.put("business_type_1", "自营");
        List<Map<String, Object>> List2 = Arrays.asList(map2);
        for (Map<String, Object> innerMap : List2) {
            Date dt = (Date) innerMap.get("dt");
            System.out.println("dt:  " + dt);
            String dt2 = (String) innerMap.get("dt");
            System.out.println("dt2:  " + dt2);

        }


    }

}


