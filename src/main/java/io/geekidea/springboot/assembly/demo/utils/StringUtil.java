package io.geekidea.springboot.assembly.demo.utils;

import com.google.common.base.Splitter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具
 *
 * @author : weibin10
 * @date : 2021/8/18 17:49
 */
public class StringUtil {
    // 整数
    private static Pattern NUM_PATTERN = Pattern.compile("(\\d+)");
    // 小数
    private static Pattern DECIMAL_PATTERN = Pattern.compile("(\\d+\\.\\d+)");

    /**
     * 字符串截断
     *
     * @param src 输入字符串
     * @param len 保留长度
     * @return 结果
     */
    public static String cutoff(String src, int len) {
        String result = src;
        if (src != null && src.length() >= len) {
            result = src.substring(0, len);
        }
        return result;
    }

    /**
     * 获取字符串中的数字
     *
     * @param src 输入字符串
     * @return 结果
     */
    public static String getNumber(String src) {
        if (StringUtils.isEmpty(src)) {
            return "";
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(src);
        return m.replaceAll("").trim();
    }


    /**
     * 获取字符串中的数字,主要是指小数
     *
     * @param str
     * @return
     */
    public static String getNumberWithDecimal(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        //先判断有没有整数，如果没有整数那就肯定就没有小数
        Matcher m = NUM_PATTERN.matcher(str);
        String result = "";
        if (m.find()) {
            Matcher d = DECIMAL_PATTERN.matcher(str);
            //如果是小数
            if (d.find()) {
                result = d.group(1) == null ? "" : d.group(1);
            } else {
                //如果是整数
                if (m.find()) {
                    result = m.group(1) == null ? "" : m.group(1);
                }
            }

        }
        return result;
    }


    /**
     * 根据指定的分隔符将字符串分割
     *
     * @param str
     * @param separator
     * @return
     */
    public static List<String> splitBySeparator(String str, String separator) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException("param is empty");
        }
        if (!str.contains(separator)) {
            throw new RuntimeException("param have not contain separator");
        }
        return Splitter.on(separator).omitEmptyStrings().trimResults().splitToList(str);
    }

    /**
     * 根据指定的分隔符将字符串分割，区分上一版，如果字符串中没有分隔符，说明只有一组数据，不抛异常
     *
     * @param str
     * @param separator
     * @return
     */
    public static List<String> split(String str, String separator) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException("param is empty");
        }
        if (!str.contains(separator)) {
            return Arrays.asList(str);
        }
        return Splitter.on(separator).omitEmptyStrings().trimResults().splitToList(str);
    }


    /**
     * 截取符号,获取符号前面的字符串内容
     *
     * @param str
     * @param
     * @return
     */
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
