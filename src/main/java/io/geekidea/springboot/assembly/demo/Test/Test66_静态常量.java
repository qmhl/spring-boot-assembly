package io.geekidea.springboot.assembly.demo.Test;

import com.google.common.base.CaseFormat;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test66_静态常量 {

    public static void main(String[] args) {
        String str1 = "web_order";
        String str2 = "WWWeb_order";
        String to = StringUtils.capitalize(str1);
        String to2 = StringUtils.uncapitalize(str2);
        System.out.println(to);
        System.out.println(to2);
//
//        String str2 = "WEB_FAVORITE";
//        String to1 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str2);
//        //                    原来的格式                  要转换的格式
//        System.out.println(to1);

    }
}



