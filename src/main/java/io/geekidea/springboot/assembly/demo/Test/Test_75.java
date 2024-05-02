package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Test_75 {


    public static void main(String[] args) throws Exception {
        List<Integer> list1 = Arrays.asList(1, 4, 7, 11, 10, 100, 9);
        System.out.println(list1);

        Collections.sort(list1);
        System.out.println(list1);

        Collections.reverse(list1);
        System.out.println(list1);

        Double i = null;
        System.out.println(i);
        //1678961303880
        //1684996011762
        System.out.println(System.currentTimeMillis());
        System.out.println(humpToLine2("tansAbc"));

        String str = "{\"opList\":[{\"opType\":\"0\",\"businessTypeList\":[\"999\"],\"groupName\":\"商品规则\",\"code\":\"lastCateGoodCommentRateRank\",\"rules\":[{\"code\":\"test1\",\"hint\":\"test1\",\"name\":\"test1\"}]}]}";
        Map<String, Object> map = JSON.parseObject(str, Map.class);
        if (!map.containsKey("opList") && !map.containsKey("erpNo")) {
            System.out.println(">>>>");
        } else {
            System.out.println("error");
        }



        System.out.println("=====================");
        System.out.println(createRuleId());

        System.out.println("=====================");
        System.out.println(getNumeric("eer"));

        System.out.println("=====================");

        String skuId="1,2,4,6";
        ArrayList<String> strings = Lists.newArrayList(skuId);
        System.out.println(strings);
    }

    public static String createRuleId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
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

    public static String getNumeric(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}



