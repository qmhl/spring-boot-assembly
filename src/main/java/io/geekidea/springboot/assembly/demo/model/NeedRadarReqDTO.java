package io.geekidea.springboot.assembly.demo.model;

import lombok.*;

import java.util.List;

/**
 * @author zhangqi
 * @Description: ***
 * @date 2021/8/30 11:18
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NeedRadarReqDTO {

    // 筛选条件
    private Integer cid1; // 一级品类id
    private Integer cid2; // 二级品类id
    private Integer cid3; // 三级品类id
    private Integer time; // 日期：1:近一周内 2:近一月内 3:近三个月内 4:近半年
    private String priceType; //价格： 1：高价格带 2：中高价格带、3：中低价格带 4：低价格带
    private List<String> needType; // 需求类别 ：售前三级类目的扩展属性类别、售后voc虚拟三级属性  支持多选
    private String attr; // 属性：售前三级类目的扩展属性、售后voc四级属性
    private List<String> attrs; // 属性：售前三级类目的扩展属性、售后voc四级属性  支持多选 [需求雷达九宫格专用]
    private String keyword; // 属性值：售前三级类目的扩展属性值、售后voc五级属性
    // 用户群：首购 复购 plus用户不能同时出现
    private Integer userGroup; // 用户人群 1=首购用户 2=复购用户 3=plus用户  其他:账号在用户超市计算好的人群的id


    // 需求部分：排序字段   1:asc 0:desc
    private String orderByVocCount; // 默认按声量排序

    private String orderByVocCountRate; // 声量增长率

    private String orderByAttentionIndex; // 关注度

    private String orderBySatisIndex;// 满足度

    private String orderByQueryClickRate; // 搜索点击率

    private String orderByDealTransRate; // 成交转化率

    private String orderByDealAmountRate; // 成交金额增长率

    private String orderByDealAmountIndex;// 成交金额指数

    private String cardType;  // 卡片类型：售前： pre  售后：post

    private Integer downLoadType; // 下载卡片类型：1=心智抢占\2=基础必备\3=未来发展\4=控制成本\5=选择发展\6=需求明细

    private String type;  // 类型：卡片=card 需求明细=detail

    private Integer emotion;//情感

    private String emotionType;//情感 1 或者-1

    private String appCode;   //"zh-pc":(纵横商家版) "zh-bdp-pc":纵横运营版

    private Boolean isTransferEmotion; //是否传入情感

}
