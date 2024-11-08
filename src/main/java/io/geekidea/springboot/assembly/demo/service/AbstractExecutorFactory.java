package io.geekidea.springboot.assembly.demo.service;

import io.geekidea.springboot.assembly.demo.entity.AbstractExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AbstractExecutorFactory {

    private final List<AbstractExecutor> abstractExecutorList;

    private static Map<String, AbstractExecutor> abstractExecutorMap;

    @Autowired
    public AbstractExecutorFactory(List<AbstractExecutor> abstractExecutorList) {
        this.abstractExecutorList = abstractExecutorList;
    }

    @PostConstruct
    public void init() {
        abstractExecutorMap = abstractExecutorList.stream().collect(Collectors.toMap(AbstractExecutor::getExecutor, e -> e));
    }

    public AbstractExecutor getExecutor(String name) {
        return abstractExecutorMap.get(name);
    }
}

