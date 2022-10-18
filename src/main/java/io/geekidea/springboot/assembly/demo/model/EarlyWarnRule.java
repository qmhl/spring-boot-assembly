package io.geekidea.springboot.assembly.demo.model;

import lombok.Data;

/**
 * @author zhangqi
 * @Description: 预警对象的规则
 * @date 2022/6/13 16:52
 */
@Data
public class EarlyWarnRule {


    /**
     * 指标
     */
    private String target;
    /**
     * 规则名称
     */
    private String ruleId;
    /**
     * 规则内容
     */
    private String ruleName;
    /**
     * 设定的目标值
     */
    private String targetValue;
    /**
     * 真实值
     */
    private String realValue;
    /**
     * true：预警标红 false:绿色
     */
    private Boolean flag;


//    {
//        "target ": "QCR",
//            "ruleId": "规则1",
//            "ruleName": "一级品类下的skuQCR 大于3500",
//            "targetValue": "大于3500",
//            "realValue": "3900",
//            "flag": true     // true：预警标红 false:绿色
//
//    }
}
