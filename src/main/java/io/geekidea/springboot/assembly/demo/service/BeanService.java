package io.geekidea.springboot.assembly.demo.service;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 参考链接：https://www.jianshu.com/p/fa4f92701807
 */
@Service
@Slf4j
@Scope(value = "singleton")
public class BeanService {
    //===========方法一：构造注入 但是构造函数中的参数是 @Bean中已经有的id==============
//    private Person person;
//
//    public BeanService(Person person) {
//        this.person = person;
//    }
    //=========================

    //==========方法二：使用@Qulifier  实际上使用的Qualifier中的值==============
//    private Person person2;
//
//    public BeanService(@Qualifier("person1") Person person2) {
//        this.person2 = person2;
//    }
    //=========================

    //==========方法三：使用@Qulifier  实际上使用的Qualifier中的值==============

//    @Autowired
//    @Qualifier("person")
//    private Person person2;
//
//    public BeanService(Person person1) {
//        this.person2 = person1;
//    }
    //==================================

    //===========方法一：构造注入 但是构造函数中的参数是 @Bean中已经有的id==============
    @Autowired
    private Person person;
    @Autowired
    private Person person1;
    //=========================






//    private Person person;
//
//    public BeanService(Person person) {
//        this.person = person;
//    }

    public String getTest1() {
        log.info("out put person>>>{}", JSON.toJSON(person));
        log.info("out put person>>>{}", JSON.toJSON(person1));
        return JSON.toJSONString(person);
    }

//    public String getTest() {
//        log.info("out put person>>>{}",JSON.toJSON(person1));
//        return JSON.toJSONString(person1);
//    }
}
