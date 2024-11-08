package io.geekidea.springboot.assembly.demo.service;

import io.geekidea.springboot.assembly.demo.annotaion.HandlerOrderType;
import io.geekidea.springboot.assembly.demo.constant.OrderType;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     实现策略接口
 * </p>
 *
 * @author: heluwei
 * @Date: 2020/4/22 17:55
 */
@Component
@HandlerOrderType(OrderType.FREE) //使用注解标明策略类型
public class FreeOrderStrategy implements OrderStrategy {
    @Override
    public void handleOrder(Integer order) {
        System.out.println("----处理免费订单----");
    }
}