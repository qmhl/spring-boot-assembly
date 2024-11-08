package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.entity.ReTrendInsightSqlResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.sql.Array;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test65 {

    public static void main(String[] args) throws ParseException {
//        Long a=223098L;
//        Long b=713309L;
//        System.out.println(a/b);
//        System.out.println(multi100FormatZero((double)a/(double)b));
//
//        double aa=223098d;
//        double bb=713309d;
//        System.out.println(aa/b);

//        String o="3.0";
//        System.out.println(Long.valueOf(o));


        List<String> strings = Arrays.asList("张", "里", "王");
        List<String> list = new ArrayList<>();
        int i = 1;
//        String ii="12345677";
//        System.out.println(ii.substring(0,4));
//        System.out.println(ii.substring(4,5));
//        System.out.println(ii.substring(,4));
//
        String s = multi100FormatZero(6562.0);
        System.out.println(s);
        System.out.println(Double.valueOf(s));
        System.out.println(s.length());
//        String e = multi100FormatZero(123456.);
//        System.out.println(e);
//        System.out.println(e.length());
    }


    public static String multi100FormatZero(Double d) {
        DecimalFormat df = new DecimalFormat("0.0");
//        return df.format(d * 100);
        String s = df.format(d * 100);
        System.out.println("s>>>" + s);
        if (s.length() >6) {
            return s.substring(0, 4) + "." + s.substring(4, 5);
        } else {
            return s;

        }
    }
}



