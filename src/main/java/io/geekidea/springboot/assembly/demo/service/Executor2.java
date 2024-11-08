package io.geekidea.springboot.assembly.demo.service;

import io.geekidea.springboot.assembly.demo.entity.AbstractExecutor;
import org.springframework.stereotype.Service;

@Service
public class Executor2 extends AbstractExecutor {
    @Override
    public String getExecutor() {
        return "Executor2";
    }
 
    @Override
    public String printContext() {
        return "Executor2----执行";
    }
}