package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_93_list对象_交并差 {
    public static void main(String[] args) {
//        interSubList();

        interSubSet();
    }

    private static void interSubList() {
        System.out.println(System.currentTimeMillis()/1000);

        List<Person> list1 = new ArrayList<>();
        list1.add(new Person("a",1));
        list1.add(new Person("b",2));
        list1.add(new Person("c",3));

        List<Person> list2 = new ArrayList<>();
        list2.add(new Person("b",2));
        list2.add(new Person("c",3));
        list2.add(new Person("d",4));

        //intersection: 取交集
        List<Person> sameList = CollectionUtils.intersection(list1,list2).stream().collect(Collectors.toList());
        System.out.println("交集："+ JSON.toJSONString(sameList));

        //取并集（去重）
        System.out.println("并集："+ JSON.toJSONString(ArrayUtils.toString(CollectionUtils.union(list1,list2))));

        //取差集
        System.out.println("差集1："+JSON.toJSONString(CollectionUtils.subtract(list1,list2)));
        System.out.println("差集2："+JSON.toJSONString(CollectionUtils.subtract(list2,list1)));

        //取两个集合的交集的补集(补集一般指绝对补集，即一般地，设S是一个集合，A是S的一个子集，由S中所有不属于A的元素组成的集合，叫做子集A在S中的绝对补集)
        System.out.println("补集："+JSON.toJSONString(CollectionUtils.disjunction(list1,list2)));
    }


    private static void interSubSet() {
        System.out.println(System.currentTimeMillis()/1000);

        Set<Person> list1 = new HashSet<>();
        list1.add(new Person("a",1));
        list1.add(new Person("b",2));
        list1.add(new Person("c",3));

        Set<Person> list2 = new HashSet<>();
        list2.add(new Person("b",2));
        list2.add(new Person("c",3));
        list2.add(new Person("d",4));

        //intersection: 取交集
        Set<Person> sameList = CollectionUtils.intersection(list1,list2).stream().collect(Collectors.toSet());
        System.out.println("交集："+ JSON.toJSONString(sameList));

        //取并集（去重）
        System.out.println("并集："+ JSON.toJSONString(ArrayUtils.toString(CollectionUtils.union(list1,list2))));

        //取差集

        System.out.println("差集1类型："+CollectionUtils.subtract(list1,list2));

        System.out.println("差集1："+JSON.toJSONString(CollectionUtils.subtract(list1,list2)));
        System.out.println("差集2："+JSON.toJSONString(CollectionUtils.subtract(list2,list1)));
        System.out.println("差集3："+JSON.toJSONString(subtract(list2,list1)));

        //取两个集合的交集的补集(补集一般指绝对补集，即一般地，设S是一个集合，A是S的一个子集，由S中所有不属于A的元素组成的集合，叫做子集A在S中的绝对补集)
        System.out.println("补集："+JSON.toJSONString(CollectionUtils.disjunction(list1,list2)));
    }



    private static Set<Person> subtract(Set<Person> data1, Set<Person> data2) {
        return (Set<Person>) CollectionUtils.subtract(data1, data2);
    }
}



