package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.geekidea.springboot.assembly.demo.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test_77_For {


    public static void main(String[] args) throws Exception {

        String aa = "123wscv";
        String bb = "123wscvWE";
        System.out.println(StringUtils.substring(bb, aa.length()));
        System.out.println(StringUtils.substringAfterLast(bb, aa));

        int a = 1111;
        int b = 1111;
        System.out.println(a == b);
        List<Map> list = Lists.newArrayList();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangqi");
        map.put("code", "111");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "list");
        map1.put("code", "222");
        list.add(map);
        list.add(map1);

        List<String> name = list.stream().map(e -> e.get("name").toString()).collect(Collectors.toList());
        List<String> code = list.stream().map(e -> e.get("code").toString()).collect(Collectors.toList());
        List<Collection> collect = list.stream().map(Map::values).collect(Collectors.toList());
        List<Collection> collect1 = list.stream().map(Map::entrySet).collect(Collectors.toList());
        System.out.println(name);
        System.out.println(code);
        System.out.println(collect);
        System.out.println(collect1);

//        int addr = (int) map.get("code");
//        System.out.println(addr);

        String name1 = "name11";
        String code1 = "code11";
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", name1);
        map2.put("code", code1);

        System.out.println(JSON.toJSONString(map2));

        System.out.println("===============");
        test();

        List<Integer> outList = new ArrayList<>();
        ArrayList<Integer> intList = Lists.newArrayList(1, 2, 4, 5);
        for (Integer i : intList) {
//            test();
            if (i == 2) {
                break;
            }
            outList.add(i);
        }
        System.out.println("outList: " + outList);

        List<Integer> outList2 = new ArrayList<>();
        ArrayList<Integer> intList2 = Lists.newArrayList(99, 92, 94, 85);
        for (Integer i : intList2) {
            if (i == 99) {
                continue;
            }
            outList2.add(i);
        }
        System.out.println("outList2: " + outList2);
    }

    public static void test() {
        List<Integer> outList = new ArrayList<>();
        ArrayList<Integer> intList = Lists.newArrayList(1, 2, 4, 5);
        for (Integer i : intList) {
            if (i == 2) {
                System.out.println("i: " + i);
                return;
            }
            outList.add(i);
        }
        System.out.println("test: " + outList);
    }


    // return； : 只对它所在的这个方法起作用，比如test()内含有for循环，内有return不会执行test()内代码，但是不影响外面代码的继续运行（test()外面还套了一层其他方法）；
    // break :  只对当前的for循环起作用，不对后面的for循环起作用；

}



