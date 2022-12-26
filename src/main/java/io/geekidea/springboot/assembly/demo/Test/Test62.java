package io.geekidea.springboot.assembly.demo.Test;

import com.google.common.base.Splitter;
import io.geekidea.springboot.assembly.demo.model.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test62 {

    public static void main(String[] args) throws ParseException {
        List list = new ArrayList();
        Entry entry = new Entry();
        entry.setAddr("上海");
        entry.setName("zhangsan");
        list.add(entry);

        entry.setName("lisi");
        entry.setAddr("深圳");
        list.add(entry);
        System.out.println(list.size());
        System.out.println(list);

        List<String> strings = Arrays.asList("1-100", "100-200");
        List<List<String>> lists = handlePrice(strings);
        System.out.println(lists);

        List<String> strings1 = Arrays.asList("一线", "四线", "三线", "五线", "二线");
        List<String> collect = strings1.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(strings1);
        System.out.println(collect);
    }

    private static List<List<String>> handlePrice(List<String> priceList)  {
        return priceList.stream().map(o -> {
            return Splitter.on("-").omitEmptyStrings().trimResults().splitToList(o);
        }).collect(Collectors.toList());
    }



}



