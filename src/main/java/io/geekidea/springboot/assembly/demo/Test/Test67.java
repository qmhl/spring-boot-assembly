package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import io.geekidea.springboot.assembly.demo.entity.ReCateSqlResDTO;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test67 {

    public static void main(String[] args) throws ParseException {
//        List<ReCateSqlResDTO> sourceList = new LinkedList<>();
//        ReCateSqlResDTO q1 = new ReCateSqlResDTO();
//        q1.setPriceLevel("1-100");
//
//        ReCateSqlResDTO q2 = new ReCateSqlResDTO();
//        q2.setPriceLevel("100-1000");
//
//        ReCateSqlResDTO q3 = new ReCateSqlResDTO();
//        q3.setPriceLevel("1000-999999999");
//
//        sourceList.add(q1);
//        sourceList.add(q2);
//        sourceList.add(q3);
//
//        //将排序后的最后一个元素的价格带中的n-99999等转为n+
//        List<ReCateSqlResDTO> resList = new LinkedList<>();
//        ReCateSqlResDTO reCateSqlResDTO = sourceList.get(sourceList.size() - 1);
//        List<String> lastPriceList = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(reCateSqlResDTO.getPriceLevel());
//        String finalPrice = lastPriceList.get(0) + "+";
//        reCateSqlResDTO.setPriceLevel(finalPrice);
//        resList.addAll(sourceList.subList(0, sourceList.size() - 1));
//        resList.add(reCateSqlResDTO);
//        log.info("resList ->{}", JSON.toJSONString(resList));

        List<List<String>> lists = handlePrice("1-100,200以上,300-800");
        System.out.println(lists);
    }


    private static List<List<String>> handlePrice(String price) {
        List<String> priceList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(price);
//        List<List<String>> res = new ArrayList<>();
//        for (String o : priceList) {
//            if (o.contains("以上")) {
//                 o = o.replaceAll("以上", "-999999999");
//            }
//            List list =Splitter.on("-").omitEmptyStrings().trimResults().splitToList(o);
//            res.add(list);
//        }
//        return res;
        return priceList.stream().map(o -> {
            if (o.contains("以上")) {
                o = o.replaceAll("以上", "-999999999");
            }
            return Splitter.on("-").omitEmptyStrings().trimResults().splitToList(o);
        }).collect(Collectors.toList());
    }
}



