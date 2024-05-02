package io.geekidea.springboot.assembly.demo.Test;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class Test_79_RANDOM {


    public static void main(String[] args) throws Exception {
        Object obj=null;
//        System.out.println(obj.toString());
        Long aa=1222222L;
        Long ll=1222222L;
        System.out.println(aa==ll);
        System.out.println(aa.equals(ll));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

}



