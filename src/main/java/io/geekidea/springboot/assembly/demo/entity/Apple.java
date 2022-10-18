package io.geekidea.springboot.assembly.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Apple {
    private int weight;
    private String color;
}