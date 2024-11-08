package io.geekidea.springboot.assembly.demo.model;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class TestBean {
    private String name;
    private Integer age;
    private List<String> list;
}
