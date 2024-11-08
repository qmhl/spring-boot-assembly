package io.geekidea.springboot.assembly.demo.entity;

import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
/**
 * 支付结果
 *
 * @author Mr.Qu
 * @since 2021/5/18 10:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayResult {
    /**
     * 响应码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
 
    public static PayResult success(String msg) {
        return new PayResult(200, msg);
    }
}
