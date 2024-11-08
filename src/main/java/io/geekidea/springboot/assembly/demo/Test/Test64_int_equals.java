package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import io.geekidea.springboot.assembly.demo.entity.ReTrendInsightSqlResDTO;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test64_int_equals {

    public static void main(String[] args) throws ParseException {
        Integer cid = 654;
        String strLIst = "[{\"avgGmv\":31.450815448148564,\"avgYoy\":10.614647850869623,\"cid\":829,\"sourceName\":\"卖点\"},{\"avgGmv\":673.3451564225253,\"avgYoy\":3.912908833271993,\"cid\":654,\"sourceName\":\"卖点\"}]";
        List<ReTrendInsightSqlResDTO> avgInfoList = JSONObject.parseArray(strLIst, ReTrendInsightSqlResDTO.class);

//        ReTrendInsightSqlResDTO cidInfo = avgInfoList.stream().filter(x -> "卖点".equals(x.getSourceName()) && cid == x.getCid()).collect(Collectors.toList()).stream().findFirst().get();
        Double a = 0d;
        for (ReTrendInsightSqlResDTO o : avgInfoList) {
            if (o.getCid().equals(cid) && o.getSourceName().equals("卖点")) {
                a = o.getAvgGmv();
                break;
            }
        }
        System.out.println(JSON.toJSONString(a));

        Integer a1 = 300;
        Integer b1 = 300;
        System.out.println(a1 == b1);
        System.out.println(a1.equals(b1));
        System.out.println(a1 == 300);

        System.out.println("==================");
        Integer a11 = 11;
        Integer b11 = 11;
        System.out.println(a11 == b11);
        System.out.println(a11.equals(b11));
        System.out.println(a11 == 11);

    }


}



