package io.geekidea.springboot.assembly.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class CrowdRuleEntity {

    private int schemaVersion;       // 规则版本，若以后对本结构有更新，则修改此字段值（getSchemaVersion返回的值）

    private String type;         // 类型：1-逻辑运算符（交并差）、2-标签、3-已有群组（bitmap）、4-人群包/商品包（url）
                                     //      5-自定义sql、6-多标签表达式计算
    private String combineType; // 组合类型（AND/OR/NOT），只有type=1时，此字段不为空

    private Long labelId;            // 标签ID
    private String nameEn;           // 标签英文名（唯一）
    private String labelType;        // 标签类型：数值型/枚举型/日期型/文本型/复合枚举型
    private String ckTable;          // 标签存储的ck表（分布式表）
    private String ckDt;             // 标签在ck中的dt
    private Integer ckVer;           // 标签在ck中的版本

    private List<BaseValue> values;   // 选择的标签值（list之间是或关系）
    private String value;            // 已有人群ID/自定义sql/上传包url

//    private List<CrowdRuleEntity> entityList;  // 非叶子结点的子结点
}

// 表达的简单类标签值
 class BaseValue {
    private String expression;       // 标签值表达式
//    private OperatorType operator;   // 操作类型：2-小于、3-大于、4-开区间、5-小于等于、6-大于等于、7-闭区间、8-左开右闭、
                                     // 9-左闭右开、10-等于、11-不等于、12-like、13-not like、14-in、15-not in 
    private List<String> values;     // 操作值
}
// 表达的复合类标签值
 class DimValue extends BaseValue {
    private String dim;              // 标签维度（如：品类偏好程度中的品类ID）
}
