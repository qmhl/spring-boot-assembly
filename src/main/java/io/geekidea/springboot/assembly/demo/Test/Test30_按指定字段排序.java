package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.PersonInsightSqlResDTO;
import io.geekidea.springboot.assembly.demo.utils.DimSortUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://blog.csdn.net/justdoit_potato/article/details/120022084
 */
@Slf4j
public class Test30_按指定字段排序 {


    public static void main(String[] args) throws Exception {
//        Double d = 0.0D;
//        System.out.println(d);
//        if (d > 0) {
//            System.out.println("大于0");
//        }

        List<String> list1 = Arrays.asList("11", "22", "33", "44", "55");
        List<String> list2 = Arrays.asList("11", "122", "133", "44", "55");
        method1(list1, list2);

        List<PersonInsightSqlResDTO> preList = new ArrayList<>();
        //"一线", "二线", "三线", "四线", "五线", "六线-1"
        preList.add(new PersonInsightSqlResDTO("pre", "一线", 11d, 22d));
        preList.add(new PersonInsightSqlResDTO("pre", "三线", 33d, 22d));
        preList.add(new PersonInsightSqlResDTO("pre", "五线", 55d, 22d));
        preList.add(new PersonInsightSqlResDTO("pre", "二线", 22d, 22d));
        preList.add(new PersonInsightSqlResDTO("pre", "六线-1", 66d, 22d));

        List<PersonInsightSqlResDTO> postList = new ArrayList<>();
        //"一线", "二线", "三线", "四线", "五线", "六线-1"
        postList.add(new PersonInsightSqlResDTO("post", "一线", 111d, 22d));
        postList.add(new PersonInsightSqlResDTO("post", "二线", 333d, 22d));
        postList.add(new PersonInsightSqlResDTO("post", "三线", 555d, 22d));
        postList.add(new PersonInsightSqlResDTO("post", "五线", 222d, 22d));
        postList.add(new PersonInsightSqlResDTO("post", "六线-1", 666d, 22d));
        method2(preList);
        customSortList(preList, "pre","cityLevel");
        log.info("排序后的{}", JSON.toJSONString(preList));

        customSortList(postList, "post","cityLevel");
        log.info("排序后的{}", JSON.toJSONString(postList));
    }

    public static void method1(List<String> list1, List<String> list2) {
        List<String> finalList = list1.stream().filter(o -> !list2.contains(o)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(finalList));
    }

    public static void method2(List<PersonInsightSqlResDTO> preList) {
        List<String> collect = preList.stream().map(PersonInsightSqlResDTO::getDimKey).collect(Collectors.toList());
        List<String> cityList = DimSortUtil.getDimMap().get("cityLevel");
        cityList.stream().forEach(o -> {
                    if (!collect.contains(o)) {
                        preList.add(new PersonInsightSqlResDTO("pre", o, 0d, 0d));
                    }
                }
        );
        System.out.println(JSON.toJSONString(preList));
    }


    private static void customSortList(List<PersonInsightSqlResDTO> sourceList, String type, String dim) {
        if (CollectionUtils.isNotEmpty(sourceList)) {
            List<String> dimList = sourceList.stream().map(PersonInsightSqlResDTO::getDimKey).collect(Collectors.toList());
            // 指定字段排序
            List<String> customSortList = DimSortUtil.getDimMap().get(dim);
            customSortList.stream().forEach(o -> {
                        //补全结果集  缺少值的占比和TGI补全值为0
                        if (!dimList.contains(o)) {
                            sourceList.add(new PersonInsightSqlResDTO(type, o, 0d, 0d));
                        }
                    }
            );
            sourceList.sort(Comparator.comparing(e -> customSortList.indexOf(e.getDimKey())));
        }

    }

}
