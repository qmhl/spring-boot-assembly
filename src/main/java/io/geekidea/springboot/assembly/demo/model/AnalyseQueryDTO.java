package io.geekidea.springboot.assembly.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @author zhangqi
 * @Description: ***
 * @date 2021/5/31 23:04
 */
@Data
public class AnalyseQueryDTO {
    private List<Integer> buIdList;// 部门列表
    private List<Integer> deptId1List;// 一级部门列表
    private List<Integer> deptId2List;// 二级部门列表
    private List<Integer> deptId3List;// 三级部门列表

    private List<Integer> cate1List;// 一级品类列表
    private List<Integer> cate2List;// 二级品类列表
    private List<Integer> cate3List;// 三级品类列表

    private List<String> sourceList; //渠道
    private String startTime; //日期--开始
    private String endTime; //日期--结束
    private List<String> brandIdList; //品牌

    // 兼容体验分析的新增字段
    private String businessModel; //业务类型  ：自营/pop
    // 业务范围：0 主站 1 企业 2 京喜拼拼 3 京喜通[掌柜宝] 4 京喜 5 自有品牌 6 拍拍二手
    private String businessRangeName;
    // 第几页
    private int pageNum;
    // 每页多少条
    private int pageSize;
    private List<String> isRedLineList;//多红线
    private List<String> omniChannelList;//全渠道
    private List<String> pltfmNameList;//下单渠道
    private List<String> buList;//BU
    private List<String> isNewUserList;//新老用户
    private List<String> isEnterpriseList;//是否企业多选

    //   ***** sql参数*****
    // 第几页
    private Integer offSet;
    // 每页多少条
    private Integer limitSize;
}
