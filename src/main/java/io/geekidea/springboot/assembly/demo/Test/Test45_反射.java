package io.geekidea.springboot.assembly.demo.Test;


import io.geekidea.springboot.assembly.demo.model.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test45_反射 {
    public static void main(String[] args) {

        Person p = new Person("mami", 99);
        Person p2 = new Person("mami22", 109);
        Integer age = (Integer) getFieldValueByName(p, "age");
        Integer age2 = (Integer) getFieldValueByName(p2, "age");
        System.out.println(age);
        System.out.println(age2);
        System.out.println(age2-age);

//
        System.out.println("==============");
//        Object age11 = ReflectUtil.getValue(p, "age");
//        Object age22 = ReflectUtil.getValue(p2, "age");
//        System.out.println(age11);
//        System.out.println(age22);

    }


    /**
     * 获取对象指定属性的值
     *
     * @param o         对象
     * @param fieldName 要获取值的属性
     *                  返回值：对象指定属性的值
     */
    public static Object getFieldValueByName(Object o, String fieldName) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 给对象指定的属性设置值
     *
     * @param obj       要设置值的对象
     * @param fieldName 要设置值的属性
     * @param value     值
     */
    public void setFieldValueByName(Object obj, String fieldName, Object value) {
        try {
            // 获取obj类的字节文件对象
            Class c = obj.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(obj, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
