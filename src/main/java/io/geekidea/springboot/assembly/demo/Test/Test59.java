package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

@Slf4j
public class Test59 {

    public static void main(String[] args) {
        double pow = Math.pow(10, -2);
        int num = 2;
        String str = "0.";
        for (int i = 0; i < num; i++) {
            str += "#";
        }
        System.out.println(str);
        DecimalFormat df = new DecimalFormat(str);//保留两位小数
//        DecimalFormat df = new DecimalFormat("00.##");//保留两位小数
        String value ="1.10";
        String result = df.format(Double.valueOf(value));
        System.out.println(result);


    }

//    public static String getStr(String str) {
//        BigDecimal db = new BigDecimal(str);
//        String ii = db.toPlainString();
//        return ii;
//    }

    public static String getStr(int num) {
        String str = "0.";
        for (int i = 0; i < num; i++) {
            str += "#";
        }
        System.out.println(str);
        return str;
    }




}



