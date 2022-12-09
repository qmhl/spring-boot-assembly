package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.BusinessTypeLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class Test43_1 {
    //Arrays.asList(Arrays.asList("自营"), Arrays.asList("自营","厂直"),Arrays.asList("pop","popsop")
//                ,Arrays.asList("全渠道","商超"));
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("自营", "厂直","popsop");
//        boolean 自营 = list.remove("自营");
//        System.out.println(list);

        List<String> list = new ArrayList<>();
        list.add("赵云");
        list.add("黄忠");
        list.add("马超");
        list.add("关羽");
        list.add("张飞");
        // 筛选出不是“关羽” 的集合
        list = list.stream().filter(e -> !"关羽".equals(e)).collect(Collectors.toList());
        System.out.println("method4|list=" + list);
    }
}
