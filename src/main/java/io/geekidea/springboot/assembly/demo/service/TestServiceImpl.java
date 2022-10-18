package io.geekidea.springboot.assembly.demo.service;


import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test1() {
        return "welcome";
    }
}
