package io.geekidea.springboot.assembly.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sunb
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DimensionOrMeasure {
    private String name;
    private List<Object> data;// 默认 是降序排序
    private List<Object> reverseData; //升序排序
}