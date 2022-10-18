package io.geekidea.springboot.assembly.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 体验管理-体验诊断参数类
 * @Author: wangnan122
 * @Create: 2021-08-20 14:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeelInsightParam extends PermissionCheckParam {

    private Integer pageNum;

    private Integer pageSize;
    /**
     * 品牌id-多选 用于品牌/品类页签 多个brand以英文逗号分隔
     */
    private String brandId;
    /**
     * skuid-多选 用于sku页签 多个sku以英文逗号分隔
     */
    private String sku;
    /**
     * spuid-多选 用于sku页签 多个spu以英文逗号分隔
     */
    private String spu;
    /**
     * 店铺id-多选 用于shop页签 多个shop以英文逗号分隔
     */
    private String shopId;
    /**
     * 品类-多选 用于品类/品牌/店铺页签，形式如：[[id1,id2,id3],[id1,id2]]
     */
    private List<List<Integer>> cateList = new ArrayList<>();
    /**
     * 部门-多选 用于品类页签，形式如：[[id1,id2,id3],[id1,id2]]
     */
    private List<List<Integer>> deptList = new ArrayList<>();
    /**
     * 业务类型（自营、pop） 用于品类页签
     */
    private List<String> businessType;
    /**
     * 文本来源 用于所有页签（["all"]表示全部来源）
     */
    private List<String> source;
    /**
     * 用于趋势成因分析前端端传来的时间数组
     */
    private List<String> trendDts;
    /**
     * 适用于用户原声，作为筛选条件
     */
    private String firstAsp;
    /**
     * 适用于nps场景,前端传来参数，获取下钻之后的右侧五级问题
     */
    private String secondAsp;
    /**
     * 适用于nps场景,前端传来参数，获取下钻之后的右侧五级问题
     */
    private String thirdAsp;
    /**
     * 适用于nps场景
     */
    private String fourthAsp;
    /**
     * 适用于nps场景,用五级问题取 top sku/brand/Cate
     */
    private String fifthAsp;
    /**
     * 适用于用户原声场景,用五级问题取用户原声
     */
    private List<String> fifthAsps;
    /**
     * 用于表示不良体验诊断下方关联类型
     * @see
     */
    private Integer type = 0;
    /**
     * 用于结果排序（value/rate/ber/ucr）
     */
    private String order = "value";
    /**
     * 用于结果排序（desc/asc）
     */
    private String orderType = "desc";
    /**
     * 前端用于区分对比分析得页签（0-brand;1-sku;2-shop）
     */
    private Integer comType = 0;
    /**
     * 对比分析页签下具体的值
     */
    private String comValue;
    /**
     * 模糊搜索五级问题
     */
    private String query;
    /**
     * 品类-0,品牌-1,店铺-2,sku-3（用于权限控制中，判断应该取cookie中的什么字段）
     */
    private Integer pageType = 0;
    /**
     * 趋势成因分析->用户原声的时间条件
     */
    private String trendTime;
    /**
     * 仅用于自助分析看板查看接口
     */
    private Integer projectId = 0;
    /**
     * 仅在趋势成因分析中用于区分不同指标
     * （ber:0-ber，1-总观点量，2-正向观点量，3-负向观点量；ucr:0-ucr,1-关联订单负向观点量,2-非关联订单负向负向观点量)
     */
    private Integer trendType = 0;
    /**
     * 用于体验诊断价格带筛选
     */
    private Double priceLow;
    /**
     * 用于体验诊断价格带筛选
     */
    private Double priceHigh;
    /**
     * 用于控制体验诊断分析是否展示ber
     */
    private Boolean showBer = true;
    /**
     * 供应商简码
     */
    private List<String> suppCodes;
    /**
     * 时间筛选框类型（0-自定义日期；1-按周筛选；2-按月筛选）
     */
    private Integer dateType;
    /**
     * 时间筛选框具体数值（0-存放的是开始、结束时间；1-某一周的开始时间，多选；2-某个月的开始时间，多选）
     */
    private List<String> dateValues;
    /**
     * 是否展示周月环比，0表示周环比，1表示月环比
     */
    private Integer weekMonthRatio;
    /**
     * 用于控制体验诊断分析是否展示ucr
     */
    private Boolean showUcr = false;
    /**
     * 用于控制体验诊断-不良体验分析扇形图分类类型（0-渠道，1-业务类型）
     */
    private Integer npsType = 0;
    /**
     * 用于体验诊断-商品名称模糊搜索的关键词
     */
    private List<String> skuKeywords;
    /**
     * 用于体验诊断-关联部门/品类下钻的name
     */
    private String relatedName;

    /**
     * 用于体验诊断-关联部门/品类下钻的level
     * @see
     * @see
     */
    private Integer relatedLevel;
    /**
     * skuname，用于商品名称筛选框
     */
    private List<String> skuNames;
    /**
     * 用于体验诊断-关联部门/品类下钻的level
     * @see
     */
    private Integer emotion;  //情感

    private Boolean isComment ;//用于过滤来源，明细展示时的文本来源规则与其他场景不同

    //--------用于sql查询-----------

    private List<List<String>> dtTimes = new ArrayList<>();//用于在ck里查询的时间筛选
    private String frontMidHid; //售前售中售后
    private List<String> skus;  //适用于topsku
    private List<String> brands;  //适用于topbrand，名称
    private List<String> cates;  //适用于topcate，名称
    private List<String> spus;  //适用于topspu
    private List<String> depts;  //适用于topdept
    private List<String> shops;  //适用于topshop
    private Map<String,String> spuNames;  //适用于topspu的spuName
    private Boolean isIndustryAvg = false;  //用于对比分析，判断是否需要返回行业平均的相关值
    private Integer deno;   //ber分母
    private List<Integer> skuCid3s;//sku页签计算行业平均值时sku对应的cid3s
    private List<String> omniChannelTypes;//全渠道  全渠道-到家融合、全渠道-商超自配送、全渠道-天选项目
    private Integer isVp;//是否关联订单 0 否 1是
    private Integer businessRangeId = 6;// ucr业务范围ID（0 企业 1 京喜拼拼 2 掌柜宝 3 京喜 4 自有品牌 5 拍拍二手 6 主站） 默认主站
    private Integer businessRangeIdBer = 1;// ber业务范围ID(数组形式) 默认主站
    private Boolean isJdPrice = true;//是否选择京东价
    private Boolean isFeelVoc = false;//是否是体验管理的用户原声查询

    //--------页面上多选，后端解析后注入-----------
    private List<String> skuList = new ArrayList<>();
    private List<String> spuList = new ArrayList<>();
    private List<String> brandList = new ArrayList<>();
    private List<String> shopList = new ArrayList<>();

    //--------明细下载新增字段-----------
    private List<String> firstAsps;   //一级体验问题集合

    private List<String> secondAsps;   //二级体验问题集合

    private List<String> thirdAsps;    //三级体验问题集合

    private List<String> fourthAsps;    //四级级体验问题集合

    private Integer isRedLine;    //是否红线 1是，0否





}

