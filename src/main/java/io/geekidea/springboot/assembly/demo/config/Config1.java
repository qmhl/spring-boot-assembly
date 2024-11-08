package io.geekidea.springboot.assembly.demo.config;


import io.geekidea.springboot.assembly.demo.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config1 {


    @Bean
    public Person person(){
        return new Person("zhangsan",11);
    }

    @Bean
    public Person person1(){
        return new Person("lisi",22);
    }
}
