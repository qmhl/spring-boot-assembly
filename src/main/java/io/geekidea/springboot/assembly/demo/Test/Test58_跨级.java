package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.BusinessTypeLevel;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test58_跨级 {
    public static final String YOY_MOM = "--";
    public static final String NAN = "nan";


    public static void main(String[] args) {
        List<List<String>> commonList = Arrays.asList(Arrays.asList("自营"), Arrays.asList("自营", "厂直"), Arrays.asList("自营", "标准"));
//        List<List<String>> commonList = Arrays.asList(Arrays.asList("全渠道"));

        Map<String, List<List<String>>> map = splitList(commonList);
        BusinessTypeLevel popLevel = getBusinessLevel(map.get("pop"));
        BusinessTypeLevel allChannelLevel = getBusinessLevel(map.get("allChannel"));
        System.out.println(JSON.toJSONString(popLevel));

        System.out.println("===============");

        System.out.println(JSON.toJSONString(allChannelLevel));

    }

    public static BusinessTypeLevel getBusinessLevel(List<List<String>> commonList) {
        log.info("getBusinessLevel commonList :{}", JSON.toJSONString(commonList));
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(commonList)) {
            return null;
        }

        int level = 1;
        List<String> list1 = new LinkedList<>();
        List<String> list2 = new LinkedList<>();
        List<Integer> collect = commonList.stream().map(o -> o.size()).distinct().collect(Collectors.toList());
        //size :1 说明每个二维数组元素个数相同
        if (collect.size() == 1) {
            // 值都是1  ，都是1级
            if (collect.get(0) == 1) {
                level = 1;
            } else {
                // 都是2级
                level = 2;
            }
        } else {
            // 跨级
            level = -1;
        }
        //跨级
        for (int i = 0; i < commonList.size(); i++) {
            if (commonList.get(i).size() == 1) {
                list1.add(commonList.get(i).get(0));
            }
            if (commonList.get(i).size() == 2) {
                list2.add(commonList.get(i).get(1));
            }
        }

        BusinessTypeLevel businessTypeLevel = new BusinessTypeLevel();
        businessTypeLevel.setLevel(level);
        businessTypeLevel.setLevel1List(list1);
        businessTypeLevel.setLevel2List(list2);
        return businessTypeLevel;
    }

    public static Map<String, List<List<String>>> splitList(List<List<String>> commonList) {

        //全渠道、自营pop的List
        List<List<String>> allChannelList = new LinkedList<>();
        List<List<String>> popList = new LinkedList<>();
        for (int i = 0; i < commonList.size(); i++) {
            if (commonList.get(i).contains("全渠道")) {
                allChannelList.add(commonList.get(i));
            } else {
                popList.add(commonList.get(i));
            }

        }
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("pop", popList);
        map.put("allChannel", allChannelList);
        return map;
    }

}



