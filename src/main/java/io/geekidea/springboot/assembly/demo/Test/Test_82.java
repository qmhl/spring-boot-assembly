package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Test_82 {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("1", "22", "33", "44");
        List<String> subList1 = list.subList(0, 2);
        List<String> subList2 = list.subList(2, list.size());
        System.out.println(list);
        System.out.println("========subList1==========");
        System.out.println(subList1);
        System.out.println(list);
        System.out.println("=========subList2=========");
        System.out.println(subList2);
        System.out.println(list);

        Map<String, Object> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        List<Map> listMap = new ArrayList<>();
        listMap.add(map);
        System.out.println("listMap"+ JSON.toJSONString(listMap));
        map.put("c","c");
        System.out.println("listMap2"+ JSON.toJSONString(listMap));


        boolean flag = false;
        boolean b = false;
        flag = flag || b;
        System.out.println(flag);

    }


}



