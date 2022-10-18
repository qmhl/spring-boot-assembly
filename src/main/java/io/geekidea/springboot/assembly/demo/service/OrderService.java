package io.geekidea.springboot.assembly.demo.service;

/**
 * <p>
 *     把写的策略模式在Service业务类中运用。
 * </p>
 *
 * @author: heluwei
 * @Date: 2020/4/22 18:01
 */
public interface OrderService {
    void handleOrder(Integer orderType);
}