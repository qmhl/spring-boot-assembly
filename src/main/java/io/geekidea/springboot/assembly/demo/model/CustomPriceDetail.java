package io.geekidea.springboot.assembly.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 自定义价格带明细表：保存自定义价格带相关配置
 */
@Data
public class CustomPriceDetail {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 类型：趋势洞察、用户决策归因、需求雷达
     */
    private String type;

    /**
     * 一级品类id
     */
    private Integer cid1;

    /**
     * 二级品类id
     */
    private Integer cid2;

    /**
     * 三级品类id
     */
    private Integer cid3;

    /**
     * 一级品类name
     */
    private String cid1Name;

    /**
     * 二级品类name
     */
    private String cid2Name;

    /**
     * 三级品类name
     */
    private String cid3Name;

    /**
     * 日期区间：1：近一周内、2：近一个月、3：近三个月、4：近半年
     */
    private Integer dateRegion;

    /**
     * 价格区间：用户自定义的所有价格区间，json格式: {"价格带1":"1-2","价格带2":"2-3"}，最多有六个价格带
     */
    private String priceRegion;

    /**
     * 删除状态,0：未删除，1：已删除
     */
    private Boolean isDeleted;

    /**
     * 人群包地址
     */
    private String userGroupPinUrl;

    /**
     * 人群包id
     */
    private Integer userGroupPinId;

    /**
     * 人群包来源：标签圈选、智能圈选和自定义圈选
     */
    private String userGroupPinSource;
    /**
     * 人群包名称
     */
    private String userGroupPinName;

    /**
     * 创建时间    前端返回的是字符串
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 价格区间list:["1-2", "2-3"]
     */
    private List<String> priceRegionList;
}