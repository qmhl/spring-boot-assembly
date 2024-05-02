package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_92_list_交并差 {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()/1000);

        List<String> list1 = new ArrayList<>(Arrays.asList("a","b","c"));

        List<String> list2 = new ArrayList<>(Arrays.asList("b","c","d"));

        //intersection: 取交集
        List<String> sameList = CollectionUtils.intersection(list1,list2).stream().collect(Collectors.toList());
        System.out.println("交集："+sameList);

        //取并集（去重）
        System.out.println("并集："+ ArrayUtils.toString(CollectionUtils.union(list1,list2)));

        //取差集
        System.out.println("差集："+CollectionUtils.subtract(list1,list2));

        //取两个集合的交集的补集(补集一般指绝对补集，即一般地，设S是一个集合，A是S的一个子集，由S中所有不属于A的元素组成的集合，叫做子集A在S中的绝对补集)
        System.out.println("补集："+CollectionUtils.disjunction(list1,list2));
    }


}



