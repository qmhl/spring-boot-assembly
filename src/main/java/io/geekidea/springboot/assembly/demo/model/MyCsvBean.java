package io.geekidea.springboot.assembly.demo.model;

import java.util.Objects;
 
public class MyCsvBean {
    private String column1;
    private String column2;
    private String column3;
 
    // 标准的getter和setter方法
    public String getColumn1() { return column1; }
    public void setColumn1(String column1) { this.column1 = column1; }
    public String getColumn2() { return column2; }
    public void setColumn2(String column2) { this.column2 = column2; }
    public String getColumn3() { return column3; }
    public void setColumn3(String column3) { this.column3 = column3; }
 
    // 省略equals()和hashCode()方法
}