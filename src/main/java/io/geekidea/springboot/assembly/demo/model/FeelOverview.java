package io.geekidea.springboot.assembly.demo.model;

import java.util.Date;

public class FeelOverview {
    /**
     * 日期分区
     */
    private Date dt;

    /**
     * 商品一级分类编号
     */
    private Integer itemFirstCateCd;

    /**
     * 商品一级品类名称
     */
    private String itemFirstCateName;

    /**
     * 商品二级分类编号
     */
    private Integer itemSecondCateCd;

    /**
     * 商品二级品类名称
     */
    private String itemSecondCateName;

    /**
     * 商品三级分类编号
     */
    private Integer itemThirdCateCd;

    /**
     * 商品三级品类名称
     */
    private String itemThirdCateName;

    /**
     * 销售订单 id
     */
    private String saleOrderId;

    /**
     * 服务单号 id
     */
    private String serviceOrderId;

    /**
     * 业务类型一级
     */
    private String businessType1;

    /**
     * 业务类型二级
     */
    private String businessType2;

    /**
     * 3级部门名称
     */
    private String deptName3;

    /**
     * 2级部门名称
     */
    private String deptName2;

    /**
     * 1级部门名称
     */
    private String deptName1;

    /**
     * 3级部门id
     */
    private Integer deptId3;

    /**
     * 2级部门id
     */
    private Integer deptId2;

    /**
     * 1级部门id
     */
    private Integer deptId1;

    /**
     * 主供应商简码
     */
    private String majorSuppBrevityCode;

    /**
     * 主供应商名称
     */
    private String majorSuppName;

    /**
     * 采购员ERP帐号
     */
    private String purchaserErpAcct;

    /**
     * 采购员昵称
     */
    private String purchaserName;

    /**
     * 销售员ERP帐号
     */
    private String salerErpAcct;

    /**
     * 销售员昵称
     */
    private String saleStafName;

    /**
     * POP运营人员erp账号
     */
    private String popOperatorErpAcct;

    /**
     * pop运营人员昵称
     */
    private String popOperatorName;

    /**
     * 一级区域划分
     */
    private String revAddrZoneFirst;

    /**
     * 收货省份id
     */
    private String revAddrProvinceId;

    /**
     * 收货省份 name
     */
    private String revAddrProvinceName;

    /**
     * 商品SPU名称
     */
    private String spuName;

    /**
     * 商品SPU编号
     */
    private Integer itemSpuId;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 门店id
     */
    private Integer storeId;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * POP商家编号
     */
    private Integer popVenderId;

    /**
     * POP商家名称
     */
    private String popVenderName;

    /**
     * 商品编号
     */
    private Integer itemSkuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 中文品牌名称
     */
    private String barndnameCn;

    /**
     * 品牌名称
     */
    private String barndnameFull;

    /**
     * 服务商id
     */
    private Integer mallVenderId;

    /**
     * 服务商名称
     */
    private String mallVenderName;

    /**
     * 服务模式
     */
    private String serviceMode;

    /**
     * 指标的最细粒度（例：sku、shop、cate1cate）
     */
    private String indexGrain;

    /**
     * 派单完整度分子
     */
    private Double orderCompletenessNumerator;

    /**
     * 派单完整度分母
     */
    private Double orderCompletenessDenominator;

    /**
     * 上门准时率分子
     */
    private Double ontimeNumerator;

    /**
     * 上门准时率分母
     */
    private Double ontimeDenominator;

    /**
     * 完工率分子
     */
    private Double completionNumerator;

    /**
     * 完工率分母
     */
    private Double completionDenominator;

    /**
     * 预约占比分子
     */
    private Double appointmentNumerator;

    /**
     * 预约占比分母
     */
    private Double appointmentDenominator;

    /**
     * PROMISE履约率分子
     */
    private Double promiseExecutionNumerator;

    /**
     * PROMISE履约率分母
     */
    private Double promiseExecutionDenominator;

    /**
     * 211 占比分子
     */
    private Double AccountsNumerator211;

    /**
     * 211 占比分母
     */
    private Double AccountsDenominator211;

    /**
     * 隔日达占比分子
     */
    private Double reachedNextDayNumerator;

    /**
     * 隔日达占比分母
     */
    private Double reachedNextDayDenominator;

    /**
     * 次日达占比分子
     */
    private Double reachedCiriDayNumerator;

    /**
     * 次日达占比分母
     */
    private Double reachedCiriDayDenominator;

    /**
     * PV现货率分子
     */
    private Double supplyChainStandardStockNumerator;

    /**
     * PV现货率分母
     */
    private Double supplyChainStandardStockDenominator;

    /**
     * 售后平均服务时长分子(电脑数码)
     */
    private Double afsServiceDurationAvgComputeDigitialNumerator;

    /**
     * 售后平均服务时长分母（电脑数码）
     */
    private Double afsServiceDurationAvgComputeDigitialDenominator;

    /**
     * 售后满意度分子（电脑数码）
     */
    private Double afsSatisfyComputeDigitialNumerator;

    /**
     * 售后满意度分母（电脑数码）
     */
    private Double afsSatisfyComputeDigitialDenominator;

    /**
     * QCR分子
     */
    private Double qcrQltyHitNumerator;

    /**
     * QCR分母
     */
    private Double qcrQltyHitDenominator;

