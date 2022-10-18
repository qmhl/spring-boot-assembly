package io.geekidea.springboot.assembly.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Father {

    private String name;
    private String age;
    private Integer phone;

    private List<List<Integer>> cateList;


}
