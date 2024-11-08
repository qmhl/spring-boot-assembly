package io.geekidea.springboot.assembly.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;
 
/**
 * 订单信息
 *
 * @author Mr.Qu
 * @since 2021/5/18 10:56
 */
@Data
@Accessors(chain = true)
public class Order {
    /**
     * 金额
     */
    private int amount;
 
    /**
     * 支付类型
     */
    private String type;
}
