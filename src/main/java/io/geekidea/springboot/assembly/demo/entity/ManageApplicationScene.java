package io.geekidea.springboot.assembly.demo.entity;

import lombok.Data;

import java.util.Date;


@Data
public class ManageApplicationScene {
    private Long id;
    private Long pid;
    private String nameCn;
    private String nameEn;
    private Integer isLeaf;
    private Integer level;
    private String description;
    private String creator;
    private String modifier;
    private Date createTime;
    private Date modifyTime;
}