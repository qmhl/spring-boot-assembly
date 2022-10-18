package io.geekidea.springboot.assembly.demo.model;

import lombok.Data;

/**
 * @author jinmengyong3
 * @description: 部门/品类/店铺通用返回值
 * @date 2021/9/1 16:18
 */
@Data
public class CommonResult {
    private Integer id;
    private String name;
}
