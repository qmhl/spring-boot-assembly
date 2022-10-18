package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.F1;
import com.example.demo.model.Person;
import com.example.demo.model.S1;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 */
@Slf4j
public class Test21 {
    private static Object Person;

    public static void main(String[] args) throws Exception {
//        Map<String,Object> source=new HashMap<>();
//        source.put("name","张三");
//        source.put("age",1);
//        //map转为对象
//        Person person = MapToObj.mapToObj(source,Person.class);
//        person.setName("xx");
//        person.setAge(22);
//        System.out.println(JSONObject.toJSONString(person));

//        Person person = Person.class.newInstance();
//        person.setAge(11);
//        person.setName("xxx");
        Person o = (Person) get(Person.class);
        o.setName("2222");
        o.setAge(33);
//
//        System.out.println(JSON.toJSONString(o));


//        System.out.println(JSON.toJSONString(t));

        for (int i = 0; i <4 ; i++) {
            System.out.println("值："+(i+1));
        }
        Class<String> stringClass = String.class;


    }

    private static Object get(Class clazz) throws IllegalAccessException, InstantiationException {
        Object o = clazz.newInstance();
        return o;
    }


}
