package io.geekidea.springboot.assembly.demo.service;

import io.geekidea.springboot.assembly.demo.entity.AbstractExecutor;
import org.springframework.stereotype.Service;

@Service
public class Executor1 extends AbstractExecutor {
    @Override
    public String getExecutor() {
        return "Executor1";
    }
 
    @Override
    public String printContext() {
        return "Executor1----执行";
    }
}