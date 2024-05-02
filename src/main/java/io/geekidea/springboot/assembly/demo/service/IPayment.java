package io.geekidea.springboot.assembly.demo.service;


import io.geekidea.springboot.assembly.demo.entity.Order;
import io.geekidea.springboot.assembly.demo.entity.PayResult;

/**
 * 支付接口
 *
 * @author Mr.Qu
 * @since 2021/5/18 10:52
 */
public interface IPayment {

    /**
     * 支付
     *
     * @param order 订单信息
     * @return PayResult 支付结果
     */
    PayResult pay(Order order);
}

