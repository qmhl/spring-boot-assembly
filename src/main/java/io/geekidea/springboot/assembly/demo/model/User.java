package io.geekidea.springboot.assembly.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String address;
    private int age;
}
