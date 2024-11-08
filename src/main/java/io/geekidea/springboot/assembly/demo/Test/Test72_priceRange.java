package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import io.geekidea.springboot.assembly.demo.model.Person2;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test72_priceRange {
    private static final String MAX_RANGE = "-9999999999";


    public static void main(String[] args) throws Exception {
        List<Person2> list = new ArrayList<>();
        Person2 p1 = new Person2();
        p1.setName("xx");
        p1.setAge(1);
        Person2 p2 = new Person2();
        p2.setName("ww");
        p2.setAge(2);
        Person2 p3 = new Person2();
        p3.setName("ee");
        p3.setAge(3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.forEach(o -> {
            o.setAddr("上海");
        });
        System.out.println(JSON.toJSONString(list));
        System.out.println("==============");
        String price1 = "396以,179-229,229-396,85-99,99-179,0-85" ;
        String s = handlePriceRange(price1);
        System.out.println(s);
    }

    public static String handlePriceRange(String price) {
        List<String> priceList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(price);
        List<String> list1 = priceList.stream().map(o -> {
            if (o.contains("以上")) {
                o = o.replaceAll("以上", MAX_RANGE);
            }
            return o;
        }).collect(Collectors.toList());
        //将每个价格带字符串的首个值存map
        Map<Integer, String> map = new LinkedHashMap<>();
        for (String s : list1) {
            String[] split = s.split("-");
            map.put(Integer.valueOf(split[0]), s);
        }
        List<String> sortList = new LinkedList<>();
        //map key排序
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> {
            sortList.add(x.getValue());
        });

        list1.sort(Comparator.comparing(e -> sortList.indexOf(e)));
        return Joiner.on(",").skipNulls().join(list1).replaceAll(MAX_RANGE,"以上");
    }

}



