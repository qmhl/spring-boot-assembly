package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.Father;
import com.example.demo.model.Son;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Test5 {
    public static void main(String[] args) throws Exception {
//        Son son = new Son();
//        son.setSex("女");
//        son.setSonAge("11");
//        son.setSonName("美女");
//        Father father= son;
//        System.out.println("son: "+JSON.toJSONString(son));
//        System.out.println("father: "+JSON.toJSONString(father));
//        String s = JSON.toJSONString(father);
//        Father father1 = JSON.parseObject(s, Father.class);
//        System.out.println(father1.toString());
//
//
//        if (son instanceof Father) {
//            System.out.println("son 是 father类型");
//        }

        Son son = new Son();
        son.setSex("女");
        son.setSonAge("11");
        son.setSonName("美女");
        son.setCid1(1);
        son.setCid2(2);
        son.setCid3(3);
        Father father= son;
        System.out.println("son: "+JSON.toJSONString(son));
        System.out.println("father: "+JSON.toJSONString(father));
        String s = JSON.toJSONString(father);
        Father father1 = JSON.parseObject(s, Father.class);
        System.out.println(father1.toString());



        final List<Integer> innerList = new ArrayList<>();
        innerList.add(1);
        innerList.add(2);
        System.out.println(innerList);


    }


}
