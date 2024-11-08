package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://blog.csdn.net/justdoit_potato/article/details/120022084
 */
@Slf4j
public class Test29去重相加 {


    public static void main(String[] args) throws Exception {
        List<Person> list = new ArrayList<>();
        list.add(new Person("xx",11));
        list.add(new Person("yy",44));
        list.add(new Person("xx",22));
//        StringBuilder targetBuilder = new StringBuilder();
        List<Person> merge = merge(list);
        System.out.println(JSON.toJSONString(merge));


    }

    /**
     * 将name进行合并ages 相加道回合并后的集合使用Java8的流进行处理
     */
    public static List<Person> merge(List<Person> list) {
        List<Person> result = list.stream()
                // 表示name为key， 接着如果有重复的，那么从Singer对象o1与o2中筛选出一个，这里选择o1，
                // 并把name重复，需要将ages与o1进行合并的o2, 赋值给o1，最后返回o1
                .collect(Collectors.toMap(Person::getName, a -> a, (o1, o2)-> {
                    o1.setAge(o1.getAge() + o2.getAge());
                    return o1;
                })).values().stream().collect(Collectors.toList());
        return result ;
    }


}
