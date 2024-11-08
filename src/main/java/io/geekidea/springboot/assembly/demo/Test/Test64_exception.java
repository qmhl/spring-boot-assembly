package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.geekidea.springboot.assembly.demo.entity.ReTrendInsightSqlResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test64_exception {

    public static void main(String[] args) throws ParseException {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> integers1 = integers.subList(0, 7);
        System.out.println(integers.size());
        System.out.println(integers1.size());

        System.out.println(formatOne(1.22d));
        System.out.println(formatZero(1.22d));
        Double d=1.0d;
        System.out.println(d);

        Long erp = desensitizationData("erp", Double.valueOf(1276543));
        System.out.println(erp);

        List<ReTrendInsightSqlResDTO> list = new LinkedList<>();
        ReTrendInsightSqlResDTO dto = new ReTrendInsightSqlResDTO();
        dto.setYoy(0.123d);
        dto.setPercent(0.13323d);
        list.add(dto);
        List<ReTrendInsightSqlResDTO> collect = tran2PercentYoYForTrendInsight(list);
        System.out.println(collect);


    }

    public static String formatOne(Double d) {
        Double d100 = d * 100;
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(d100);
    }

    /**
     * 取整
     *
     * @param d
     * @return
     */
    public static String formatZero(Double d) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(d);
    }


    public static Long desensitizationData(String type, Double d) {
        DecimalFormat df = new DecimalFormat("0");
        if ("pin".equals(type)) {
            return Long.valueOf(df.format(d / 1234));
        } else {
            return Long.valueOf(df.format(d));
        }
    }


    private static List<ReTrendInsightSqlResDTO> tran2PercentYoYForTrendInsight(List<ReTrendInsightSqlResDTO> list) {
        List<ReTrendInsightSqlResDTO> collect = list.stream().map(o -> {
            ReTrendInsightSqlResDTO dto = new ReTrendInsightSqlResDTO();
            BeanUtils.copyProperties(o, dto);
            dto.setYoy(Double.valueOf(multi100FormatOne(o.getYoy())));
            dto.setPercent(Double.valueOf(multi100FormatOne(o.getPercent())));
            return dto;
        }).collect(Collectors.toList());
        return collect;
    }

    public static String multi100FormatOne(Double d) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(d * 100);
    }
}



