package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.model.Person;
import io.geekidea.springboot.assembly.demo.utils.DatePatternEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class Test_90 {
    public static void main(String[] args) throws ParseException {
        Person person = new Person();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            person = new Person();
            person.setName("name"+i);
            person.setAge(i);
            list.add(person);
        }
        System.out.println("list: "+list);



        List<Person> list1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Person person1 = new Person();
            person1.setName("name"+i);
            person1.setAge(i);
            list1.add(person1);
        }
        System.out.println("list1: "+list1);
    }


}



