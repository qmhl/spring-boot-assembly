package io.geekidea.springboot.assembly.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@ToString
public class Son extends Father {

    private String sex;
    private String sonName;
    private String sonAge;

    private Integer cid1; // 一级品类id
    private Integer cid2; // 二级品类id
    private Integer cid3; // 三级品类id

    final List<Integer> innerList = Arrays.asList(new Integer[3]);

    public void setSonName(String sonName) {
        this.sonName = sonName;
        super.setName(sonName);
    }

    public void setSonAge(String age) {
        this.sonAge = age;
        super.setAge(age);
    }

    public void setCid1(Integer cid1) throws Exception {

        if (cid1 == null) {
            throw new Exception("cid1不能为空");
        }
        this.cid1 = cid1;
        this.innerList.set(0, cid1);
        super.setCateList(Collections.singletonList(this.innerList));


    }

    public void setCid2(Integer cid2) throws Exception {

        if (cid2 == null) {
            throw new Exception("cid2不能为空");
        }

        this.cid2 = cid2;
        this.innerList.set(1, cid2);
        super.setCateList(Collections.singletonList(this.innerList));
    }

    public void setCid3(Integer cid3) throws Exception {

        if (cid3 == null) {
            throw new Exception("cid3不能为空");
        }

        this.cid3 = cid3;
        this.innerList.set(2, cid3);
        super.setCateList(Collections.singletonList(this.innerList));

    }
}
