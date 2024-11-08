package io.geekidea.springboot.assembly.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JsonTest {


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;
    /**
     * xiangqing
     */
    private String detail;
    /**
     * shuju
     */
    private String data;
}
