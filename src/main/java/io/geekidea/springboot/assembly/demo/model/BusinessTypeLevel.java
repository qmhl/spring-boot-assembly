package io.geekidea.springboot.assembly.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class BusinessTypeLevel {

    private Integer level;

//    private Integer level;

    private List<String> level1List;

    private List<String> level2List;
}
