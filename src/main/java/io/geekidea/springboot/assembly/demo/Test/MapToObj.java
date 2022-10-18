package io.geekidea.springboot.assembly.demo.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * https://blog.csdn.net/WX5991/article/details/121163412
 */
public class MapToObj {
 
    /**
     * 把source转为target
     * @param source source
     * @param target target
     * @param <T> 返回值类型
     * @return 返回值
     * @throws Exception newInstance可能会抛出的异常
     */
    public static <T> T mapToObj(Map source, Class<T> target) throws Exception {
        Field[] fields = target.getDeclaredFields();
        T o = target.newInstance();
        for(Field field:fields){
            Object val;
            if((val=source.get(field.getName()))!=null){
                field.setAccessible(true);
                field.set(o,val);
            }
        }
        return o;
    }


//    public static <T> T mapToObj(List<String>, Class<T> target) throws Exception {
//        Field[] fields = target.getDeclaredFields();
//        T o = target.newInstance();
//        for(Field field:fields){
//            Object val;
//            if((val=source.get(field.getName()))!=null){
//                field.setAccessible(true);
//                field.set(o,val);
//            }
//        }
//        return o;
//    }
 
 
}