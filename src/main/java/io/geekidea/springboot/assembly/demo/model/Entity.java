package io.geekidea.springboot.assembly.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 配置中心底池配置-标签组合实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Entity {

    private String name; // 标签中文名、逻辑运算符、括号
    private String nameEn; // 标签英文名
    private String labelType; // 标签类型，枚举型、日期型
    private Object value; // 标签值列表（old）
    private List<String> valueDesc; // 标签值描述
    private String operator; // 操作符 参考OperationType
    private String entityId; // 实体id
    private String entityName; // 实体名称
    private String type; // 标签类型 symbol
    private boolean leftBracket; // 是否左括号
    private boolean rightBracket; // 是否右括号
    private boolean and; // 是否 AND 计算符
    private boolean or; // 是否 OR 计算符
    private boolean not; // 是否 NOT 计算符

    public static final String LEFT_BRACKET = "LeftBracket";
    public static final String RIGHT_BRACKET = "RightBracket";
    public static final String AND = "and";
    public static final String OR = "or";
    public static final String NOT = "not";

}
