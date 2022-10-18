package io.geekidea.springboot.assembly.demo.service;

import com.example.demo.utils.HandlerOrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    HandlerOrderContext handlerOrderContext;

    @Override
    public void handleOrder(Integer type) {
        //使用策略处理订单
        OrderStrategy orderStrategy = handlerOrderContext.getOrderStrategy(type);
        orderStrategy.handleOrder(type);
    }
}