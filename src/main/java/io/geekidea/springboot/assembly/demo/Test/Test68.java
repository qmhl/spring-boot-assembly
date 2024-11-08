package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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


//        String str1="";
//        System.out.println(Integer.valueOf(str1));

        Integer aa = null;
        System.out.println(aa);

        String url="https://selection-platform.s3.cn-north-1.jdcloud-oss.com/xycenter/202304061553235914885825101467536718.txt";
        System.out.println("url: "+cutUrlKey(url));
    }


    public static  String cutUrlKey(String url) {
        String[] split = StringUtils.split(url,"/");
        System.out.println(JSON.toJSONString(split));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0,len = split.length;i < len; i++) {
            if (i > 1) {
                stringBuilder.append("/").append(split[i]);
            }
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.substring(1);
    }
}



