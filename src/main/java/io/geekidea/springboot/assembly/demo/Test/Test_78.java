package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test_78 {


    public static void main(String[] args) throws Exception {
        List<Integer> innerList1 = new ArrayList<>();
        List<Integer> outList1 = new ArrayList<>();
        ArrayList<Integer> outerList = Lists.newArrayList(1, 2, 4, 5);
        ArrayList<Integer> innerList = Lists.newArrayList(11, 21, 41, 51);
        for (Integer i : outerList) {
            for (Integer j : innerList) {
                if (j == 21) {
                    System.out.println("j: " + j);
                    return;
                }
                innerList1.add(j);
            }
            outList1.add(i);

        }
        System.out.println("innerList1: " + innerList1);
        System.out.println("outList1: " + outList1);
    }


    public static void doubleFor() {
        List<Integer> outList = new ArrayList<>();
        ArrayList<Integer> innerList = Lists.newArrayList(11, 21, 41, 51);

        for (Integer j : innerList) {
            if (j == 21) {
                System.out.println("j: " + j);
                return;
            }
            outList.add(j);
        }
        System.out.println("outList: " + outList);
    }

    // return； : 只对它所在的这个方法起作用，比如test()内含有for循环，内有return不会执行test()内代码，但是不影响外面代码的继续运行（test()外面还套了一层其他方法）；
    // return；   双重for循环，里面有return话直接返回，外层循环也不会执行；
    // return； : 外面for循环中嵌套一个方法test()，这个test()方法中有个for循环，这时候return只会对里面的for循环起作用，不会影响外面for循环的执行；
    // break :  只对当前的for循环起作用，不对后面的for循环起作用；
    // Integer :  最好使用equals比较  -128 - 127之间的可以使用==，最好用equals；

}



