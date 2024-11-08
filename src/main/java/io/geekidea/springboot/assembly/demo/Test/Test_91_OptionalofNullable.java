package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Test_91_OptionalofNullable {
    public static void main(String[] args) throws ParseException {

        Optional<Optional<String>> opt = Optional.of(Optional.of("hello"));
//        opt.ifPresent(innerOpt -> innerOpt.ifPresent(s -> System.out.println(s)));
        String str=null;
        String str1 = Optional.ofNullable(str).orElse("aaa");
        System.out.println(str1);

        Optional.ofNullable(str).ifPresent(a-> System.out.println("a:"+a));
        Person person = null;
        Optional<Person> os2 = Optional.ofNullable(person);
        System.out.println(os2);

        System.out.println("================");
        // 创建一个可能为 null 的值的 Optional 对象
        Optional<String> optionalValue = Optional.ofNullable(null);

        // 使用 ifPresent 遍历 Optional 中的值
        optionalValue.ifPresent(value -> System.out.println("Value is present: " + value));


    }


}



