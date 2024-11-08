package io.geekidea.springboot.assembly.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商机挖掘洞察的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReTrendInsightSqlResDTO {


    /**
     * 品类Id
     */
    private Integer cid;

    /**
     * 品类
     */
    private String cidName;

    /**
     * 二级关键词
     */
    private String itemSecondKeyword;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 来源名称
     */
    private String sourceName;

    /**
     * gmv
     */
    private Double gmv;
    /**
     * 占比
     */
    private Double percent;
    /**
     * 同比
     */
    private Double yoy;

    /**
     * gmv的平均值
     */
    private Double avgGmv;

    /**
     * 同比的平均值
     */
    private Double avgYoy;


}
