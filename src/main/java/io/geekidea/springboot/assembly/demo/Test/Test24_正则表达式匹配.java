package io.geekidea.springboot.assembly.demo.Test;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 */
@Slf4j
public class Test24_正则表达式匹配 {

    public static void main(String[] args) throws Exception {

        testMore();
    }


    public static void testMore() {
//        Pattern p = Pattern.compile("\\d+");
//        Matcher m = p.matcher("aa22bb23");
//        m.find();
//        int start = m.start();//2
//        String group = m.group();//22
//        int end = m.end();//4
//        System.out.println(start);
//        System.out.println(group);
//        System.out.println(end);
//        m.find();
//        start = m.start();//6
//        group = m.group();//23
//        end = m.end();//8
//        System.out.println(start);
//        System.out.println(group);
//        System.out.println(end);


        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("aa22bb23");
        m.find();
        String group1 = m.group(0);
        System.out.println(group1);
        String group2 = m.group(1);
        System.out.println(group2);
    }

}
