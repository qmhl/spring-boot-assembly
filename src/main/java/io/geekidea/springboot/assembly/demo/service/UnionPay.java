package io.geekidea.springboot.assembly.demo.service;

import io.geekidea.springboot.assembly.demo.entity.Order;
import io.geekidea.springboot.assembly.demo.entity.PayResult;
import org.springframework.stereotype.Service;

/**
 * 银联支付
 *
 * @author Mr.Qu
 * @since 2021/5/18 11:10
 */
@Service
public class UnionPay implements IPayment, IReceivement {
    @Override
    public PayResult pay(Order order) {
        System.out.println("开始银联支付");
        return PayResult.success("银联支付成功");
    }

    @Override
    public PayResult receive(Order order) {
        return new PayResult(200, "银联 【【收款】】 成功");
    }
}

