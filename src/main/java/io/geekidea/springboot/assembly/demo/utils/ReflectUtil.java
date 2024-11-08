package io.geekidea.springboot.assembly.demo.utils;


import io.geekidea.springboot.assembly.demo.entity.Apple;

import java.lang.reflect.Method;

public class ReflectUtil {
    public static void main(String[] args) {
        Apple apple = new Apple(1,"红苹果");

        System.out.println(getValue(apple,"color"));
    }

    //动态获取字段值
    public static Object getValue(Object dto, String name) {
        try {
            Method[] m = dto.getClass().getMethods();
            for (int i = 0; i < m.length; i++) {
                if (("get" + name).toLowerCase().equals(m[i].getName().toLowerCase())) {
                    return m[i].invoke(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}