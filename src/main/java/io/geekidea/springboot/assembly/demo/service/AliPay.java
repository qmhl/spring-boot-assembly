package io.geekidea.springboot.assembly.demo.service;

import io.geekidea.springboot.assembly.demo.entity.Order;
import io.geekidea.springboot.assembly.demo.entity.PayResult;
import org.springframework.stereotype.Service;

/**
 * 支付宝支付
 *
 * https://blog.csdn.net/qq_37949192/article/details/116993087
 *
 * @author Mr.Qu
 * @since 2021/5/18 11:08
 */
@Service
public class AliPay implements IPayment, IReceivement {
    @Override
    public PayResult pay(Order order) {
        return new PayResult(200, "支付宝支付成功");
    }

    @Override
    public PayResult receive(Order order) {
        return new PayResult(200, "支付宝 【【收款】】 成功");
    }
}