    /**
     * 差评率（自营_3C）分子
     */
    private Double negativeCommetNumerator;

    /**
     * 差评率（自营_3C）分母
     */
    private Double negativeCommetDenominator;

    /**
     * 售后申请率（自营_3C）分子
     */
    private Double afterSaleNumerator;

    /**
     * 售后申请率（自营_3C）分母
     */
    private Double afterSaleDenominator;

    /**
     * 质量问题返修率（3C）分子-非追溯
     */
    private Double qualityProblemRepairNotraceNumerator;

    /**
     * 质量问题返修率（3C）分母-非追溯
     */
    private Double qualityProblemRepairNotraceDenominator;

    /**
     * 质量问题返修率（3C）分子-追溯
     */
    private Double qualityProblemRepairTraceNumerator;

    /**
     * 质量问题返修率（3C）分母-追溯
     */
    private Double qualityProblemRepairTraceDenominator;

    /**
     * 履约及时率分子
     */
    private Double medicineStandardHourlyDeliverHonourIntimeNumerator;

    /**
     * 履约及时率分母
     */
    private Double medicineStandardHourlyDeliverHonourIntimeDenominator;

    /**
     * 即时达占比分子
     */
    private Double immediateReachNumerator;

    /**
     * 即时达占比分母
     */
    private Double immediateReachDenominator;

    /**
     * 即时达平均妥投时长分子
     */
    private Double averageReachDeliveryTimeNumerator;

    /**
     * 即时达平均妥投时长分母
     */
    private Double averageReachDeliveryTimeDenominator;

    /**
     * 预约达平均妥投时长分子
     */
    private Double averageAppointmentDeliveryTimeNumerator;

    /**
     * 预约达平均妥投时长分母
     */
    private Double averageAppointmentDeliveryTimeDenominator;

    /**
     * 7天无理由问题占比分子
     */
    private Double aftersale7daysNoreasonquesRateNumerator;

    /**
     * 7天无理由问题占比分母
     */
    private Double aftersale7daysNoreasonquesRateDenominator;

    /**
     * 产品质量金额占比分子
     */
    private Double aftersaleProdQualityAmountRateNumerator;

    /**
     * 产品质量金额占比分母
     */
    private Double aftersaleProdQualityAmountRateDenominator;

    /**
     * 产品质量问题占比分子
     */
    private Double aftersaleQualityQuestionsRateNumerator;

    /**
     * 产品质量问题占比分母
     */
    private Double aftersaleQualityQuestionsRateDenominator;

    /**
     * 净满意度分子
     */
    private Double aftersaleNetSatisfactionNumerator;

    /**
     * 净满意度分母
     */
    private Double aftersaleNetSatisfactionDenominator;

    /**
     * 售后返修率分子
     */
    private Double aftersaleRebackRateNumerator;

    /**
     * 售后返修率分母
     */
    private Double aftersaleRebackRateDenominator;

    /**
     * 好评率分子
     */
    private Double aftersaleGoodCommentRateNumerator;

    /**
     * 好评率分母
     */
    private Double aftersaleGoodCommentRateDenominator;

    /**
     * 误购问题占比分子
     */
    private Double aftersaleMisbuyRateNumerator;

    /**
     * 误购问题占比分母
     */
    private Double aftersaleMisbuyRateDenominator;

    /**
     * 15秒应答率分子
     */
    private Double beforesale15srespondNumerator;

    /**
     * 15秒应答率分母
     */
    private Double beforesale15srespondDenominator;

    /**
     * 促成金额占比分子
     */
    private Double beforesalePromoteamountRateNumerator;

    /**
     * 促成金额占比分母
     */
    private Double beforesalePromoteamountRateDenominator;

    /**
     * 发送消息比分子
     */
    private Double beforesaleSendmsgRateNumerator;

    /**
     * 发送消息比分母
     */
    private Double beforesaleSendmsgRateDenominator;

    /**
     * 咨询用户占比分子
     */
    private Double beforesaleConsuleuserRateNumerator;

    /**
     * 咨询用户占比分母
     */
    private Double beforesaleConsuleuserRateDenominator;

    /**
     * 咨询转化率分子
     */
    private Double beforesaleConsultTransRateNumerator;

    /**
     * 咨询转化率分母
     */
    private Double beforesaleConsultTransRateDenominator;

    /**
     * 售前客服满意度分子
     */
    private Double beforesaleKefuSatisfyrateNumerator;

    /**
     * 售前客服满意度分母
     */
    private Double beforesaleKefuSatisfyrateDenominator;

    /**
     * 客单价分子
     */
    private Double beforesaleKedanjiaNumerator;

    /**
     * 客单价分母
     */
    private Double beforesaleKedanjiaDenominator;

    /**
     * 对话回合数分子
     */
    private Double beforesaleDialoguetimesNumerator;

    /**
     * 对话回合数分母
     */
    private Double beforesaleDialoguetimesDenominator;

    /**
     * 静默转化率分子
     */
    private Double beforesaleQuietTransrateNumerator;

    /**
     * 静默转化率分母
     */
    private Double beforesaleQuietTransrateDenominator;

    /**
     * 咨询量
     */
    private Double consultQtty;

    /**
     * 接待量
     */
    private Double receiveQtty;

    /**
     * 客服账号
     */
    private String custSerAcctNo;

}