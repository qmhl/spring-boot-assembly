package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import io.geekidea.springboot.assembly.demo.utils.ProcessUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Slf4j
public class Test_85_驼峰转换 {
    public static void main(String[] args) {
        String str="sku_cate3_free_shipping_deal_order_rank_rate_7d";
        List<String> ckColumnList = Lists.newArrayList(str.split(","));
        for (String ckColumn : ckColumnList) {
            String camelCode = ProcessUtils.underline2Camel(ckColumn);
            System.out.println(camelCode);
        }

        String a="{\"wait\":1000}";
        List<String> ruleIdList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList("xxx");
        HashSet<String> strings = new HashSet<>(ruleIdList);
//        log.info("queryFailResult ruleIdList:{}",ruleIdList);
//        List<String> ruleIdList = Lists.newArrayList("e473c8b056472e07a6b5a5dcc9545fbc");
        String ruleId="x";
        if(CollectionUtils.isNotEmpty(strings) && !strings.contains(ruleId)){
            strings.add(ruleId);
        }
        System.out.println(strings);


        System.out.println("=========驼峰转下划线========");
        String s="deptInfo, categoryInfos, isDeliveryNewSku, expectDelFeatureTime, isXsgSku, shopScoreRankRateGrade, bmScoreRankRate, shopDisputeFactorScoreRank, isCShop, logisticsFactorScoreRank, sameDayExpressRateOrgin, ttTimeOrigin, skuSpecAttrList, spuItemQiGrade, dataType, skuD30DealAmtHb, skuD180DealSaleQtty, skuGoodCommentRate, avgPrice, orderCntRatio, l2LabelValue31829, l02IsFreeShipping29513, skuPriceStarFloor, skuPriceStar, l2Discount, innerItemUvRank, innerItemConverRate, innerParentOrdQtty, innerParentOrdQttyRank, innerDealAmtRank, skuPlusPreferIndex, skuPromotActTmList, venderId, shopId, mainBrandCode, spuBestCjNumNdSum";
        String[] sarry = s.split(",");
        for (String s1 : sarry) {
            // 驼峰转下划线
            String camelCode = ProcessUtils.humpToLine2(s1);
            System.out.println(camelCode);
        }




    }


}



