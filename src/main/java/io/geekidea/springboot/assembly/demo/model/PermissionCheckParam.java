package io.geekidea.springboot.assembly.demo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
* 权限校验入参
*/
@Data
public class PermissionCheckParam {

    private String moduleCode;     //模块code:查询权限时需要指定查询的模块（体验管理-1、商机挖掘-2、用户超市-3）

    private String brandId;   //品牌id-多选 用于品牌/品类页签 多个brand以英文逗号分隔

    private String shopId;    //店铺id-多选 用于shop页签 多个shop以英文逗号分隔

    private List<List<Integer>> cateList = new ArrayList<>();  //品类-多选 用于品类/品牌/店铺页签，形式如：[[id1,id2,id3],[id1,id2]]

    private Integer permissionGran=0;     //权限粒度：品类-0，品类品牌-1，品类店铺-2，店铺-3，纯品类-4

    private Integer catePermissionType=2;  //品类权限类型：纯品类-1/品类下全部-2,不必传

    private String userType;    //用户类型,不必传
}