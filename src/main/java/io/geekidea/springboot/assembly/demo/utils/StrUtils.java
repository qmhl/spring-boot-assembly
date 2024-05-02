package io.geekidea.springboot.assembly.demo.utils;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoxu
 * @date 2022-11-19 21:52
 * crawlerJ:com.xiaoxu.crawler.utils.StringUtils
 */
public class StrUtils {

    public static void main(String[] args) {
        String camel= underline2Camel("l2_discount_15_31993");
        String line = humpToLine2(camel);
        System.out.println(camel);
        System.out.println(line);
    }
    //下划线转驼峰
    public static String underline2Camel(String underline){
        Pattern pattern = Pattern.compile("[_]\\w");
        Matcher matcher = pattern.matcher(underline);
        while(matcher.find()){
            String w = matcher.group().trim();
            underline = underline.replace(w,w.toUpperCase().replace("_", ""));
        }
        return underline;
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


}
