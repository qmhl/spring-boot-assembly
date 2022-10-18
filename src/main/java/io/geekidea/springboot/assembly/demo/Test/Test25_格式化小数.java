package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.EarlyWarnRule;
import com.example.demo.model.IndexEnum;
import com.example.demo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 */
@Slf4j
public class Test25_格式化小数 {
    private static Object Person;

    public static void main(String[] args) throws Exception {
//        String str = "0.023000";
//        String str1 = "0.000789";
//        String str2 = "0.1234567";
//        System.out.println(trans2Percent(str));
//        System.out.println(trans2Percent(str1));
//        System.out.println(trans2Percent(str2));
//
//
//        String s = trans2RationalNum("3001.54");
//        System.out.println(s);
        String str = "[{\"target\":\"商品差评率_自营\",\"ruleId\":\"规则K\",\"ruleName\":\"当<span>SKU</span><span>上三个月</span>的<span>商品差评率_自营</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"售后申请率分子\",\"ruleId\":\"规则L\",\"ruleName\":\"当<span>SKU</span><span>近30天</span>的<span>售后申请率分子</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"售后申请率分母\",\"ruleId\":\"规则M\",\"ruleName\":\"当<span>SKU</span><span>近30天</span>的<span>售后申请率分母</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"商品差评率_自营\",\"ruleId\":\"规则N\",\"ruleName\":\"当<span>SKU</span><span>近30天</span>的<span>商品差评率_自营</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"商品差评率_自营\",\"ruleId\":\"规则O\",\"ruleName\":\"当<span>SKU</span><span>近7天</span>的<span>商品差评率_自营</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"QCR\",\"ruleId\":\"规则A\",\"ruleName\":\"当<span>SKU</span><span>上三个月</span>的<span>QCR</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"6525\",\"flag\":true},{\"target\":\"QCR分子\",\"ruleId\":\"规则B\",\"ruleName\":\"当<span>SKU</span><span>上三个月</span>的<span>QCR分子</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"QCR分母\",\"ruleId\":\"规则C\",\"ruleName\":\"当<span>SKU</span><span>近30天</span>的<span>QCR分母</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"567\",\"flag\":true},{\"target\":\"QCR分母\",\"ruleId\":\"规则D\",\"ruleName\":\"当<span>SKU</span><span>近30天</span>的<span>QCR分母</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"567\",\"flag\":true},{\"target\":\"售后申请率\",\"ruleId\":\"规则E\",\"ruleName\":\"当<span>SKU</span><span>近7天</span>的<span>售后申请率</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"商品差评率_自营\",\"ruleId\":\"规则F\",\"ruleName\":\"当<span>SKU</span><span>近7天</span>的<span>商品差评率_自营</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"商品差评率分子_自营\",\"ruleId\":\"规则G\",\"ruleName\":\"当<span>SKU</span><span>近30天</span>的<span>商品差评率分子_自营</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"商品差评率_自营\",\"ruleId\":\"规则H\",\"ruleName\":\"当<span>SKU</span><span>上三个月</span>的<span>商品差评率_自营</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"售后申请率分母\",\"ruleId\":\"规则I\",\"ruleName\":\"当<span>SKU</span><span>近30天</span>的<span>售后申请率分母</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false},{\"target\":\"售后申请率分母\",\"ruleId\":\"规则J\",\"ruleName\":\"当<span>SKU</span><span>上三个月</span>的<span>售后申请率分母</span><span>大于</span><span>100</span>时\",\"targetValue\":\"大于100\",\"realValue\":\"0\",\"flag\":false}] ";

        String s = handleIndex(str);
        System.out.println(s);

    }


    public static String trans2Percent(String str) {
        if (StringUtils.isEmpty(str)) {
            return "0.####%";
        }
        DecimalFormat df = new DecimalFormat("0.####%");
        return (df.format(Double.valueOf(str)));
    }


    public static String trans2RationalNum(String str) {
        if (StringUtils.isEmpty(str)) {
            return "0";
        }
        return String.valueOf(Math.round(Double.valueOf(str)));
    }


    private static String handleIndex(String rule) {
        // 对规则进行处理
        if (StringUtils.isEmpty(rule)) {
            return "";
        }
        List<EarlyWarnRule> ruleList = JSONObject.parseArray(rule, EarlyWarnRule.class);
        if (!CollectionUtils.isEmpty(ruleList)) {
            ruleList.stream().forEach(x -> {
                //售后申请率/商品差评率_自营 展示百分比； 其他保持整数不变
                if (IndexEnum.AFTER_SALE.getLabel().equals(x.getTarget()) || IndexEnum.NEGATIVE_COMMENT.getLabel().equals(x.getTarget())) {
                    //展示百分比
                    x.setRealValue(trans2Percent(x.getRealValue()));
                    x.setTargetValue(handleOtherRate(x.getTargetValue()));
                }
            });
            return JSON.toJSONString(ruleList);
        } else {
            return JSON.toJSONString(new LinkedList<Object>());
        }

    }


    public static String handleOtherRate(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException("param is null");
        }
        String num = StringUtil.getNumberWithDecimal(str);
        return str.replaceAll(num, trans2Percent(num));
    }

}
