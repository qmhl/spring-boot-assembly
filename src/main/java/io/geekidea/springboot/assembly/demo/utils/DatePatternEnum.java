package io.geekidea.springboot.assembly.demo.utils;

import lombok.Getter;

/**
 * 时间格式化枚举
 * Created by shenhaonan on 2017/6/13.
 */
@Getter
public enum DatePatternEnum {

    DATE_FORMAT("yyyy-MM-dd"),
    MONTH_FORMAT("MM-dd"),
    DATE_FORMAT_24H("yyyy-MM-dd HH:mm:ss"),
    DATE_FORMAT_24H_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
    DATE_FORMAT_24H_TIGHT("yyyyMMddHHmmss"),
    DATE_FORMAT_12H("yyyy-MM-dd hh:mm:ss"),
    DATE_FORMAT_WEEK("yyyy-ww"),
    DATE_FORMAT_MONTH("yyyy-MM"),
    DATE_FORMAT_00H("yyyy-MM-dd 00:00:00"),
    DATE_FORMAT_23H("yyyy-MM-dd 23:59:59"),
    DATE_FORMAT_HH("yyyy-MM-dd HH"),
    DATE_FORMAT_XIAO_HH_SS("mm:ss"),
    DATE_FORMAT_MONTH1("yyyyMM");

    public String pattern;

    DatePatternEnum(String pattern) {
        this.pattern = pattern;
    }
}
