package io.geekidea.springboot.assembly.demo.service;

import io.geekidea.springboot.assembly.demo.entity.Order;
import io.geekidea.springboot.assembly.demo.entity.PayResult;
import org.springframework.stereotype.Service;

/**
 * 微信支付
 *
 * @author Mr.Qu
 * @since 2021/5/18 10:58
 */
@Service
public class WechatPay implements IPayment, IReceivement {

    @Override
    public PayResult pay(Order order) {
        return new PayResult(200, "微信支付成功");
    }


    @Override
    public PayResult receive(Order order) {
        return new PayResult(200, "微信 【【收款】】 成功");
    }
}
