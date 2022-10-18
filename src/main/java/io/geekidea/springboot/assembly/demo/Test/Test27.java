package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 */
@Slf4j
public class Test27 {
    private static Object Person;

    public static void main(String[] args) throws Exception {

        String  str1="科沃斯（ecovacs）";
//        String str = "[tid:2656,Q:W183CWbcBewrSgccJOdw] ";
//        //String substring = str.substring(0, 10);
//        //System.out.println(substring);
//        //String sub001 = str.substring(0 ,10);
//        //System.out.println(sub001);
//        String substring = str1.substring(str1.indexOf("（")+1, str1.indexOf("）"));
        //可行
//        String substring = str1.substring(0, str1.indexOf("（"));
//        System.out.println(substring);
        System.out.println(interceptSymbol(str1, Arrays.asList("(", "（")));



    }


    public static String interceptSymbol(String str, List<String> symbolList) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException("param is empty");
        }
        for (String symbol : symbolList) {
            if (str.contains(symbol)) {
                return str.substring(0, str.indexOf(symbol));
            }
        }
        return str;
    }


}
