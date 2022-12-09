package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Test48_List_map_groupingby {


    public static void main(String[] args) {
        List<Map<String, Object>> result= new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name","qq");
        map.put("age","111");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name","qq");
        map2.put("age","222");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name","ww");
        map3.put("age","333");
        result.add(map);
        result.add(map2);
        result.add(map3);
//根据map中的key分组
        Map<Object, List<Map<String, Object>>> resMap = result.stream().collect(Collectors.groupingBy(item -> item.get("name")));
        System.out.println(JSON.toJSONString(resMap));
    }

}


