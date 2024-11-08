package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.entity.ActiveExtendDTO;
import io.geekidea.springboot.assembly.demo.entity.Apple;
import io.geekidea.springboot.assembly.demo.model.Person;
import io.geekidea.springboot.assembly.demo.model.PersonInsightSqlResDTO;
import io.geekidea.springboot.assembly.demo.utils.DimSortUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java 区间重叠判断
 * https://blog.csdn.net/weixin_33882452/article/details/93933386
 */
public class Test12_sort {
    public static void main(String[] args) {
//        ArrayList<Apple> inventory = Lists.newArrayList(
//                new Apple(10, "red"),
//                new Apple(5, "red"),
//                new Apple(1, "green"),
//                new Apple(15, "green"),
//                new Apple(2, "red"));
//
//         inventory.stream().sorted(Comparator.comparing(Apple::getWeight));
//        inventory.sort((a, b) -> a.getWeight() - b.getWeight());
        List<ActiveExtendDTO> list = new ArrayList<>();
        list.add(new ActiveExtendDTO(1L, 100L));
        list.add(new ActiveExtendDTO(101L, 200L));
        list.add(new ActiveExtendDTO(1L, 300L));
//        checkOverlap2(list);

        System.out.println("=========================");
        List<Person> pList = new ArrayList<>();
        pList.add(new Person("机关党建", 1));
        pList.add(new Person("部门工作", 2));
        pList.add(new Person("通知公告", 3));
        pList.add(new Person("信息公示", 4));
        customSortList(pList);
        System.out.println(JSON.toJSONString(pList));

        System.out.println("=========================");
        List<PersonInsightSqlResDTO> sourceList = new ArrayList<>();
        sourceList.add(new PersonInsightSqlResDTO("pre", "男", 11.22D, 110.67D));
        sourceList.add(new PersonInsightSqlResDTO("pre", "女", 88.88D, 110.67D));
        sourceList.add(new PersonInsightSqlResDTO("post", "女", 31.22D, 110.67D));
        sourceList.add(new PersonInsightSqlResDTO("post", "男", 51.22D, 110.67D));
        Map<String, List<PersonInsightSqlResDTO>> list22 =  sourceList.stream().collect(Collectors.groupingBy(PersonInsightSqlResDTO::getType));
        List<PersonInsightSqlResDTO> preList = list22.get("pre");
        List<PersonInsightSqlResDTO> postList = list22.get("post");
        customSortList2(preList,"sex");
        customSortList2(postList,"sex");
        System.out.println("pre: "+JSON.toJSONString(preList));
        System.out.println("post: "+JSON.toJSONString(postList));



    }

    private static void checkOverlap2(List<ActiveExtendDTO> list) {
        list.stream().sorted(Comparator.comparing(ActiveExtendDTO::getCommodityMinNum))
                .reduce((a, b) -> {
                    if (a.getCommodityMaxNum() >= b.getCommodityMinNum()) {
                        throw new RuntimeException("区间有重叠");
                    }
                    return b;
                });
    }


    /**
     * 自定义排序--指定字段排序
     *
     * @param sourceList
     */
    private static void customSortList(List<Person> sourceList) {
        // JDK1.8之后使用Lambda表达式的基本排序
        List regulationOrder = Arrays.asList("通知公告", "信息公示", "机关党建", "部门工作", "个性办公", "涉台舆情", "会议纪要", "学习园地", "机关文化", "下载专区", "通讯录", "安全邮件");
        sourceList.sort(Comparator.comparing(e -> regulationOrder.indexOf(e.getName())));

    }


    private static void customSortList2(List<PersonInsightSqlResDTO> sourceList, String dim) {
        // 指定字段排序
        List<String> customSortList = DimSortUtil.getDimMap().get(dim);
        if (CollectionUtils.isNotEmpty(sourceList) && CollectionUtils.isNotEmpty(customSortList)) {
            sourceList.sort(Comparator.comparing(e -> customSortList.indexOf(e.getDimKey())));
        }
    }

}
