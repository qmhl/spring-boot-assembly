package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
//        String key = CacheKey.TASK_INFO.createCacheKey("4373a3bc1b814f3581822d7771005c85", "158548");
//        System.out.println(key);

        ArrayList<Integer> list1 = Lists.newArrayList(1,2,3,4);
        ArrayList<Integer> list2 = Lists.newArrayList(111,211,311,411);
        list1.addAll(list2);
        System.out.println(list1);

        int i = 5;
        do {
            i++;
            System.out.println(i);
        } while (i < 5);

//        boolean accquired = false;
//        int retryTime = 0;
//        do {
//            System.out.println(Thread.currentThread().getName() + ", retryTime = " + retryTime);
//            if (retryTime < 5) {
//                accquired = true;
//                retryTime++;
//            } else {
//                return;
//            }
//        } while (!accquired);
//        System.out.println("里面的retryTime为:" + retryTime);
        int start = 0;
        List<String> list = Lists.newArrayList("11","21","31","41");
        int QUERY_RECORD_COUNT = 100;


        do {
            list.stream().forEach(info -> {

                if (info != null) {
                    System.out.println("遍历的内容为："+info);
                }
            });

            start += QUERY_RECORD_COUNT;
        } while (!CollectionUtils.isEmpty(list) && list.size() == QUERY_RECORD_COUNT);


    }
}
