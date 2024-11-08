package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * map排序
 * https://www.cnblogs.com/zhaoyijunjava/p/16015967.html
 *
 * https://blog.csdn.net/weixin_43203539/article/details/119388874?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-119388874-blog-105204485.pc_relevant_3mothn_strategy_and_data_recovery&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-119388874-blog-105204485.pc_relevant_3mothn_strategy_and_data_recovery&utm_relevant_index=1
 */
@Slf4j
public class Test53_list_contain {
    public static void main(String[] args) {
        List<Long> initList = Arrays.asList(5L, 6L, 7L, 8L, 27L, 28L, 29L);
        List<Long> initList2 = Arrays.asList(1L, 2L, 3L,5L, 6L, 7L, 8L, 27L, 28L, 29L);
        List<Long> finalList = new ArrayList<>();
        initList2.forEach(o->{
            if(!initList.contains(o)){
                finalList.add(o);
            }
        });
        System.out.println(finalList);
    }


}



