package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.Father;
import io.geekidea.springboot.assembly.demo.model.Person;
import io.geekidea.springboot.assembly.demo.model.Son;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Test6 {
    public static void main(String[] args) throws ParseException {

//        final List<Integer> innerList = new LinkedList<>();
//        System.out.println(innerList.size());
//        innerList.add(2,3);
//        innerList.add(0,1);
//        innerList.add(1,2);
//        System.out.println(innerList);


//        List<String> list = Arrays.asList(new String[10]);
//        list.set(1,"bb");
//        list.set(3,"dd");
//        list.set(0,"aa");
//        list.set(2,"cc");
//        System.out.println(list.toString());

        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(2);
//        list1.add(4);

        List<Person> list = new ArrayList();
        list.add(new Person("zhangsan", 1));
        list.add(new Person("lisi", 2));
        list.add(new Person("wangwu", 3));
        list.add(new Person("liuliu", 4));

        List<Person> collect = list.stream().filter(o -> !list1.contains(o.getAge())).collect(Collectors.toList());

        collect.forEach(o -> o.setName("xxxx"));
//        list.forEach(item->{
//            if(list1.contains(item.getAge())){
//
//            }
//        });
        System.out.println(JSON.toJSONString(collect));

    }

}
