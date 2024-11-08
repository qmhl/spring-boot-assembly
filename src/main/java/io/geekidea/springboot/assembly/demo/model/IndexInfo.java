package io.geekidea.springboot.assembly.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 指标明细表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexInfo {
    /**
     * id
     */
    private Long id;

    /**
     * 分类编号
     */
    private Long classificationId;

    /**
     * 指标英文名
     */
    private String indexEnName;

    /**
     * 指标中文名
     */
    private String indexCnName;

    /**
     * 指标描述（指标说明，包含指标描述 和 加工口径）
     */
    private String indexDescription;

    /**
     * 指标归属方（客服、平生、Y、商超、启明星）
     */
    private String indexOwner;

    /**
     * 指标事业群（3C、时尚、商超）
     */
    private String indexBusinessGroup;

    /**
     * 指标最细存储粒度（SKU、店铺、品类、品牌，与ck表中数据存储粒度保持一致）
     */
    private String indexMinimumStorageGranularity;

    /**
     * 是否初始指标（0是、1否）
     */
    private Boolean isInitialIndex;

    /**
     * 是否显示（0是、1否）
     */
    private Boolean isDisplay;

    /**
     * 恶化方向（0无、1越小、2越大）
     */
    private Boolean deteriorationDirection;

    /**
     * 计算方式（sum(x)  或 sum(x) / sum(y)）
     */
    private String calMethod;

    /**
     * 字段名x
     */
    private String fieldNameX;

    /**
     * 字段名y
     */
    private String fieldNameY;

    /**
     * 字段名z
     */
    private String fieldNameZ;

    /**
     * 指标小数位数
     */
    private Integer indexDecimalPlaces;

    /**
     * 指标别名
     */
    private String indexAlias;

    /**
     * 是否关联商品（0不支持，1支持）
     */
    private Boolean isRelatedGood;

    /**
     * 是否关联店铺（0不支持，1支持）
     */
    private Boolean isRelatedShop;

    /**
     * 是否关联商家（0不支持，1支持）
     */
    private Boolean isRelatedVender;

    /**
     * 是否关联品牌（0不支持，1支持）
     */
    private Boolean isRelatedBrand;

    /**
     * 是否关联品类（0不支持，1支持）
     */
    private Boolean isRelatedCategory;

    /**
     * 是否关联部门（0不支持，1支持）
     */
    private Boolean isRelatedDept;

    /**
     * 是否关联区域（0不支持，1支持）
     */
    private Boolean isRelatedArea;

    /**
     * 是否关联门店（0不支持，1支持）
     */
    private Boolean isRelatedStore;

    /**
     * 是否关联供应商（0不支持，1支持）
     */
    private Boolean isRelatedSupplier;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 删除标识，0未删除，1已删除
     */
    private Boolean isDeleted;
}