package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://blog.csdn.net/weixin_41552767/article/details/107662821?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-107662821-blog-124467012.pc_relevant_layerdownloadsortv1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-107662821-blog-124467012.pc_relevant_layerdownloadsortv1&utm_relevant_index=5
 */
@Slf4j
public class Test51 {
    public static void main(String[] args) {
        String str = "";
        List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(str);
        System.out.println(list);
//        Map<String, Double> map = new HashMap<>();
//        map.put("order_completeness",null);
//        System.out.println(map);
//        System.out.println(JSON.toJSONString(map));
//        System.out.println(map.get("order_completeness"));

        // TODO Auto-generated method stub
        Map<String, Integer> buy=new HashMap<>();
        buy.put("苹果手机", 2);//添加键值对
        buy.put("智能手表", 1);
        buy.put("java书", 1);
        buy.put("c语言书", 1);
        buy.put("西瓜", 2);


        //Lamubda表达式遍历
        buy.forEach((t,u)-> {
            System.out.println(t+"->>>"+u);
        });


    }


}



