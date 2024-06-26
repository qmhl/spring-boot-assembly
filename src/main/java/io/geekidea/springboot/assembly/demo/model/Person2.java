package io.geekidea.springboot.assembly.demo.model;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person2 {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private Integer age;

    private String addr;

}
