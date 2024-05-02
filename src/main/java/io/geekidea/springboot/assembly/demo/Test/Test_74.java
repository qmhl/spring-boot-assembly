package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class Test_74 {


    public static void main(String[] args) throws Exception {
        String obj = "{\"bizTypeId\":1,\"bizDomainId\":1,\"entityId\":3,\"labelNameEn\":\"xbl\",\"labelProductTemplate\":\"0\",\"dim\":\"\"}";

        System.out.println(obj.toString());

        String url = "123?abc";
        String abc = url.replace("abc", "22222222");
        System.out.println(abc);

        JSONArray array = new JSONArray();
        array.add("xx");
        array.add("yy");
        array.add("zz");
        for (Object o : array) {

        }


        System.out.println(getSecondsNextEarlyMorning());

        //handleMap
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("addr", "北京");
        System.out.println("原始：" + JSON.toJSONString(map));
        Map res = handleMap(map);
        System.out.println("处理后：" + JSON.toJSONString(res));

        // ==========================
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "111");
        map1.put("addr", "111");
        map1.put("other", "other");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "222");
        map2.put("addr", "222");
        List<Map<String, Object>> maps = Arrays.asList(map1, map2);
        List<Map<String, Object>> resMap = handleMap1(maps);
        System.out.println(JSON.toJSONString(resMap));
    }

    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date time = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(time);
        System.out.println("formart : " + format);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    public static Map handleMap(Map<String, Object> resultMap) {
//
//        resultMap.put("name", "lisi");
//        return resultMap;


        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("map1", "aa");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("map2 ", "bb");

//通过Map的putAll来将一个Map完全赋值给另外一个Map
        map1.putAll(map2);
        return map1;




    }

    public static List<Map<String, Object>> handleMap1(List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            String other = (String) map.get("other");
            if (StringUtils.isNotBlank(other)) {
                map.put("name", "999");
                map.put("addr", "999");
            }
        }
        return list;

    }




}



