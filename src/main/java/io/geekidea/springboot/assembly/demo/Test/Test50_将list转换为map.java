package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://blog.csdn.net/qq_40320667/article/details/108475658
 * 
 * https://blog.csdn.net/weixin_41552767/article/details/107662821?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-107662821-blog-124467012.pc_relevant_layerdownloadsortv1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-107662821-blog-124467012.pc_relevant_layerdownloadsortv1&utm_relevant_index=5
 */
@Slf4j
public class Test50_将list转换为map {
    public static void main(String[] args) {

        List<Person> listA = new ArrayList<>();
        Person person = new Person();
        person.setName("张三");
        person.setAge(11);
        listA.add(person);


        //1、将listA集合转换为map
        Map<String, Person> map = listA.stream().collect
                (Collectors.toMap(Person ::getName, Person -> Person));
        System.out.println(JSON.toJSONString(map));

        Person stu1 = new Person("张三", 23);
        Person stu2 = new Person("李四", 24);
        Person stu3 = new Person("王五", 25);
        Person stu4 = new Person("赵六", 23);
        Person stu5 = new Person("前七", 25);

        List<Person> strList = new ArrayList<>();
        Collections.addAll(strList, stu1, stu2, stu3, stu4,stu5);
        // 1.根据age作为key,name作为value转map（age相同时前面覆盖后面的数据）
        Map<Integer, String> collect = strList.stream().collect(Collectors.toMap(Person::getAge, Person::getName, (key1,key2) -> key1 ));
        System.out.println("=======================");
        System.out.println(JSON.toJSONString(collect));
        for (Map.Entry<Integer, String> integerStudentEntry : collect.entrySet()) {
            System.out.println(integerStudentEntry.getKey() + ":" + String.valueOf(integerStudentEntry.getValue()));
        }

        // 2.根据age作为key，student对象作为value转map（age相同时前面覆盖后面的数据）
        Map<Integer, Person> collectStu = strList.stream().collect(Collectors.toMap(Person::getAge, Function.identity(), (key1, key2) -> key2));
        System.out.println(JSON.toJSONString(collectStu));

        System.out.println("=======================");

        for (Map.Entry<Integer, Person> integerStudentEntry : collectStu.entrySet()) {
            System.out.println(integerStudentEntry.getKey() + "::" + String.valueOf(integerStudentEntry.getValue()));
        }

        // 3.根据age作为key，student对象作为value分组转map（age相同时前面覆盖后面的数据）
        Map<Integer, List<Person>> listMap = strList.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println("=======================");
        System.out.println(JSON.toJSONString(listMap));
        for (Map.Entry<Integer, List<Person>> integerStudentEntry : listMap.entrySet()) {
            System.out.println(integerStudentEntry.getKey() + "::" + String.valueOf(integerStudentEntry.getValue()));
        }



    }


}



