package io.geekidea.springboot.assembly.demo.constant;

import lombok.Getter;

@Getter
public enum SortFieldEnum {

//    CUTCOUNT("cutCount", "设置截断数", "1"),
//    STARTTIME("startTime", "开始日期", "1"),
//    CALCULATEITEM("calculateItem", "更新频率", "1"),
//    SORTITEM("sortItem", "排序规则", "2"),
//    DASAN("dasan", "打散规则", "2"),
//    ONLYINTERVENTION("onlyIntervention", "只显示人工干预商品", "2"),
//
//    SORTITEM_OPTIMGMV("sortItem_optimGMV", "优选GMV", "0"),
//    SORTITEM_WEIGHTINFO("sortItem_weightInfo", "自定义排序指标", "0"),
//    SORTITEM_OPTIMUV("sortItem_optimUV", "优选UV", "0"),
//    SORTITEM_OPTIMBI("sortItem_optimBi", "算法优选", "0"),
//    SORTITEM_OPTIMTRANSFORRATIO("sortItem_optimTransforRatio", "优选转化率", "0"),
//
//    DASAN_1("dasan_1", "品牌打散", "0"),
//    DASAN_2("dasan_2", "品类打散", "0"),
//    DASAN_3("dasan_3", "部门打散", "0"),
//
//    ONLYINTERVENTION_0("onlyIntervention_0", "是", "0"),
//    ONLYINTERVENTION_1("onlyIntervention_1", "否", "0"),
//
//    SORTITEM_SHOP_GMV("sortItem_shopUvTransD30", "转化率", "0"),
//    SORTITEM_SHOP_TRANS("sortItem_shopSaleSumD30", "成交GMV", "0");

    //BI 排序相关的
    CUTCOUNT("cutCount", "设置截断数", "1"),
    STARTTIME("startTime", "开始日期", "1"),
    CALCULATEITEM("calculateItem", "更新频率", "1"),

    BI_UNLIMIT("biUnlimit", "优选top数量", "0"),

    SORTITEM_SELECTGMV("sortItem_selectGmv", "优选GMV", "0"),
    SORTITEM_SELECTRATIO("sortItem_selectRatio", "优选转化率", "0"),
    SORTITEM_OPTIMUV("sortItem_optimUV", "优选UV", "0"),
    SORTITEM_WXFACTORSCORE("sortItem_wxFactorScore", "微信域因子", "0");




    private String code;
    private String name;
    //0：默认，1:直接展示text内容，2：通过枚举转化text内容
    private String type;

    SortFieldEnum(String code, String name, String type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    public static SortFieldEnum getEnumBycode(String code) {
        if (null == code) {
            return null;
        }
        for (SortFieldEnum subEnum : SortFieldEnum.values()) {
            if (subEnum.getCode().equals(code)) {
                return subEnum;
            }
        }
        return null;
    }
}
