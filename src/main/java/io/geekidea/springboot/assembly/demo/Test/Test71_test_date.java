package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class Test71_test_date {


    private final static String YYYY_MM = "yyyy-MM";
    private final static String YYYY_MM_DD = "yyyy-MM-dd";
    private final static String YYYY_MM_DD_HH = "yyyy-MM-dd HH:mm:ss";


    public static void main(String[] args) throws Exception {
        List<Long> list = Arrays.asList(1680487200000L, 1683079200000L, 1685757600000L, 1688349600000L, 1691028000000L);
        for (Long aLong : list) {
            System.out.println(DateUtil.format(new Date(aLong), YYYY_MM_DD_HH));
        }

        Long next = list.iterator().next();
        System.out.println(next);

        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("name", "zhangsan");
        map1.put("age", "11");

        map2.put("type", "1");
        map1.putAll(map2);

        System.out.println(JSON.toJSONString(map1));

        ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3, 4,6);
        ArrayList<Integer> list2 = Lists.newArrayList(2, 3, 4, 5);
        boolean b = list1.retainAll(list2);
        System.out.println(list1);
        System.out.println(list2);


        String str = "121,2,11,17,18,19,20,22,23,24,26,27,28,29,32,33,35,38,40,42,44,45,46,47,48,50,52,53,55,58,90,101,102,103,104,105,107,110,111,112,113,115,116,117,118,119,120,122,123,124,520,127";
        String str1="17,19,23,24,28,29,32,101,102,105,110,801,90,113,119,120,121,123,124,127";
        List<String> size = Splitter.on(",").splitToList(str1);
        System.out.println("size: " + size.size());


        ArrayList<Integer> list11 = Lists.newArrayList(11, 12, 33, 4,6);
        boolean b1 = list11.addAll(1, Collections.singleton(99));
        System.out.println(list11);

    }
}



