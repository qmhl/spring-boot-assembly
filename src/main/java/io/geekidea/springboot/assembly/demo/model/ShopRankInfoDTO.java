package io.geekidea.springboot.assembly.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangqi1092
 * @Description: 某个店铺的明细信息
 * @Date: 2022/09/11 17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRankInfoDTO {

    /**
     * N级类目Id
     */
    private Integer itemCateCd;
    /**
     * N级类目名称
     */
    private String itemCateName;
    /**
     * 品类级别
     */
    private Integer cateLevel;
    /**
     * 店铺id
     */
    private Integer shopId;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 排名
     */
    private Integer rank;
    /**
     * 排名环比
     */
    private String rankRatio;
    /**
     * 指数环比
     */
    private String indexRatio;
    /**
     * 考核质量分
     */
    private String qualityScore;

    /**
     * 行业平均值
     */
    private String industryAvg;

    /**
     * 是否铆钉
     */
    private Boolean isFixed = false;

}

