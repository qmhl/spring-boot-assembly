package io.geekidea.springboot.assembly.demo.Test;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 */
@Slf4j
public class Test28 {
    private static Object Person;

    public static void main(String[] args) throws Exception {

//        StringBuilder targetBuilder = new StringBuilder();
        StringBuilder targetBuilder = new StringBuilder();
        StringBuilder realBuilder = new StringBuilder();
        String symbolStr = targetBuilder.append("你好").append("设定值(不等式)").toString();
        String targetStr = targetBuilder.append("hello").append("设定的阈值").toString();
        String realStr = realBuilder.append("hello").append("真实值").toString();
        System.out.println(symbolStr);
        System.out.println(targetStr);
        System.out.println(realStr);



    }


}
