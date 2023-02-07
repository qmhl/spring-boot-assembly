package io.geekidea.springboot.assembly.demo.Test;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test68 {

    public static void main(String[] args) throws ParseException {
        StringBuilder sb = new StringBuilder();

        StringBuilder sb1 = new StringBuilder();
        sb.append("nihao");
        sb.append(sb1);
        System.out.println(sb1.toString().length());
        System.out.println(sb.toString().length());

        StringBuilder sb2 = new StringBuilder();

        StringBuilder sb12 = new StringBuilder();

        sb2.append(sb12);
        System.out.println(sb12.toString().length());
        System.out.println(sb2.toString().length());

        String str=" ";
        System.out.println(str.length());


        String str1="";
        System.out.println(Integer.valueOf(str1));

        Integer aa = null;
        System.out.println(aa);

    }
}



