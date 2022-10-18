package io.geekidea.springboot.assembly.demo.Test;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * https://blog.csdn.net/justdoit_potato/article/details/120022084
 */
@Slf4j
public class Test35_两个list对象合并成一个 {
    private final static String EXCEL_DIR = "/export/Data/textsearch/excel/";
    private final static String DATETIME_PATTERN = "yyyyMMddHHmmssSS";

    public static void main(String[] args) throws Exception {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        List<Integer> list = Arrays.asList(array);
        System.out.println(getPagedList(4, 3, list));

    }

    public static List getPagedList(Integer nowPage, Integer pageSize, List data) {
        int fromIndex = (nowPage - 1) * pageSize;
        int toIndex = nowPage * pageSize;
        if (toIndex >= data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);
    }
}