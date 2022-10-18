package io.geekidea.springboot.assembly.demo.service;

import com.example.demo.annotaion.HandlerOrderType;
import com.example.demo.constant.OrderType;
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
@HandlerOrderType(OrderType.HALF)
public class HalfOrderStrategy implements OrderStrategy {
    @Override
    public void handleOrder(Integer order) {
        System.out.println("----处理半价订单----");
    }
}