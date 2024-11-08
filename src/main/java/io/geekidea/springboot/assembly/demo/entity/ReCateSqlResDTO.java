package io.geekidea.springboot.assembly.demo.entity;


import lombok.Data;

/**
 * 商机挖掘洞察的实体类
 */
@Data
public class ReCateSqlResDTO {

    /**
     * 品类
     */
    private String cidName;

    /**
     * 品类Id
     */
    private Integer cid;
    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 年月周期
     */
    private String dt;
    /**
     * 城市线级
     */
    private String cityLevel;
    /**
     * 价格区间
     */
    private String priceLevel;

    /**
     * 市场规模
     */
    private Long gmv;
    /**
     * 同比的市场规模
     */
    private Long tGmv;

    /**
     * 市场规模占比
     */
    private Double percent;
    /**
     * 销量
     */
    private Long orderNums;

    /**
     * 客单价
     */
    private Long customPrice;

    /**
     * 同比增速
     */
    private Double yoy;
    /**
     * 同比增速(取对数之后的虚拟值)
     */
    private Double yoyIndex;


}
