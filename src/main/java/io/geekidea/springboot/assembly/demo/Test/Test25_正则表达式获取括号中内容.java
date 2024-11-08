package io.geekidea.springboot.assembly.demo.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test25_正则表达式获取括号中内容 {
    public static void main(String[] args) {
        String msg = "PerformanceManager（第1个中括号）";
//        List<String> list = extractMessageByRegular(msg);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(i + "-->" + list.get(i));
//        }

        getStrContainData(msg, "（", "）");

    }

    /**
     * 使用正则表达式提取中括号中的内容
     *
     * @param msg
     * @return
     */
    public static List<String> extractMessageByRegular(String msg) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile("\\（.*\\）");
        Matcher m = p.matcher(msg);
        while (m.find()) {
            list.add(m.group().substring(1, m.group().length() - 1));
        }
        return list;
    }


    //暴力截取
    public void getStrs(String str) {
        String[] strs = str.split("\\{");
        System.out.println("第一种解法:");
        for (String s : strs) {
            if (s.indexOf("}") > -1) {
                System.out.println(s.substring(0, s.indexOf("}")));
            }
        }
    }

    //序列化
    public static void getStrContainData(String str, String start, String end) {
//        String regex = "\\" + start + "(.*?)" + "\\" + end;
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(str);
        System.out.println("第二种解法:");
        if (str.contains(start)) {
            System.out.println("括号外内容： " + str.substring(0, str.indexOf(start)));
        }

        if (str.contains(end)) {
            System.out.println("括号中内容： " + str.substring(str.indexOf(start)+1, str.indexOf(end)));
        }
//        if (matcher.find()) {
//            String key = matcher.group(1);
//            System.out.println("括号中内容： " + key);
//        }

    }
}