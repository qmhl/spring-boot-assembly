package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.BusinessTypeLevel;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class Test43_自营pop同城购跨级 {

    public static void main(String[] args) {
        List<List<String>> commonList = Arrays.asList(Arrays.asList("自营", "标准自营"), Arrays.asList("pop"), Arrays.asList("同城购"));
        Map<String, List<List<String>>> map = splitList(commonList);
        log.info(JSON.toJSONString(map));
        log.info("=============");
//        BusinessTypeLevel businessTypeLevel = getBusinessLevel(commonList);
        BusinessTypeLevel businessTypeLevel = getBusinessLevel(map.get("pop"));
        BusinessTypeLevel businessTypeLevel2 = getBusinessLevel(map.get("localShopping"));
        log.info(JSON.toJSONString(businessTypeLevel));
        log.info(JSON.toJSONString(businessTypeLevel2));


    }


    public static BusinessTypeLevel getBusinessLevel(List<List<String>> commonList) {
        int level = 0;
        //分组 1级和二级：
        List<String> list1 = new LinkedList<>();
        List<String> list2 = new LinkedList<>();

        //同城购及自营pop
        List<List<String>> localShoppingList = new LinkedList<>();
        List<List<String>> popList = new LinkedList<>();
        for (int i = 0; i < commonList.size(); i++) {
            if (commonList.get(i).contains("同城购")) {
                localShoppingList.add(commonList.get(i));
            } else {
                popList.add(commonList.get(i));
            }

        }

        // size :1 没跨级 都是1级  2：没跨级 都是2级 -1：跨级
        int size = commonList.get(0).size();

        for (int i = 0; i < commonList.size(); i++) {
            if (commonList.get(i).size() == size) {
                level = commonList.get(0).size();
            } else {
                level = -1;
            }
        }

        System.out.println(level == -1 ? "跨级" : level + "级");
        //  分组1级和2级
        //跨级
        if (level == -1) {
            for (int i = 0; i < commonList.size(); i++) {
                if (commonList.get(i).size() == 1) {
                    list1.add(commonList.get(i).get(0));
                }
                if (commonList.get(i).size() == 2) {
                    list2.add(commonList.get(i).get(1));
                }
            }
            System.out.println(" 跨级 list1 " + list1);
            System.out.println(" 跨级 list2 " + list2);
        } else {
            System.out.println(" 不跨级 size:  " + level);

        }

        BusinessTypeLevel businessTypeLevel = new BusinessTypeLevel();
        businessTypeLevel.setLevel(level);
        businessTypeLevel.setLevel1List(list1);
        businessTypeLevel.setLevel2List(list2);

        return businessTypeLevel;

    }

    public static Map<String, List<List<String>>> splitList(List<List<String>> commonList) {

        //同城购及自营pop
        List<List<String>> localShoppingList = new LinkedList<>();
        List<List<String>> popList = new LinkedList<>();
        for (int i = 0; i < commonList.size(); i++) {
            if (commonList.get(i).contains("同城购")) {
                localShoppingList.add(commonList.get(i));
            } else {
                popList.add(commonList.get(i));
            }

        }
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("pop", popList);
        map.put("localShopping", localShoppingList);

        return map;
    }
}
