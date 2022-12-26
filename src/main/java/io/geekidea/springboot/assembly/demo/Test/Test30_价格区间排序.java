package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.PersonInsightSqlResDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://blog.51cto.com/u_15127658/4334611
 */
@Slf4j
public class Test30_价格区间排序 {


    public static void main(String[] args) {
//        String[] regulation = {"1-100","301-400","200-300","101-200"};
//        final List<String> list = Arrays.asList(regulation);

        List<PersonInsightSqlResDTO> postList = new ArrayList<>();
        postList.add(new PersonInsightSqlResDTO("post", "101-200", 444d, 22d));
        postList.add(new PersonInsightSqlResDTO("post", "301-400", 111d, 22d));
        postList.add(new PersonInsightSqlResDTO("post", "1-100", 333d, 22d));
        postList.add(new PersonInsightSqlResDTO("post", "201-300", 555d, 22d));
        List<String> list = postList.stream().map(PersonInsightSqlResDTO::getDimKey).collect(Collectors.toList());

        extracted(list,postList);
    }

    private static void extracted(List<String> list,List<PersonInsightSqlResDTO> sourceList) {

        Map<Integer, String> map = new LinkedHashMap<>();
        for (String s : list) {
            String[] split = s.split("-");
            map.put(Integer.valueOf(split[0]),s);
        }
        System.out.println(JSON.toJSONString(map));

        List<String> sortList = new LinkedList<>();
        //map key排序
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                forEachOrdered(x -> {
                    sortList.add(x.getValue());
                });
        System.out.println(sortList);

        sourceList.sort(Comparator.comparing(e -> sortList.indexOf(e.getDimKey())));
        System.out.println(JSON.toJSONString(sourceList));

    }

}
