package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.RelateAnalyse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class Test57 {
    public static final String YOY_MOM="--";
    public static final String NAN="nan";
    public static void main(String[] args) {
//        String s = calDivide(82794.0,2.1707428E7);
        String s = calDivide(34692D,8121070D);
        System.out.println(s);
    }


    private static String calDivide(Double value1, Double value2) {
        log.info("calDivide v1 :{} ,v2 ：{}", value1, value2);
        DecimalFormat df = new DecimalFormat("0.0000");
        if (value1 == null || value1.isNaN() || value1.isInfinite()) {
            return YOY_MOM;
        }
        if (value2 == null || value2.isNaN() || value2.equals(0D) || value2.isInfinite()) {
            return YOY_MOM;
        }
        return df.format(value1 / value2);
    }


}



