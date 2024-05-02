package io.geekidea.springboot.assembly.demo.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class MyObject {
    @CsvBindByName
    private String name;
 
    @CsvBindByName
    private int age;
 
    // 标准的getter和setter
    // ...
}