package io.geekidea.springboot.assembly.demo.Test;

import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test4 {
    public static void main(String[] args) throws ParseException {
//        String s ="271204551";
//        Float aFloat = Float.valueOf(s);
//        System.out.println(aFloat);

        List<Integer> inner = new ArrayList<>();
//        inner.add(670);
//        inner.add(686);
//        inner.add(1047);
        List<List<Integer>> cateIds = new ArrayList<>();
        cateIds.add(inner);
        System.out.println(cateIds);
        //获取内层最后一级的id
        List<Integer> cateIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cateIds)) {
            for (List<Integer> innerCateIds : cateIds) {
                if (!CollectionUtils.isEmpty(innerCateIds) && innerCateIds.get(innerCateIds.size() - 1) != null) {
                    cateIdList.add(innerCateIds.get(innerCateIds.size() - 1));
                }
            }
        }
        System.out.println(cateIdList);
    }


}
