package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import io.geekidea.springboot.assembly.demo.constant.SortFieldEnum;
import io.geekidea.springboot.assembly.demo.utils.EasyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test_81 {
    public static void main(String[] args) {
        List<Object> listValue = Lists.newArrayList("a","b");
        Object o = convertStringType(String.class, listValue);
        System.out.println(o);
    }

    public static Object convertStringType(Class type,Object value) {
        try {
            String valStr = String.valueOf(value);
            if (StringUtils.isBlank(valStr)) {
                return null;
            }
            Constructor constructor = type.getConstructor(String.class);
            if (constructor != null) {
                return constructor.newInstance(String.valueOf(value));
            } else {
                return value;
            }
        } catch (Exception e) {
            log.error("error getValue for class:{} value:{}",type.getName(),value);
        }
        return value;
    }
}



