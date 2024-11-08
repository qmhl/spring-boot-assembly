package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://blog.csdn.net/weixin_41552767/article/details/107662821?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-107662821-blog-124467012.pc_relevant_layerdownloadsortv1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-107662821-blog-124467012.pc_relevant_layerdownloadsortv1&utm_relevant_index=5
 */
@Slf4j
public class Test52 {

    public static final String YOY_MOM = "--";
    public static final String NAN = "nan";

    public static void main(String[] args) {

        Map<String, Double> map = new HashMap<>();

        map.put("order_completeness", Double.NaN);
        System.out.println(map);
        System.out.println(JSON.toJSONString(map));
        System.out.println("===========");
        System.out.println(map.get("order_completeness"));
        System.out.println(map.get("order_completeness") + "");
        System.out.println(map.get("order_completeness").equals(Double.NaN));
        System.out.println(map.get("order_completeness").isNaN());
//        System.out.println(map.get("order_completeness") + "");
//        String str = "0.123";
//        String s = transValueWithPercent(str);
//        System.out.println(s);
        if(map.get("order_completeness").isNaN()){
            System.out.println("is NaN");
        }else{
            System.out.println("not NaN");
        }
        Double nowValue = map.get("order_completeness");
        if (nowValue == null || nowValue.isNaN() || nowValue.equals(Double.NaN)) {

            System.out.println("nan >>>>>>>");
            System.out.println("nowValue : "+nowValue);
        }
    }


    public static String transValueWithPercent(String value) {
        log.info("transValueWithPercent value  is {}", value);
        String str = "--";
        //如果计算方式是：分子/分母方式的  *100%

        if (!YOY_MOM.equals(value) && !NAN.equals(value)) {
            DecimalFormat df = new DecimalFormat("0.00");
//            str=df.format(Double.valueOf(value));
            str = df.format(Double.valueOf(value)*100) + "%";
        }

        return str;
    }


}



