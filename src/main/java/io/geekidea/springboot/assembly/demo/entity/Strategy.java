package io.geekidea.springboot.assembly.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Strategy implements Serializable {

    // 策略场景id
    @ExcelProperty("id")
    private Integer id;

    // 策略名称
    @ExcelProperty("name")
    private String name;

    // 描述
    @ExcelProperty("description")
    private String description;

    // 案例场景
    @ExcelProperty("caseScene")
    private String caseScene;

    // 规则
    @ExcelProperty("ruleUrl")
    private String ruleUrl;

    // 类型：1-通用 2-自定义
    @ExcelProperty("type")
    private Integer type;

    // 场景类型：猜你喜欢、内容分发、内容精选、商品素材、用户洞察
    @ExcelProperty("sceneType")
    private String sceneType;

    // 创建人
    @ExcelProperty("creator")
    private String creator;

    // 修改人
    @ExcelProperty("modifier")
    private String modifier;

    // 状态,0：无效，1：有效
    @ExcelProperty("status")
    private Integer status;

    // 创建时间
    @ExcelProperty("createTime")
    private String createTime;

    // 修改时间
    @ExcelProperty("modifyTime")
    private String modifyTime;

}