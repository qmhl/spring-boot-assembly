package io.geekidea.springboot.assembly.demo.aspect;

import java.lang.annotation.*;

/**
 * @author zhangqi
 * @Description: ***
 * @date 2022/2/24 19:53
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PermissionAuth {


    // 模块code:查询权限时需要指定查询的模块（体验管理-1、商机挖掘-2、用户超市-3）
    String moduleCode() default "";

    // 权限粒度：品类-0，品类品牌-1，品类店铺-2，店铺-3，纯品类-4
    int permissionGran() default 0;

    // 品类权限类型：纯品类-1/品类下全部-2
    int catePermissionType() default 0;

}
