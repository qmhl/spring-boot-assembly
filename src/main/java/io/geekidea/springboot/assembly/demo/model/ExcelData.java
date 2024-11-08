package io.geekidea.springboot.assembly.demo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelData {
    @ExcelProperty("列1名称")
    private String column1;
 
    @ExcelProperty("列2名称")
    private String column2;
 
    // 省略getter和setter方法
}