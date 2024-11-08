package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.PersonInsightSqlResDTO;
import io.geekidea.springboot.assembly.demo.utils.DimSortUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://blog.51cto.com/u_15127658/4334611
 */
@Slf4j
public class Test30_按指定字段排序2 {

    public static void main(String[] args) {
        String[] regulation = {"诸葛亮","鲁班","貂蝉","吕布"};
        final List<String> regulationOrder = Arrays.asList(regulation);
        String[] ordered = {"貂蝉","诸葛亮","吕布","貂蝉","鲁班","诸葛亮","貂蝉","鲁班","诸葛亮"};
        // 待排序字段
        List<String> orderedList = Arrays.asList(ordered);
        Collections.sort(orderedList, new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                int io1 = regulationOrder.indexOf(o1);
                int io2 = regulationOrder.indexOf(o2);
                return io1 - io2;
            }
        });
        System.out.println(orderedList);
    }

}
