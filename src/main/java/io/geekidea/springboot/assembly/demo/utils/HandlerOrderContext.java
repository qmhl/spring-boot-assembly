package io.geekidea.springboot.assembly.demo.utils;

import io.geekidea.springboot.assembly.demo.service.OrderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HandlerOrderContext {
    @Autowired
    private ApplicationContext applicationContext;


    //存放所有策略类Bean的map
    public static Map<Integer, Class<OrderStrategy>> orderStrategyBeanMap = new HashMap<>();


    public OrderStrategy getOrderStrategy(Integer type) {
        Class<OrderStrategy> strategyClass = orderStrategyBeanMap.get(type);
        if (strategyClass == null) {
            throw new IllegalArgumentException("没有对应的订单类型");
        }
        //从容器中获取对应的策略Bean
        return applicationContext.getBean(strategyClass);
    }
}