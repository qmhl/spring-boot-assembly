package io.geekidea.springboot.assembly.demo.Test;

import com.google.common.base.Splitter;
import io.geekidea.springboot.assembly.demo.model.Entry;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test63_拼接sql片段 {

    public static void main(String[] args) throws ParseException {
        String str = "1-100,101-200,201-300";
        String s = generateSql(str);
        System.out.println(s);
    }


    private static List<List<String>> handlePrice(String price) {
        List<String> priceList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(price);
        return priceList.stream().map(o -> {
            return Splitter.on("-").omitEmptyStrings().trimResults().splitToList(o);
        }).collect(Collectors.toList());
    }


    private static String generateSql(String price) {
        //when `sku_price` &gt;= priceList[0] and `sku_price` &lt;= priceList[1] then concat(toString(priceList[0]),'-',toString(priceList[1]))
        String start = "case ( ";
        String end = "end )";
        StringBuffer res = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        List<List<String>> priceList = handlePrice(price);
        for (List<String> list : priceList) {
            sb.append("when `sku_price` &gt;= ").append(list.get(0)).append(" and `sku_price` &lt;= ").append(list.get(1)).append(" then concat(toString(").append(list.get(0)).append("),'-',").append("toString(").append(list.get(1)).append(")) ");
        }
        return res.append(start).append(sb).append(end).toString();
    }

}



