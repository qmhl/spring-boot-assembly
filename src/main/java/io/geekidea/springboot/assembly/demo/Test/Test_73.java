package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import io.geekidea.springboot.assembly.demo.model.Person2;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test_73 {
    private static final String MAX_RANGE = "-9999999999";


    public static void main(String[] args) throws Exception {
        String k = "77,88";
        Map<String, Object> map = new HashMap<>();
        map.put("name", Arrays.asList(k));
        map.put("ids", Arrays.asList(596));
        List<Integer> name = (List<Integer>) map.get("name");
        System.out.println(name);


        List<Integer> ids = (List<Integer>) map.get("ids");
        System.out.println(ids);
        for (Integer id : ids) {
            System.out.println(id);
            System.out.println(Long.valueOf(id));
        }
        System.out.println(changeScientificNotation("92149"));
        int num = (int)(Math.random()*3000) ;

        System.out.println("随机数："+ num);
//        Thread.sleep();

        List<Integer> integers = Arrays.asList(1, 2, 3);
        for (Integer integer : integers) {
            System.out.println(integer+" 时间戳："+ new Date());
            Thread.sleep(2500);
            System.out.println(integer+" 时间戳2："+ new Date());

        }
    }

    public static String changeScientificNotation(String value) {
        DecimalFormat df = new DecimalFormat("0.##");
        BigDecimal db = new BigDecimal(value);
        return df.format(Double.valueOf(db.toPlainString()));


    }

}



