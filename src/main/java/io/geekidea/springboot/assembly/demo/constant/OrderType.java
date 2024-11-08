package io.geekidea.springboot.assembly.demo.constant;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 *     if-else的条件
 * </p>
 *
 * @author: heluwei
 * @Date: 2020/4/22 17:53
 */
@Data
@Builder
public class OrderType {
    //免费订单
    public static final int FREE = 1;
    //半价订单
    public static final int HALF = 2;
    //打折订单
    public static final int DISCOUT = 3; 
}