package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.geekidea.springboot.assembly.demo.constant.SortFieldEnum;
import io.geekidea.springboot.assembly.demo.utils.EasyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.TypeReference;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test_80 {

    private static final Map<String, String> map1 = new HashMap<>();

    static {
        map1.put("high", "高");
        map1.put("middle", "中");
        map1.put("low", "低");
    }

    public static void main(String[] args) throws Exception {
//        String json = "[{\"ruleName\":\"排序规则\",\"ruleContent\":[{\"code\":\"sortItem\",\"data\":[{\"text\":\"optimGMV\"}]},{\"code\":\"dasanV2\",\"data\":[{\"fieldName\":\"mainBrandCode\",\"text\":\"high\"},{\"fieldName\":\"itemThirdCateCd\",\"text\":\"high\"}]},{\"code\":\"onlyIntervention\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"spuDuplicate\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"interveneType\",\"data\":[{\"text\":\"0\"}]}]}]";
        // 前端数据: 定时-自定义排序
//        String json = "[{\"ruleName\":\"排序规则\",\"ruleContent\":[{\"code\":\"cutCount\",\"data\":[{\"text\":\"999\"}]},{\"code\":\"zySkuNum\",\"data\":[{\"text\":99}]},{\"code\":\"startTime\",\"data\":[{\"text\":\"2023-07-21 10:26:43\"}]},{\"code\":\"calculateItem\",\"data\":[{\"text\":\"24\"}]},{\"code\":\"sortScope\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"sortItem\",\"data\":[{\"text\":\"weightInfo\",\"skuPlusPreferIndex\":0.1,\"skuDimGmvScore\":0.1,\"skuDimBrandScore\":0.1,\"praiseCount\":0.1,\"skuDimUserScore\":0.1,\"skuDimAdScore\":0.1,\"clickUv\":0.1,\"advPrice\":0.1}]},{\"code\":\"realIndex\",\"data\":[{\"rPriceScope\":0.1,\"rGmv\":0.1,\"rOrdnum\":null,\"rAdvPrice\":null}]},{\"code\":\"dasanV2\",\"data\":[{\"fieldName\":\"mainBrandCode\",\"text\":\"high\"},{\"fieldName\":\"itemThirdCateCd\",\"text\":\"middle\"},{\"fieldName\":\"deptId3\",\"text\":\"low\"}]},{\"code\":\"onlyIntervention\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"spuDuplicate\",\"data\":[{\"text\":\"0\"}]}]}]\n";
        //  前端数据：bi
//        String json = "[{\"ruleName\":\"排序规则\",\"ruleContent\":[{\"code\":\"calculateItem\",\"data\":[{\"text\":\"24\"}]},{\"code\":\"biUnlimit\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"sortItem\",\"data\":[{\"text\":\"wxFactorScore\"}]}]}]\n";
        // 前端数据：人工筛选-无定义排序
//       String json="[{\"ruleName\":\"排序规则\",\"ruleContent\":[{\"code\":\"sortItem\",\"data\":[{\"text\":\"optimGMV\"}]},{\"code\":\"dasanV2\",\"data\":[{\"fieldName\":\"mainBrandCode\",\"text\":\"high\"},{\"fieldName\":\"itemThirdCateCd\",\"text\":\"middle\"}]},{\"code\":\"onlyIntervention\",\"data\":[{\"text\":\"2\"}]},{\"code\":\"spuDuplicate\",\"data\":[{\"text\":\"0\"}]}]}]\n";
//        =====================================人工筛选-==============================
//        // 1.前端数据： 人工筛选-自定义排序  无离线和实时指标一说
//        String json="[{\"ruleName\":\"排序规则\",\"ruleContent\":[{\"code\":\"sortItem\",\"data\":[{\"text\":\"weightInfo\",\"skuPlusPreferIndex\":0.1,\"skuDimGmvScore\":0.1,\"skuDimAdScore\":0.1,\"praiseCount\":0.1,\"skuDimBrandScore\":0.1,\"skuDimUserScore\":0.1,\"clickUv\":0.1,\"skuUserNewCate3InnerD180\":0.1,\"calSkuPreGmvOrderFirstDept\":0.1,\"GMV\":0.1}]},{\"code\":\"dasanV2\",\"data\":[{\"fieldName\":\"mainBrandCode\",\"text\":\"high\"},{\"fieldName\":\"itemThirdCateCd\",\"text\":\"middle\"}]},{\"code\":\"onlyIntervention\",\"data\":[{\"text\":\"2\"}]},{\"code\":\"spuDuplicate\",\"data\":[{\"text\":\"1\"}]}]}]\n";
//        // 1.魔盒数据配置： 人工排序
//        String mohesortJson="[{\"code\":\"sortItem\",\"defaultValue\":\"\",\"name\":\"排序规则\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"\",\"name\":\"关闭\"},{\"code\":\"optimGMV\",\"hint\":\"以商品过去1个月UV价值(GMV/曝光UV)数据表现排序，建议目标为UV价值时使用\",\"name\":\"GMV目标导向\"},{\"code\":\"optimTransforRatio\",\"hint\":\"以商品过去1个月转化率数据表现排序，建议目标为转化率时使用\",\"name\":\"转化率目标导向\"},{\"code\":\"weightInfo\",\"extralRule\":{\"type\":\"MetricLabels\",\"config\":[{\"code\":\"weightInfo2\",\"name\":\"\",\"metricList\":[{\"code\":\"skuPlusPreferIndex\",\"hint\":\"PLUS用户偏好指数是基于商品在PLUS用户下销售表现、流量表现、广告表现预估商品的PLUS偏好指数，指数越高，PLUS用户偏好程度越高\",\"name\":\"PLUS偏好指数\",\"type\":\"Input\"},{\"code\":\"skuDimGmvScore\",\"hint\":\"根据商品成交表现数据计算销售力得分，数值越大越好\",\"name\":\"销售力\",\"type\":\"Input\"},{\"code\":\"skuDimAdScore\",\"hint\":\"根据广告投入等数据计算营销力得分，数值越大越好。\",\"name\":\"营销力\",\"type\":\"Input\"},{\"code\":\"skuDimBrandScore\",\"hint\":\"根据品牌销售和广告等数据计算品牌力得分，数值越大越好\",\"name\":\"品牌力\",\"type\":\"Input\"},{\"code\":\"skuDimUserScore\",\"hint\":\"根据用户数据计算用户力得分，数值越大越好\",\"name\":\"用户力\",\"type\":\"Input\"},{\"code\":\"praiseCount\",\"hint\":\"\",\"name\":\"评论数\",\"type\":\"Input\"},{\"code\":\"clickUv\",\"hint\":\"商品对店铺引流uv数。\",\"name\":\"对店铺引流\",\"width\":\"100%\",\"dropList\":[{\"code\":\"clickUv\",\"name\":\"近1天\"},{\"code\":\"15ClickUv\",\"name\":\"近15天\"},{\"code\":\"30ClickUv\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"advPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"width\":\"100%\",\"dropList\":[{\"code\":\"advPrice\",\"name\":\"近1天\"},{\"code\":\"15AdvPrice\",\"name\":\"近15天\"},{\"code\":\"30AdvPrice\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"easyBuy\",\"name\":\"放心购\",\"type\":\"Input\"},{\"code\":\"exactPromiseRate\",\"hint\":\"商品物流时效按承诺时效妥投比率，数值越高越好。\",\"name\":\"物流时效履约率\",\"type\":\"Input\"},{\"code\":\"skuUserNewCate3InnerD180\",\"hint\":\"取近N天站内有成交订单的品类首购用户数累加\",\"name\":\"品类新用户数(站内)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3InnerD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3InnerD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3OuterD180\",\"hint\":\"取近N天站外有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(站外)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3OuterD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3OuterD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3AllD180\",\"hint\":\"取近N天全站有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(全站)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3AllD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3AllD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"tbd1\",\"hint\":\"活动爆品指数是通过算法模型学习商品供给侧表现、需求侧表现、流量侧用户与商品的交互行为、营销活动与商品的匹配行为共4大维度来预测商品在活动中的销售指数，并按照三级部门维度进行排名。排名及排名率越小，商品越靠前。\",\"width\":\"100%\",\"name\":\"爆品指数\",\"dropList\":[{\"code\":\"calSkuPreGmvOrderFirstDept\",\"name\":\"一级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateFirstDept\",\"name\":\"一级部门排名率\"},{\"code\":\"calSkuPreGmvOrderSecondDept\",\"name\":\"二级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateSecondDept\",\"name\":\"二级部门排名率\"},{\"code\":\"calSkuPreGmvOrder\",\"name\":\"三级部门排名\"},{\"code\":\"calSkuPreGmvOrderRate\",\"name\":\"三级部门排名率\"}],\"type\":\"SelectAndInput\"},{\"code\":\"GMV\",\"width\":\"100%\",\"name\":\"成交金额\",\"dropList\":[{\"code\":\"GMV\",\"name\":\"近1天\"},{\"code\":\"skuD7DealAmt\",\"name\":\"近7天\"},{\"code\":\"skuGmv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"addCartRate\",\"width\":\"100%\",\"name\":\"加购率\",\"dropList\":[{\"code\":\"addCartRate\",\"name\":\"近1天\"},{\"code\":\"skuAddCardUvRatioD30\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"UV\",\"width\":\"100%\",\"name\":\"UV\",\"dropList\":[{\"code\":\"UV\",\"name\":\"近1天\"},{\"code\":\"skuUv7d\",\"name\":\"近7天\"},{\"code\":\"skuUv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"praiseRate\",\"width\":\"100%\",\"name\":\"好评率\",\"dropList\":[{\"code\":\"praiseRate\",\"name\":\"近1天\"},{\"code\":\"skuD15GoodCommentRate\",\"name\":\"近15天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"orderCount\",\"width\":\"100%\",\"name\":\"订单量\",\"dropList\":[{\"code\":\"orderCount\",\"name\":\"近1天\"},{\"code\":\"skuD90DealSaleQtty\",\"name\":\"近90天\"},{\"code\":\"skuD180DealSaleQtty\",\"name\":\"近180天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"transforRatio\",\"width\":\"100%\",\"name\":\"转化率\",\"dropList\":[{\"code\":\"transforRatio\",\"name\":\"近1天\"},{\"code\":\"skuUvValue30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"uvHb\",\"width\":\"100%\",\"name\":\"UV环比\",\"dropList\":[{\"code\":\"skuUvD7Hb\",\"name\":\"近7天\"},{\"code\":\"skuUvD30Hb\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"}]}]},\"hint\":\"可在0.0-1.0区间范围内，自定义分配各指标的排序权重，权重总和需等于1\",\"name\":\"自定义排序指标\"},{\"code\":\"wxFactorScore\",\"hint\":\"综合评估商品微信域的转化效率、销量&流量、微信域渗透率、价格力度等，判断商品在微信域的竞争力分值；分值越高，竞争力越强\",\"name\":\"微信竞争力指数\"}]},{\"code\":\"dasanV2\",\"name\":\"丰富度\",\"type\":\"CheckBoxGroup\",\"config\":[{\"code\":\"mainBrandCode\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"品牌打散\"},{\"code\":\"itemThirdCateCd\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"品类打散\"},{\"code\":\"deptId3\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"部门打散\"}]},{\"code\":\"spuDuplicate\",\"defaultValue\":\"0\",\"name\":\"spu是否去重\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"0\",\"name\":\"否\"},{\"code\":\"1\",\"name\":\"是\"}]},{\"code\":\"onlyIntervention\",\"defaultValue\":\"0\",\"hint\":\"只展示干预商品：如设置类目A 取top10商品，只展示该类目下前10商品，补足阶段数也不进行补足;干预商品+非干预维度商品：设置人工干预维度的商品数，不足商品截断数时，用其他非干预维度商品补足。如品牌A下（或类目、部门等维度）商品数量过多露出时，可干预设置该品牌下商品数，截断数商品只可用其他品牌商品补足；干预商品+不限维度商品：设置人工干预维度的商品数，不足商品截断数时，可用底池中其他商品补足（包括干预维度和非干预维度下的商品），适用于力保品牌A（或类目、部门等维度）商品数量时，可干预设置该品牌下商品数，截断数商品可用品牌A其他商品和其他品牌商品补足。\",\"name\":\"商品展示设置\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"1\",\"name\":\"只展示干预商品\"},{\"code\":\"2\",\"name\":\"干预商品+非干预维度商品\"},{\"code\":\"0\",\"name\":\"干预商品+不限维度商品\"}]}]\n";
//
//        List<Map<String, Object>> listObjectSec = JSONArray.parseObject(json, List.class);
//        List<Map<String, Object>> mohesortList = JSONArray.parseObject(mohesortJson, List.class);
//
//        System.out.println("======前端 魔盒对比=======");
//        System.out.println(JSON.toJSONString(getSortNames(listObjectSec, mohesortList)));

//        // 2.前端数据： 人工筛选-自定义排序  无离线和实时指标一说
//        String json="[{\"ruleName\":\"排序规则\",\"ruleContent\":[{\"code\":\"sortItem\",\"data\":[{\"text\":\"wxFactorScore\"}]},{\"code\":\"dasanV2\",\"data\":[{\"fieldName\":\"mainBrandCode\",\"text\":\"high\"},{\"fieldName\":\"itemThirdCateCd\",\"text\":\"middle\"}]},{\"code\":\"onlyIntervention\",\"data\":[{\"text\":\"2\"}]},{\"code\":\"spuDuplicate\",\"data\":[{\"text\":\"1\"}]},{\"code\":\"interveneType\",\"data\":[{\"text\":\"1\"}]},{\"code\":\"interveneCategory\",\"data\":[{\"text\":1,\"fieldName\":\"itemThirdCateCd\"}]},{\"code\":\"interveneDept\",\"data\":[{\"text\":2,\"fieldName\":\"deptId2\"}]},{\"code\":\"interveneBrand\",\"data\":[{\"text\":3}]},{\"code\":\"interveneErp\",\"data\":[{\"text\":4}]},{\"code\":\"interveneShop\",\"data\":[{\"text\":5}]}]}]\n";
//        // 2.魔盒数据配置： 人工排序
//        String mohesortJson="[{\"code\":\"sortItem\",\"defaultValue\":\"\",\"name\":\"排序规则\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"\",\"name\":\"关闭\"},{\"code\":\"optimGMV\",\"hint\":\"以商品过去1个月UV价值(GMV/曝光UV)数据表现排序，建议目标为UV价值时使用\",\"name\":\"GMV目标导向\"},{\"code\":\"optimTransforRatio\",\"hint\":\"以商品过去1个月转化率数据表现排序，建议目标为转化率时使用\",\"name\":\"转化率目标导向\"},{\"code\":\"weightInfo\",\"extralRule\":{\"type\":\"MetricLabels\",\"config\":[{\"code\":\"weightInfo2\",\"name\":\"\",\"metricList\":[{\"code\":\"skuPlusPreferIndex\",\"hint\":\"PLUS用户偏好指数是基于商品在PLUS用户下销售表现、流量表现、广告表现预估商品的PLUS偏好指数，指数越高，PLUS用户偏好程度越高\",\"name\":\"PLUS偏好指数\",\"type\":\"Input\"},{\"code\":\"skuDimGmvScore\",\"hint\":\"根据商品成交表现数据计算销售力得分，数值越大越好\",\"name\":\"销售力\",\"type\":\"Input\"},{\"code\":\"skuDimAdScore\",\"hint\":\"根据广告投入等数据计算营销力得分，数值越大越好。\",\"name\":\"营销力\",\"type\":\"Input\"},{\"code\":\"skuDimBrandScore\",\"hint\":\"根据品牌销售和广告等数据计算品牌力得分，数值越大越好\",\"name\":\"品牌力\",\"type\":\"Input\"},{\"code\":\"skuDimUserScore\",\"hint\":\"根据用户数据计算用户力得分，数值越大越好\",\"name\":\"用户力\",\"type\":\"Input\"},{\"code\":\"praiseCount\",\"hint\":\"\",\"name\":\"评论数\",\"type\":\"Input\"},{\"code\":\"clickUv\",\"hint\":\"商品对店铺引流uv数。\",\"name\":\"对店铺引流\",\"width\":\"100%\",\"dropList\":[{\"code\":\"clickUv\",\"name\":\"近1天\"},{\"code\":\"15ClickUv\",\"name\":\"近15天\"},{\"code\":\"30ClickUv\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"advPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"width\":\"100%\",\"dropList\":[{\"code\":\"advPrice\",\"name\":\"近1天\"},{\"code\":\"15AdvPrice\",\"name\":\"近15天\"},{\"code\":\"30AdvPrice\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"easyBuy\",\"name\":\"放心购\",\"type\":\"Input\"},{\"code\":\"exactPromiseRate\",\"hint\":\"商品物流时效按承诺时效妥投比率，数值越高越好。\",\"name\":\"物流时效履约率\",\"type\":\"Input\"},{\"code\":\"skuUserNewCate3InnerD180\",\"hint\":\"取近N天站内有成交订单的品类首购用户数累加\",\"name\":\"品类新用户数(站内)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3InnerD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3InnerD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3OuterD180\",\"hint\":\"取近N天站外有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(站外)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3OuterD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3OuterD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3AllD180\",\"hint\":\"取近N天全站有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(全站)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3AllD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3AllD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"tbd1\",\"hint\":\"活动爆品指数是通过算法模型学习商品供给侧表现、需求侧表现、流量侧用户与商品的交互行为、营销活动与商品的匹配行为共4大维度来预测商品在活动中的销售指数，并按照三级部门维度进行排名。排名及排名率越小，商品越靠前。\",\"width\":\"100%\",\"name\":\"爆品指数\",\"dropList\":[{\"code\":\"calSkuPreGmvOrderFirstDept\",\"name\":\"一级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateFirstDept\",\"name\":\"一级部门排名率\"},{\"code\":\"calSkuPreGmvOrderSecondDept\",\"name\":\"二级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateSecondDept\",\"name\":\"二级部门排名率\"},{\"code\":\"calSkuPreGmvOrder\",\"name\":\"三级部门排名\"},{\"code\":\"calSkuPreGmvOrderRate\",\"name\":\"三级部门排名率\"}],\"type\":\"SelectAndInput\"},{\"code\":\"GMV\",\"width\":\"100%\",\"name\":\"成交金额\",\"dropList\":[{\"code\":\"GMV\",\"name\":\"近1天\"},{\"code\":\"skuD7DealAmt\",\"name\":\"近7天\"},{\"code\":\"skuGmv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"addCartRate\",\"width\":\"100%\",\"name\":\"加购率\",\"dropList\":[{\"code\":\"addCartRate\",\"name\":\"近1天\"},{\"code\":\"skuAddCardUvRatioD30\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"UV\",\"width\":\"100%\",\"name\":\"UV\",\"dropList\":[{\"code\":\"UV\",\"name\":\"近1天\"},{\"code\":\"skuUv7d\",\"name\":\"近7天\"},{\"code\":\"skuUv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"praiseRate\",\"width\":\"100%\",\"name\":\"好评率\",\"dropList\":[{\"code\":\"praiseRate\",\"name\":\"近1天\"},{\"code\":\"skuD15GoodCommentRate\",\"name\":\"近15天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"orderCount\",\"width\":\"100%\",\"name\":\"订单量\",\"dropList\":[{\"code\":\"orderCount\",\"name\":\"近1天\"},{\"code\":\"skuD90DealSaleQtty\",\"name\":\"近90天\"},{\"code\":\"skuD180DealSaleQtty\",\"name\":\"近180天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"transforRatio\",\"width\":\"100%\",\"name\":\"转化率\",\"dropList\":[{\"code\":\"transforRatio\",\"name\":\"近1天\"},{\"code\":\"skuUvValue30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"uvHb\",\"width\":\"100%\",\"name\":\"UV环比\",\"dropList\":[{\"code\":\"skuUvD7Hb\",\"name\":\"近7天\"},{\"code\":\"skuUvD30Hb\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"}]}]},\"hint\":\"可在0.0-1.0区间范围内，自定义分配各指标的排序权重，权重总和需等于1\",\"name\":\"自定义排序指标\"},{\"code\":\"wxFactorScore\",\"hint\":\"综合评估商品微信域的转化效率、销量&流量、微信域渗透率、价格力度等，判断商品在微信域的竞争力分值；分值越高，竞争力越强\",\"name\":\"微信竞争力指数\"}]},{\"code\":\"dasanV2\",\"name\":\"丰富度\",\"type\":\"CheckBoxGroup\",\"config\":[{\"code\":\"mainBrandCode\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"品牌打散\"},{\"code\":\"itemThirdCateCd\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"品类打散\"},{\"code\":\"deptId3\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"部门打散\"}]},{\"code\":\"spuDuplicate\",\"defaultValue\":\"0\",\"name\":\"spu是否去重\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"0\",\"name\":\"否\"},{\"code\":\"1\",\"name\":\"是\"}]},{\"code\":\"onlyIntervention\",\"defaultValue\":\"0\",\"hint\":\"只展示干预商品：如设置类目A 取top10商品，只展示该类目下前10商品，补足阶段数也不进行补足;干预商品+非干预维度商品：设置人工干预维度的商品数，不足商品截断数时，用其他非干预维度商品补足。如品牌A下（或类目、部门等维度）商品数量过多露出时，可干预设置该品牌下商品数，截断数商品只可用其他品牌商品补足；干预商品+不限维度商品：设置人工干预维度的商品数，不足商品截断数时，可用底池中其他商品补足（包括干预维度和非干预维度下的商品），适用于力保品牌A（或类目、部门等维度）商品数量时，可干预设置该品牌下商品数，截断数商品可用品牌A其他商品和其他品牌商品补足。\",\"name\":\"商品展示设置\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"1\",\"name\":\"只展示干预商品\"},{\"code\":\"2\",\"name\":\"干预商品+非干预维度商品\"},{\"code\":\"0\",\"name\":\"干预商品+不限维度商品\"}]}]\n";
//
//        List<Map<String, Object>> listObjectSec = JSONArray.parseObject(json, List.class);
//        List<Map<String, Object>> mohesortList = JSONArray.parseObject(mohesortJson, List.class);
//
//        System.out.println("======前端 魔盒对比=======");
//        System.out.println(JSON.toJSONString(getSortNames(listObjectSec, mohesortList)));
//
//        // 3.前端数据： 定时筛选-自定义排序  有离线和实时指标一说
//String json="[{\"ruleName\":\"排序规则\",\"ruleContent\":[{\"code\":\"cutCount\",\"data\":[{\"text\":\"2000\"}]},{\"code\":\"zySkuNum\",\"data\":[{\"text\":101}]},{\"code\":\"startTime\",\"data\":[{\"text\":\"2023-07-30 17:04:58\"}]},{\"code\":\"calculateItem\",\"data\":[{\"text\":\"24\"}]},{\"code\":\"sortScope\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"sortItem\",\"data\":[{\"text\":\"weightInfo\",\"skuPlusPreferIndex\":0.1,\"skuDimAdScore\":0.1,\"skuDimBrandScore\":0.1,\"skuDimGmvScore\":0.1,\"clickUv\":0.1,\"skuUserNewCate3InnerD180\":0.1,\"calSkuPreGmvOrderFirstDept\":0.1,\"l2InnerDealPermeateRateRank7d31925\":0.1}]},{\"code\":\"realIndex\",\"data\":[{\"rPriceScope\":0.1,\"rGmv\":0.1}]},{\"code\":\"dasanV2\",\"data\":[{\"fieldName\":\"itemThirdCateCd\",\"text\":\"high\"},{\"fieldName\":\"deptId3\",\"text\":\"middle\"},{\"fieldName\":\"mainBrandCode\",\"text\":\"high\"}]},{\"code\":\"onlyIntervention\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"spuDuplicate\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"interveneType\",\"data\":[{\"text\":\"1\"}]},{\"code\":\"interveneCategory\",\"data\":[{\"text\":1,\"fieldName\":\"itemFirstCateCd\"}]},{\"code\":\"interveneDept\",\"data\":[{\"text\":1,\"fieldName\":\"deptId2\"}]},{\"code\":\"interveneBrand\",\"data\":[{\"text\":1}]},{\"code\":\"interveneShop\",\"data\":[{\"text\":1}]}]}]\n";
//        // 3.魔盒数据配置： 定时排序
//        String mohesortJson = "[{\"isMulti\":true,\"code\":\"cutCount\",\"necessary\":\"true\",\"max\":{\"ythKuorong\":10000,\"default\":2000},\"hint\":\"当仅展示人工干预商品为【是】时，设置的人工干预商品数大于设置截断数时，按排序进行截断，小于等于设置截断数，按人工干预商品数下发；当仅展示人工干预商品为【否】时，设置的人工干预商品数大于设置截断数时，按排序进行截断，小于等于设置截断数时，用其他商品按排序取商品补足。\",\"name\":\"优选top数量\",\"type\":\"Input\"},{\"code\":\"startTime\",\"necessary\":\"true\",\"name\":\"开始日期\",\"type\":\"TimeLabel\"},{\"code\":\"calculateItem\",\"relateDefaultValue\":\"1\",\"necessary\":\"true\",\"defaultValue\":\"24\",\"name\":\"更新频率\",\"relateConfig\":[{\"code\":\"1\",\"name\":\"1小时\"}],\"type\":\"RadioGroup\",\"relateRuleConfig\":[{\"ruleCode\":\"purchasePrice\",\"body\":\"return value\"},{\"ruleCode\":\"purchaseDiscount\",\"body\":\"return value\"},{\"ruleCode\":\"lowPriceDayList\",\"body\":\"return value\"},{\"ruleCode\":\"pricestar2Floor\",\"body\":\"return value\"}],\"config\":[{\"code\":\"24\",\"name\":\"24小时\"}]},{\"code\":\"sortScope\",\"necessary\":\"true\",\"defaultValue\":\"0\",\"hint\":\"“多规则组统一排序”指左侧规则选出的全部商品均共享一套排序规则，如统一按照点击率排序。“多规则组各自排序”指左侧每套规则组均可设置各自的排序规则，如规则组1按点击率排序，规则组2按GMV排序\",\"name\":\"排序范围\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"0\",\"name\":\"多规则组统一排序\"},{\"code\":\"1\",\"name\":\"多规则组各自排序\"}]},{\"isMulti\":true,\"code\":\"sortItem\",\"necessary\":\"true\",\"defaultValue\":\"optimBi\",\"name\":\"排序规则\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"optimBi\",\"hint\":\"以商品过去1个月点击率数据表现排序，建议目标为UV点击率时使用\",\"name\":\"点击率目标导向\"},{\"code\":\"optimGMV\",\"hint\":\"以商品过去1个月UV价值(GMV/曝光UV)数据表现排序，建议目标为UV价值时使用\",\"name\":\"GMV目标导向\"},{\"code\":\"optimTransforRatio\",\"hint\":\"以商品过去1个月转化率数据表现排序，建议目标为转化率时使用\",\"name\":\"转化率目标导向\"},{\"code\":\"weightInfo\",\"extralRule\":{\"type\":\"MetricLabels\",\"config\":[{\"code\":\"realIndex\",\"name\":\"实时指标\",\"metricList\":[{\"code\":\"rPriceScope\",\"name\":\"价格力\",\"type\":\"Input\"},{\"code\":\"rGmv\",\"name\":\"成交GMV\",\"type\":\"Input\"},{\"code\":\"rOrdnum\",\"name\":\"成交订单量\",\"type\":\"Input\"},{\"code\":\"rAdvPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"type\":\"Input\"}]},{\"code\":\"weightInfo2\",\"name\":\"离线指标\",\"metricList\":[{\"code\":\"skuPlusPreferIndex\",\"hint\":\"PLUS用户偏好指数是基于商品在PLUS用户下销售表现、流量表现、广告表现预估商品的PLUS偏好指数，指数越高，PLUS用户偏好程度越高\",\"name\":\"PLUS偏好指数\",\"type\":\"Input\"},{\"code\":\"skuDimGmvScore\",\"hint\":\"根据商品成交表现数据计算销售力得分，数值越大越好\",\"name\":\"销售力\",\"type\":\"Input\"},{\"code\":\"skuDimAdScore\",\"hint\":\"根据广告投入等数据计算营销力得分，数值越大越好。\",\"name\":\"营销力\",\"type\":\"Input\"},{\"code\":\"skuDimBrandScore\",\"hint\":\"根据品牌销售和广告等数据计算品牌力得分，数值越大越好\",\"name\":\"品牌力\",\"type\":\"Input\"},{\"code\":\"skuDimUserScore\",\"hint\":\"根据用户数据计算用户力得分，数值越大越好\",\"name\":\"用户力\",\"type\":\"Input\"},{\"code\":\"praiseCount\",\"hint\":\"\",\"name\":\"评论数\",\"type\":\"Input\"},{\"code\":\"clickUv\",\"hint\":\"商品对店铺引流uv数。\",\"name\":\"对店铺引流\",\"width\":\"100%\",\"dropList\":[{\"code\":\"clickUv\",\"name\":\"近1天\"},{\"code\":\"15ClickUv\",\"name\":\"近15天\"},{\"code\":\"30ClickUv\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"advPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"width\":\"100%\",\"dropList\":[{\"code\":\"advPrice\",\"name\":\"近1天\"},{\"code\":\"15AdvPrice\",\"name\":\"近15天\"},{\"code\":\"30AdvPrice\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"exactPromiseRate\",\"hint\":\"商品物流时效按承诺时效妥投比率，数值越高越好。\",\"name\":\"物流时效履约率\",\"type\":\"Input\"},{\"code\":\"depositRate\",\"name\":\"定金支付率\",\"type\":\"Input\"},{\"code\":\"presaleOrderCount\",\"name\":\"预售订单量\",\"type\":\"Input\"},{\"code\":\"skuUserNewCate3InnerD180\",\"hint\":\"取近N天站内有成交订单的品类首购用户数累加\",\"name\":\"品类新用户数(站内)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3InnerD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3InnerD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3OuterD180\",\"hint\":\"取近N天站外有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(站外)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3OuterD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3OuterD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3AllD180\",\"hint\":\"取近N天全站有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(全站)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3AllD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3AllD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"tbd1\",\"hint\":\"活动爆品指数是通过算法模型学习商品供给侧表现、需求侧表现、流量侧用户与商品的交互行为、营销活动与商品的匹配行为共4大维度来预测商品在活动中的销售指数，并按照三级部门维度进行排名。排名及排名率越小，商品越靠前。\",\"width\":\"100%\",\"name\":\"爆品指数\",\"dropList\":[{\"code\":\"calSkuPreGmvOrderFirstDept\",\"name\":\"一级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateFirstDept\",\"name\":\"一级部门排名率\"},{\"code\":\"calSkuPreGmvOrderSecondDept\",\"name\":\"二级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateSecondDept\",\"name\":\"二级部门排名率\"},{\"code\":\"calSkuPreGmvOrder\",\"name\":\"三级部门排名\"},{\"code\":\"calSkuPreGmvOrderRate\",\"name\":\"三级部门排名率\"}],\"type\":\"SelectAndInput\"},{\"code\":\"GMV\",\"width\":\"100%\",\"name\":\"成交金额\",\"dropList\":[{\"code\":\"GMV\",\"name\":\"近1天\"},{\"code\":\"skuD7DealAmt\",\"name\":\"近7天\"},{\"code\":\"skuGmv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"addCartRate\",\"width\":\"100%\",\"name\":\"加购率\",\"dropList\":[{\"code\":\"addCartRate\",\"name\":\"近1天\"},{\"code\":\"skuAddCardUvRatioD30\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"UV\",\"width\":\"100%\",\"name\":\"UV\",\"dropList\":[{\"code\":\"UV\",\"name\":\"近1天\"},{\"code\":\"skuUv7d\",\"name\":\"近7天\"},{\"code\":\"skuUv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"praiseRate\",\"width\":\"100%\",\"name\":\"好评率\",\"dropList\":[{\"code\":\"praiseRate\",\"name\":\"近1天\"},{\"code\":\"skuD15GoodCommentRate\",\"name\":\"近15天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"orderCount\",\"width\":\"100%\",\"name\":\"订单量\",\"dropList\":[{\"code\":\"orderCount\",\"name\":\"近1天\"},{\"code\":\"skuD90DealSaleQtty\",\"name\":\"近90天\"},{\"code\":\"skuD180DealSaleQtty\",\"name\":\"近180天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"transforRatio\",\"width\":\"100%\",\"name\":\"转化率\",\"dropList\":[{\"code\":\"transforRatio\",\"name\":\"近1天\"},{\"code\":\"skuUvValue30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"uvHb\",\"width\":\"100%\",\"name\":\"UV环比\",\"dropList\":[{\"code\":\"skuUvD7Hb\",\"name\":\"近7天\"},{\"code\":\"skuUvD30Hb\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"l02Score31413\",\"hint\":\"微信域竞争力指数\",\"name\":\"微信域竞争力指数\",\"type\":\"Input\"},{\"code\":\"innerItemUv\",\"width\":\"100%\",\"name\":\"微信域近N天商详UV\",\"dropList\":[{\"code\":\"l2InnerItemUv7d31903\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUv15d31909\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemUv30d31908\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemUvRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详UV排名率\",\"dropList\":[{\"code\":\"l2InnerItemUvRank7d31919\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUvRank15d31933\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemUvRank30d31934\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天商详UV渗透率\",\"dropList\":[{\"code\":\"l2InnerItemPermeateRate15d31927\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUv15d31909\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemPermeateRate30d31928\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详UV渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerItemPermeateRateRank7d31923\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemPermeateRateRank15d31941\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemPermeateRateRank30d31942\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemConverRate\",\"width\":\"100%\",\"name\":\"微信域近N天商详转化率\",\"dropList\":[{\"code\":\"l2InnerItemConverRate7d31904\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemConverRate15d31910\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemConverRate30d31911\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemConverRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详转化率排名率\",\"dropList\":[{\"code\":\"l2InnerItemConverRateRank7d31920\",\"name\":\"近1天\"},{\"code\":\"l2InnerItemConverRateRank15d31935\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemConverRateRank30d31936\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerParentOrdQtty\",\"width\":\"100%\",\"name\":\"微信域近N天成交父单量\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRate7d31917\",\"name\":\"近7天\"},{\"code\":\"l2InnerParentOrdQtty15d31912\",\"name\":\"近15天\"},{\"code\":\"l2InnerParentOrdQtty30d31913\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerParentOrdQttyRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交父单量排名率\",\"dropList\":[{\"code\":\"l2InnerParentOrdQttyRank7d31921\",\"name\":\"近7天\"},{\"code\":\"l2InnerParentOrdQttyRank15d31937\",\"name\":\"近15天\"},{\"code\":\"l2InnerParentOrdQttyRank30d31938\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerOrdsPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天成交父单量渗透率\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRate7d31917\",\"name\":\"近7天\"},{\"code\":\"l2InnerOrdsPermeateRate15d31929\",\"name\":\"近15天\"},{\"code\":\"l2InnerOrdsPermeateRate30d31930\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerOrdsPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交父单量渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRateRank7d31924\",\"name\":\"近7天\"},{\"code\":\"l2InnerOrdsPermeateRateRank15d31943\",\"name\":\"近15天\"},{\"code\":\"l2InnerOrdsPermeateRateRank30d31944\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealAmt\",\"width\":\"100%\",\"name\":\"微信域近N天成交金额\",\"dropList\":[{\"code\":\"l2InnerDealAmt7d31906\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealAmt15d31914\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealAmt30d31915\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealAmtRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交金额排名率\",\"dropList\":[{\"code\":\"l2InnerDealAmtRank7d31922\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealAmtRank15d31939\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealAmtRank30d31940\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天成交金额渗透率\",\"dropList\":[{\"code\":\"l2InnerDealPermeateRate7d31918\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealPermeateRate15d31931\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealPermeateRate30d31932\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"InnerDealPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交金额渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerDealPermeateRateRank7d31925\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealPermeateRateRank15d31945\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealPermeateRateRank30d31946\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"}]}]},\"hint\":\"可在0.0-1.0区间范围内，自定义分配各指标的排序权重，权重总和需等于1\",\"name\":\"自定义排序指标\"},{\"code\":\"wxFactorScore\",\"hint\":\"综合评估商品微信域的转化效率、销量&流量、微信域渗透率、价格力度等，判断商品在微信域的竞争力分值；分值越高，竞争力越强\",\"name\":\"微信竞争力指数\"}]},{\"isMulti\":true,\"code\":\"zySkuNum\",\"hint\":\"1、若未在输入框中填写数字，则不对选品结果进行额外自营商品数量控制 2、若输入框中填写数字，则对最终商品池内的自营商品数量进行如下控制： 当满足规则的自营商品数量足够时，将保证圈选的商品中自营商品数量等于输入框中所填数量； 当满足规则的自营商品数量不足时，将保证所有满足规则的自营商品均被选中 3、此处配置与“更多详细配置-人工干预”中的“自营商品数量控制”互斥，请在两处中最多选择一处进行配置”\",\"name\":\"自营商品数量控制\",\"type\":\"Input\"},{\"isMulti\":true,\"code\":\"dasanV2\",\"name\":\"丰富度\",\"type\":\"CheckBoxGroup\",\"config\":[{\"code\":\"mainBrandCode\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"品牌打散\"},{\"code\":\"itemThirdCateCd\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"品类打散\"},{\"code\":\"deptId3\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"部门打散\"}]},{\"isMulti\":true,\"code\":\"spuDuplicate\",\"defaultValue\":\"0\",\"name\":\"spu是否去重\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"0\",\"name\":\"否\"},{\"code\":\"1\",\"name\":\"是\"}]},{\"isMulti\":true,\"code\":\"onlyIntervention\",\"defaultValue\":\"0\",\"hint\":\"<div>只展示干预商品：如设置类目A 取top10商品，只展示该类目下前10商品，补足阶段数也不进行补足;干预商品+非干预维度商品：设置人工干预维度的商品数，不足商品截断数时，用其他非干预维度商品补足。如品牌A下（或类目、部门等维度）商品数量过多露出时，可干预设置该品牌下商品数，截断数商品只可用其他品牌商品补足；<br />干预商品+不限维度商品：设置人工干预维度的商品数，不足商品截断数时，可用底池中其他商品补足（包括干预维度和非干预维度下的商品），适用于力保品牌A（或类目、部门等维度）商品数量时，可干预设置该品牌下商品数，截断数商品可用品牌A其他商品和其他品牌商品补足。</div>\",\"name\":\"商品展示设置\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"1\",\"name\":\"只展示干预商品\"},{\"code\":\"2\",\"name\":\"干预商品+非干预维度商品\"},{\"code\":\"0\",\"name\":\"干预商品+不限维度商品\"}]}]\n";
//        List<Map<String, Object>> listObjectSec = JSONArray.parseObject(json, List.class);
//        List<Map<String, Object>> mohesortList = JSONArray.parseObject(mohesortJson, List.class);
//
//        System.out.println("======前端 魔盒对比=======");
//        System.out.println(JSON.toJSONString(getSortNames(listObjectSec, mohesortList)));
        // 4.前端数据： 147底池-定时筛选-自定义排序  有离线和实时指标一说
        String json="[{\"ruleContent\":[{\"code\":\"cutCount\",\"data\":[{\"text\":\"9990\"}]},{\"code\":\"startTime\",\"data\":[{\"text\":\"2023-07-18 15:03:00\"}]},{\"code\":\"calculateItem\",\"data\":[{\"text\":\"24\"}]},{\"code\":\"sortScope\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"sortItem\",\"data\":[{\"l2ItemDetailFreeUv31961\":1,\"text\":\"newSelectionWeightInfo\"}]},{\"code\":\"onlyIntervention\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"spuDuplicate\",\"data\":[{\"text\":\"0\"}]}],\"ruleName\":\"排序规则\"}]\n";
        // 4.魔盒数据配置： 147底池-定时排序
String mohesortJson="[{\"isMulti\":true,\"code\":\"cutCount\",\"necessary\":\"true\",\"max\":{\"ythKuorong\":10000,\"default\":2000},\"hint\":\"当仅展示人工干预商品为【是】时，设置的人工干预商品数大于设置截断数时，按排序进行截断，小于等于设置截断数，按人工干预商品数下发；当仅展示人工干预商品为【否】时，设置的人工干预商品数大于设置截断数时，按排序进行截断，小于等于设置截断数时，用其他商品按排序取商品补足。\",\"name\":\"优选top数量\",\"type\":\"Input\"},{\"code\":\"startTime\",\"necessary\":\"true\",\"name\":\"开始日期\",\"type\":\"TimeLabel\"},{\"code\":\"calculateItem\",\"relateDefaultValue\":\"1\",\"necessary\":\"true\",\"defaultValue\":\"24\",\"name\":\"更新频率\",\"relateConfig\":[{\"code\":\"1\",\"name\":\"1小时\"}],\"type\":\"RadioGroup\",\"relateRuleConfig\":[{\"ruleCode\":\"purchasePrice\",\"body\":\"return value\"},{\"ruleCode\":\"purchaseDiscount\",\"body\":\"return value\"},{\"ruleCode\":\"lowPriceDayList\",\"body\":\"return value\"},{\"ruleCode\":\"pricestar2Floor\",\"body\":\"return value\"}],\"config\":[{\"code\":\"24\",\"name\":\"24小时\"}]},{\"code\":\"sortScope\",\"necessary\":\"true\",\"defaultValue\":\"0\",\"hint\":\"“多规则组统一排序”指左侧规则选出的全部商品均共享一套排序规则，如统一按照点击率排序。“多规则组各自排序”指左侧每套规则组均可设置各自的排序规则，如规则组1按点击率排序，规则组2按GMV排序\",\"name\":\"排序范围\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"0\",\"name\":\"多规则组统一排序\"},{\"code\":\"1\",\"name\":\"多规则组各自排序\"}]},{\"isMulti\":true,\"code\":\"sortItem\",\"necessary\":\"true\",\"defaultValue\":\"optimBi\",\"name\":\"排序规则\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"optimBi\",\"hint\":\"以商品过去1个月点击率数据表现排序，建议目标为UV点击率时使用\",\"name\":\"点击率目标导向\"},{\"code\":\"optimGMV\",\"hint\":\"以商品过去1个月UV价值(GMV/曝光UV)数据表现排序，建议目标为UV价值时使用\",\"name\":\"GMV目标导向\"},{\"code\":\"optimTransforRatio\",\"hint\":\"以商品过去1个月转化率数据表现排序，建议目标为转化率时使用\",\"name\":\"转化率目标导向\"},{\"code\":\"weightInfo\",\"extralRule\":{\"type\":\"MetricLabels\",\"config\":[{\"code\":\"realIndex\",\"name\":\"实时指标\",\"metricList\":[{\"code\":\"rPriceScope\",\"name\":\"价格力\",\"type\":\"Input\"},{\"code\":\"rGmv\",\"name\":\"成交GMV\",\"type\":\"Input\"},{\"code\":\"rOrdnum\",\"name\":\"成交订单量\",\"type\":\"Input\"},{\"code\":\"rAdvPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"type\":\"Input\"}]},{\"code\":\"weightInfo2\",\"name\":\"离线指标\",\"metricList\":[{\"code\":\"skuPlusPreferIndex\",\"hint\":\"PLUS用户偏好指数是基于商品在PLUS用户下销售表现、流量表现、广告表现预估商品的PLUS偏好指数，指数越高，PLUS用户偏好程度越高\",\"name\":\"PLUS偏好指数\",\"type\":\"Input\"},{\"code\":\"skuDimGmvScore\",\"hint\":\"根据商品成交表现数据计算销售力得分，数值越大越好\",\"name\":\"销售力\",\"type\":\"Input\"},{\"code\":\"skuDimAdScore\",\"hint\":\"根据广告投入等数据计算营销力得分，数值越大越好。\",\"name\":\"营销力\",\"type\":\"Input\"},{\"code\":\"skuDimBrandScore\",\"hint\":\"根据品牌销售和广告等数据计算品牌力得分，数值越大越好\",\"name\":\"品牌力\",\"type\":\"Input\"},{\"code\":\"skuDimUserScore\",\"hint\":\"根据用户数据计算用户力得分，数值越大越好\",\"name\":\"用户力\",\"type\":\"Input\"},{\"code\":\"praiseCount\",\"hint\":\"\",\"name\":\"评论数\",\"type\":\"Input\"},{\"code\":\"clickUv\",\"hint\":\"商品对店铺引流uv数。\",\"name\":\"对店铺引流\",\"width\":\"100%\",\"dropList\":[{\"code\":\"clickUv\",\"name\":\"近1天\"},{\"code\":\"15ClickUv\",\"name\":\"近15天\"},{\"code\":\"30ClickUv\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"advPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"width\":\"100%\",\"dropList\":[{\"code\":\"advPrice\",\"name\":\"近1天\"},{\"code\":\"15AdvPrice\",\"name\":\"近15天\"},{\"code\":\"30AdvPrice\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"exactPromiseRate\",\"hint\":\"商品物流时效按承诺时效妥投比率，数值越高越好。\",\"name\":\"物流时效履约率\",\"type\":\"Input\"},{\"code\":\"depositRate\",\"name\":\"定金支付率\",\"type\":\"Input\"},{\"code\":\"presaleOrderCount\",\"name\":\"预售订单量\",\"type\":\"Input\"},{\"code\":\"skuUserNewCate3InnerD180\",\"hint\":\"取近N天站内有成交订单的品类首购用户数累加\",\"name\":\"品类新用户数(站内)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3InnerD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3InnerD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3OuterD180\",\"hint\":\"取近N天站外有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(站外)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3OuterD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3OuterD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3AllD180\",\"hint\":\"取近N天全站有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(全站)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3AllD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3AllD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"tbd1\",\"hint\":\"活动爆品指数是通过算法模型学习商品供给侧表现、需求侧表现、流量侧用户与商品的交互行为、营销活动与商品的匹配行为共4大维度来预测商品在活动中的销售指数，并按照三级部门维度进行排名。排名及排名率越小，商品越靠前。\",\"width\":\"100%\",\"name\":\"爆品指数\",\"dropList\":[{\"code\":\"calSkuPreGmvOrderFirstDept\",\"name\":\"一级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateFirstDept\",\"name\":\"一级部门排名率\"},{\"code\":\"calSkuPreGmvOrderSecondDept\",\"name\":\"二级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateSecondDept\",\"name\":\"二级部门排名率\"},{\"code\":\"calSkuPreGmvOrder\",\"name\":\"三级部门排名\"},{\"code\":\"calSkuPreGmvOrderRate\",\"name\":\"三级部门排名率\"}],\"type\":\"SelectAndInput\"},{\"code\":\"GMV\",\"width\":\"100%\",\"name\":\"成交金额\",\"dropList\":[{\"code\":\"GMV\",\"name\":\"近1天\"},{\"code\":\"skuD7DealAmt\",\"name\":\"近7天\"},{\"code\":\"skuGmv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"addCartRate\",\"width\":\"100%\",\"name\":\"加购率\",\"dropList\":[{\"code\":\"addCartRate\",\"name\":\"近1天\"},{\"code\":\"skuAddCardUvRatioD30\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"UV\",\"width\":\"100%\",\"name\":\"UV\",\"dropList\":[{\"code\":\"UV\",\"name\":\"近1天\"},{\"code\":\"skuUv7d\",\"name\":\"近7天\"},{\"code\":\"skuUv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"praiseRate\",\"width\":\"100%\",\"name\":\"好评率\",\"dropList\":[{\"code\":\"praiseRate\",\"name\":\"近1天\"},{\"code\":\"skuD15GoodCommentRate\",\"name\":\"近15天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"orderCount\",\"width\":\"100%\",\"name\":\"订单量\",\"dropList\":[{\"code\":\"orderCount\",\"name\":\"近1天\"},{\"code\":\"skuD90DealSaleQtty\",\"name\":\"近90天\"},{\"code\":\"skuD180DealSaleQtty\",\"name\":\"近180天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"transforRatio\",\"width\":\"100%\",\"name\":\"转化率\",\"dropList\":[{\"code\":\"transforRatio\",\"name\":\"近1天\"},{\"code\":\"skuUvValue30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"uvHb\",\"width\":\"100%\",\"name\":\"UV环比\",\"dropList\":[{\"code\":\"skuUvD7Hb\",\"name\":\"近7天\"},{\"code\":\"skuUvD30Hb\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"l02Score31413\",\"hint\":\"微信域竞争力指数\",\"name\":\"微信域竞争力指数\",\"type\":\"Input\"},{\"code\":\"innerItemUv\",\"width\":\"100%\",\"name\":\"微信域近N天商详UV\",\"dropList\":[{\"code\":\"l2InnerItemUv7d31903\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUv15d31909\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemUv30d31908\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemUvRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详UV排名率\",\"dropList\":[{\"code\":\"l2InnerItemUvRank7d31919\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUvRank15d31933\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemUvRank30d31934\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天商详UV渗透率\",\"dropList\":[{\"code\":\"l2InnerItemPermeateRate15d31927\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUv15d31909\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemPermeateRate30d31928\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详UV渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerItemPermeateRateRank7d31923\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemPermeateRateRank15d31941\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemPermeateRateRank30d31942\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemConverRate\",\"width\":\"100%\",\"name\":\"微信域近N天商详转化率\",\"dropList\":[{\"code\":\"l2InnerItemConverRate7d31904\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemConverRate15d31910\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemConverRate30d31911\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemConverRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详转化率排名率\",\"dropList\":[{\"code\":\"l2InnerItemConverRateRank7d31920\",\"name\":\"近1天\"},{\"code\":\"l2InnerItemConverRateRank15d31935\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemConverRateRank30d31936\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerParentOrdQtty\",\"width\":\"100%\",\"name\":\"微信域近N天成交父单量\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRate7d31917\",\"name\":\"近7天\"},{\"code\":\"l2InnerParentOrdQtty15d31912\",\"name\":\"近15天\"},{\"code\":\"l2InnerParentOrdQtty30d31913\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerParentOrdQttyRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交父单量排名率\",\"dropList\":[{\"code\":\"l2InnerParentOrdQttyRank7d31921\",\"name\":\"近7天\"},{\"code\":\"l2InnerParentOrdQttyRank15d31937\",\"name\":\"近15天\"},{\"code\":\"l2InnerParentOrdQttyRank30d31938\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerOrdsPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天成交父单量渗透率\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRate7d31917\",\"name\":\"近7天\"},{\"code\":\"l2InnerOrdsPermeateRate15d31929\",\"name\":\"近15天\"},{\"code\":\"l2InnerOrdsPermeateRate30d31930\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerOrdsPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交父单量渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRateRank7d31924\",\"name\":\"近7天\"},{\"code\":\"l2InnerOrdsPermeateRateRank15d31943\",\"name\":\"近15天\"},{\"code\":\"l2InnerOrdsPermeateRateRank30d31944\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealAmt\",\"width\":\"100%\",\"name\":\"微信域近N天成交金额\",\"dropList\":[{\"code\":\"l2InnerDealAmt7d31906\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealAmt15d31914\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealAmt30d31915\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealAmtRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交金额排名率\",\"dropList\":[{\"code\":\"l2InnerDealAmtRank7d31922\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealAmtRank15d31939\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealAmtRank30d31940\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天成交金额渗透率\",\"dropList\":[{\"code\":\"l2InnerDealPermeateRate7d31918\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealPermeateRate15d31931\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealPermeateRate30d31932\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"InnerDealPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交金额渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerDealPermeateRateRank7d31925\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealPermeateRateRank15d31945\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealPermeateRateRank30d31946\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"}]}]},\"hint\":\"可在0.0-1.0区间范围内，自定义分配各指标的排序权重，权重总和需等于1\",\"name\":\"自定义排序指标\"},{\"code\":\"wxFactorScore\",\"hint\":\"综合评估商品微信域的转化效率、销量&流量、微信域渗透率、价格力度等，判断商品在微信域的竞争力分值；分值越高，竞争力越强\",\"name\":\"微信竞争力指数\"},{\"code\":\"newSelectionWeightInfo\",\"extralRule\":{\"type\":\"MetricLabels\",\"config\":[{\"code\":\"weightInfo2\",\"name\":\"指标\",\"metricList\":[{\"code\":\"l2ItemDetailFreeUv31961\",\"name\":\"近7天新品日均商详免费UV\",\"type\":\"Input\"},{\"code\":\"skuD7DealSaleNumDayAvg\",\"name\":\"近7天商品日均成交数量\",\"type\":\"Input\"},{\"code\":\"skuD7DealAmt\",\"name\":\"SKU近7天成交金额\",\"type\":\"Input\"},{\"code\":\"l2ConsumeAmt30d31874\",\"name\":\"新品近30天广告累计消耗金额\",\"type\":\"Input\"},{\"code\":\"l2ItemSearchUvCtr31963\",\"name\":\"近7天新品搜索UV点击率\",\"type\":\"Input\"},{\"code\":\"l2ItemRecommendUvCtr31964\",\"name\":\"近7天新品推荐UV点击率\",\"type\":\"Input\"},{\"code\":\"cate3SearchConversionRankRate7d\",\"name\":\"近7日搜索转化排名率\",\"type\":\"Input\"},{\"code\":\"sxTrans7d\",\"name\":\"7天商详转化排名率\",\"type\":\"Input\"}]}]},\"hint\":\"综合评估商品的成交、广告数据，以及在频道和搜推长的表现等，进而对商品进行排序，支持配置指标占比，适用于新品场域。\",\"name\":\"新品排序指标\"}]},{\"isMulti\":true,\"code\":\"zySkuNum\",\"hint\":\"1、若未在输入框中填写数字，则不对选品结果进行额外自营商品数量控制 2、若输入框中填写数字，则对最终商品池内的自营商品数量进行如下控制： 当满足规则的自营商品数量足够时，将保证圈选的商品中自营商品数量等于输入框中所填数量； 当满足规则的自营商品数量不足时，将保证所有满足规则的自营商品均被选中 3、此处配置与“更多详细配置-人工干预”中的“自营商品数量控制”互斥，请在两处中最多选择一处进行配置”\",\"name\":\"自营商品数量控制\",\"type\":\"Input\"},{\"isMulti\":true,\"code\":\"dasanV2\",\"name\":\"丰富度\",\"type\":\"CheckBoxGroup\",\"config\":[{\"code\":\"mainBrandCode\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"品牌打散\"},{\"code\":\"itemThirdCateCd\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"品类打散\"},{\"code\":\"deptId3\",\"extralRule\":{\"name\":\"品类丰富度\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"high\",\"name\":\"高\"},{\"code\":\"middle\",\"name\":\"中\"},{\"code\":\"low\",\"name\":\"低\"}]},\"name\":\"部门打散\"}]},{\"isMulti\":true,\"code\":\"spuDuplicate\",\"defaultValue\":\"0\",\"name\":\"spu是否去重\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"0\",\"name\":\"否\"},{\"code\":\"1\",\"name\":\"是\"}]},{\"isMulti\":true,\"code\":\"onlyIntervention\",\"defaultValue\":\"0\",\"hint\":\"<div>只展示干预商品：如设置类目A 取top10商品，只展示该类目下前10商品，补足阶段数也不进行补足;干预商品+非干预维度商品：设置人工干预维度的商品数，不足商品截断数时，用其他非干预维度商品补足。如品牌A下（或类目、部门等维度）商品数量过多露出时，可干预设置该品牌下商品数，截断数商品只可用其他品牌商品补足；<br />干预商品+不限维度商品：设置人工干预维度的商品数，不足商品截断数时，可用底池中其他商品补足（包括干预维度和非干预维度下的商品），适用于力保品牌A（或类目、部门等维度）商品数量时，可干预设置该品牌下商品数，截断数商品可用品牌A其他商品和其他品牌商品补足。</div>\",\"name\":\"商品展示设置\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"1\",\"name\":\"只展示干预商品\"},{\"code\":\"2\",\"name\":\"干预商品+非干预维度商品\"},{\"code\":\"0\",\"name\":\"干预商品+不限维度商品\"}]}]\n";
        List<Map<String, Object>> listObjectSec = JSONArray.parseObject(json, List.class);
        List<Map<String, Object>> mohesortList = JSONArray.parseObject(mohesortJson, List.class);

        System.out.println("======前端 魔盒对比=======");
        System.out.println(JSON.toJSONString(getSortNames(listObjectSec, mohesortList)));

//        System.out.println("=======针对魔盒中的sortItem 测试工具类--主要是定时排序规则======");
//
//        // 针对魔盒中的sortItem 测试工具类--主要是定时排序规则 的解析
//        String js = "{\"isMulti\":true,\"code\":\"sortItem\",\"necessary\":\"true\",\"defaultValue\":\"optimBi\",\"name\":\"排序规则\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"optimBi\",\"hint\":\"以商品过去1个月点击率数据表现排序，建议目标为UV点击率时使用\",\"name\":\"点击率目标导向\"},{\"code\":\"optimGMV\",\"hint\":\"以商品过去1个月UV价值(GMV/曝光UV)数据表现排序，建议目标为UV价值时使用\",\"name\":\"GMV目标导向\"},{\"code\":\"optimTransforRatio\",\"hint\":\"以商品过去1个月转化率数据表现排序，建议目标为转化率时使用\",\"name\":\"转化率目标导向\"},{\"code\":\"weightInfo\",\"extralRule\":{\"type\":\"MetricLabels\",\"config\":[{\"code\":\"realIndex\",\"name\":\"实时指标\",\"metricList\":[{\"code\":\"rPriceScope\",\"name\":\"价格力\",\"type\":\"Input\"},{\"code\":\"rGmv\",\"name\":\"成交GMV\",\"type\":\"Input\"},{\"code\":\"rOrdnum\",\"name\":\"成交订单量\",\"type\":\"Input\"},{\"code\":\"rAdvPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"type\":\"Input\"}]},{\"code\":\"weightInfo2\",\"name\":\"离线指标\",\"metricList\":[{\"code\":\"skuPlusPreferIndex\",\"hint\":\"PLUS用户偏好指数是基于商品在PLUS用户下销售表现、流量表现、广告表现预估商品的PLUS偏好指数，指数越高，PLUS用户偏好程度越高\",\"name\":\"PLUS偏好指数\",\"type\":\"Input\"},{\"code\":\"skuDimGmvScore\",\"hint\":\"根据商品成交表现数据计算销售力得分，数值越大越好\",\"name\":\"销售力\",\"type\":\"Input\"},{\"code\":\"skuDimAdScore\",\"hint\":\"根据广告投入等数据计算营销力得分，数值越大越好。\",\"name\":\"营销力\",\"type\":\"Input\"},{\"code\":\"skuDimBrandScore\",\"hint\":\"根据品牌销售和广告等数据计算品牌力得分，数值越大越好\",\"name\":\"品牌力\",\"type\":\"Input\"},{\"code\":\"skuDimUserScore\",\"hint\":\"根据用户数据计算用户力得分，数值越大越好\",\"name\":\"用户力\",\"type\":\"Input\"},{\"code\":\"praiseCount\",\"hint\":\"\",\"name\":\"评论数\",\"type\":\"Input\"},{\"code\":\"clickUv\",\"hint\":\"商品对店铺引流uv数。\",\"name\":\"对店铺引流\",\"width\":\"100%\",\"dropList\":[{\"code\":\"clickUv\",\"name\":\"近1天\"},{\"code\":\"15ClickUv\",\"name\":\"近15天\"},{\"code\":\"30ClickUv\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"advPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"width\":\"100%\",\"dropList\":[{\"code\":\"advPrice\",\"name\":\"近1天\"},{\"code\":\"15AdvPrice\",\"name\":\"近15天\"},{\"code\":\"30AdvPrice\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"exactPromiseRate\",\"hint\":\"商品物流时效按承诺时效妥投比率，数值越高越好。\",\"name\":\"物流时效履约率\",\"type\":\"Input\"},{\"code\":\"depositRate\",\"name\":\"定金支付率\",\"type\":\"Input\"},{\"code\":\"presaleOrderCount\",\"name\":\"预售订单量\",\"type\":\"Input\"},{\"code\":\"skuUserNewCate3InnerD180\",\"hint\":\"取近N天站内有成交订单的品类首购用户数累加\",\"name\":\"品类新用户数(站内)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3InnerD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3InnerD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3OuterD180\",\"hint\":\"取近N天站外有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(站外)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3OuterD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3OuterD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3AllD180\",\"hint\":\"取近N天全站有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(全站)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3AllD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3AllD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"tbd1\",\"hint\":\"活动爆品指数是通过算法模型学习商品供给侧表现、需求侧表现、流量侧用户与商品的交互行为、营销活动与商品的匹配行为共4大维度来预测商品在活动中的销售指数，并按照三级部门维度进行排名。排名及排名率越小，商品越靠前。\",\"width\":\"100%\",\"name\":\"爆品指数\",\"dropList\":[{\"code\":\"calSkuPreGmvOrderFirstDept\",\"name\":\"一级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateFirstDept\",\"name\":\"一级部门排名率\"},{\"code\":\"calSkuPreGmvOrderSecondDept\",\"name\":\"二级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateSecondDept\",\"name\":\"二级部门排名率\"},{\"code\":\"calSkuPreGmvOrder\",\"name\":\"三级部门排名\"},{\"code\":\"calSkuPreGmvOrderRate\",\"name\":\"三级部门排名率\"}],\"type\":\"SelectAndInput\"},{\"code\":\"GMV\",\"width\":\"100%\",\"name\":\"成交金额\",\"dropList\":[{\"code\":\"GMV\",\"name\":\"近1天\"},{\"code\":\"skuD7DealAmt\",\"name\":\"近7天\"},{\"code\":\"skuGmv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"addCartRate\",\"width\":\"100%\",\"name\":\"加购率\",\"dropList\":[{\"code\":\"addCartRate\",\"name\":\"近1天\"},{\"code\":\"skuAddCardUvRatioD30\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"UV\",\"width\":\"100%\",\"name\":\"UV\",\"dropList\":[{\"code\":\"UV\",\"name\":\"近1天\"},{\"code\":\"skuUv7d\",\"name\":\"近7天\"},{\"code\":\"skuUv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"praiseRate\",\"width\":\"100%\",\"name\":\"好评率\",\"dropList\":[{\"code\":\"praiseRate\",\"name\":\"近1天\"},{\"code\":\"skuD15GoodCommentRate\",\"name\":\"近15天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"orderCount\",\"width\":\"100%\",\"name\":\"订单量\",\"dropList\":[{\"code\":\"orderCount\",\"name\":\"近1天\"},{\"code\":\"skuD90DealSaleQtty\",\"name\":\"近90天\"},{\"code\":\"skuD180DealSaleQtty\",\"name\":\"近180天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"transforRatio\",\"width\":\"100%\",\"name\":\"转化率\",\"dropList\":[{\"code\":\"transforRatio\",\"name\":\"近1天\"},{\"code\":\"skuUvValue30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"uvHb\",\"width\":\"100%\",\"name\":\"UV环比\",\"dropList\":[{\"code\":\"skuUvD7Hb\",\"name\":\"近7天\"},{\"code\":\"skuUvD30Hb\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"l02Score31413\",\"hint\":\"微信域竞争力指数\",\"name\":\"微信域竞争力指数\",\"type\":\"Input\"},{\"code\":\"innerItemUv\",\"width\":\"100%\",\"name\":\"微信域近N天商详UV\",\"dropList\":[{\"code\":\"l2InnerItemUv7d31903\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUv15d31909\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemUv30d31908\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemUvRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详UV排名率\",\"dropList\":[{\"code\":\"l2InnerItemUvRank7d31919\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUvRank15d31933\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemUvRank30d31934\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天商详UV渗透率\",\"dropList\":[{\"code\":\"l2InnerItemPermeateRate15d31927\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUv15d31909\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemPermeateRate30d31928\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详UV渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerItemPermeateRateRank7d31923\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemPermeateRateRank15d31941\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemPermeateRateRank30d31942\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemConverRate\",\"width\":\"100%\",\"name\":\"微信域近N天商详转化率\",\"dropList\":[{\"code\":\"l2InnerItemConverRate7d31904\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemConverRate15d31910\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemConverRate30d31911\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemConverRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详转化率排名率\",\"dropList\":[{\"code\":\"l2InnerItemConverRateRank7d31920\",\"name\":\"近1天\"},{\"code\":\"l2InnerItemConverRateRank15d31935\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemConverRateRank30d31936\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerParentOrdQtty\",\"width\":\"100%\",\"name\":\"微信域近N天成交父单量\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRate7d31917\",\"name\":\"近7天\"},{\"code\":\"l2InnerParentOrdQtty15d31912\",\"name\":\"近15天\"},{\"code\":\"l2InnerParentOrdQtty30d31913\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerParentOrdQttyRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交父单量排名率\",\"dropList\":[{\"code\":\"l2InnerParentOrdQttyRank7d31921\",\"name\":\"近7天\"},{\"code\":\"l2InnerParentOrdQttyRank15d31937\",\"name\":\"近15天\"},{\"code\":\"l2InnerParentOrdQttyRank30d31938\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerOrdsPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天成交父单量渗透率\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRate7d31917\",\"name\":\"近7天\"},{\"code\":\"l2InnerOrdsPermeateRate15d31929\",\"name\":\"近15天\"},{\"code\":\"l2InnerOrdsPermeateRate30d31930\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerOrdsPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交父单量渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRateRank7d31924\",\"name\":\"近7天\"},{\"code\":\"l2InnerOrdsPermeateRateRank15d31943\",\"name\":\"近15天\"},{\"code\":\"l2InnerOrdsPermeateRateRank30d31944\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealAmt\",\"width\":\"100%\",\"name\":\"微信域近N天成交金额\",\"dropList\":[{\"code\":\"l2InnerDealAmt7d31906\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealAmt15d31914\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealAmt30d31915\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealAmtRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交金额排名率\",\"dropList\":[{\"code\":\"l2InnerDealAmtRank7d31922\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealAmtRank15d31939\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealAmtRank30d31940\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天成交金额渗透率\",\"dropList\":[{\"code\":\"l2InnerDealPermeateRate7d31918\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealPermeateRate15d31931\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealPermeateRate30d31932\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"InnerDealPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交金额渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerDealPermeateRateRank7d31925\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealPermeateRateRank15d31945\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealPermeateRateRank30d31946\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"}]}]},\"hint\":\"可在0.0-1.0区间范围内，自定义分配各指标的排序权重，权重总和需等于1\",\"name\":\"自定义排序指标\"},{\"code\":\"wxFactorScore\",\"hint\":\"综合评估商品微信域的转化效率、销量&amp;流量、微信域渗透率、价格力度等，判断商品在微信域的竞争力分值；分值越高，竞争力越强\",\"name\":\"微信竞争力指数\"}]}";
//        Map<String, Object> sortItemMap = JSONArray.parseObject(js, Map.class);
//        System.out.println(JSON.toJSONString(parseSortItem(sortItemMap)));

        System.out.println("===========not in 魔盒++++++++++");
        // 针对 不在 魔盒中的sortItem 测试工具类--主要是bi选品排序
//        String notInmoHeJson = "[{\"ruleName\":\"排序规则\",\"ruleContent\":[{\"code\":\"calculateItem\",\"data\":[{\"text\":\"24\"}]},{\"code\":\"biUnlimit\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"sortItem\",\"data\":[{\"text\":\"wxFactorScore\"}]}]}]\n";

        String notInmoHeJson="[{\"ruleContent\":[{\"code\":\"calculateItem\",\"data\":[{\"text\":\"24\"}]},{\"code\":\"biUnlimit\",\"data\":[{\"text\":\"0\"}]},{\"code\":\"sortItem\",\"data\":[{\"text\":\"selectGmv\"}]},{\"code\":\"algType\",\"data\":[{\"text\":\"1\"}]}],\"ruleName\":\"排序规则\"}]";
        List<Map<String, Object>> notInMohe = JSONArray.parseObject(notInmoHeJson, List.class);
        System.out.println(JSON.toJSONString(getSortNamesCodeNotInMohe(notInMohe)));
        System.out.println(JSON.toJSONString(getSortNamesCodeNotInMohe2(notInMohe)));

//        System.out.println("===========针对147魔盒中的sortItem 测试工具类--主要是定时排序规则++++++++++");
//        //  针对147魔盒中的sortItem 测试工具类--主要是定时排序规则 的解析
//String js="{\"isMulti\":true,\"code\":\"sortItem\",\"necessary\":\"true\",\"defaultValue\":\"optimBi\",\"name\":\"排序规则\",\"type\":\"RadioGroup\",\"config\":[{\"code\":\"optimBi\",\"hint\":\"以商品过去1个月点击率数据表现排序，建议目标为UV点击率时使用\",\"name\":\"点击率目标导向\"},{\"code\":\"optimGMV\",\"hint\":\"以商品过去1个月UV价值(GMV/曝光UV)数据表现排序，建议目标为UV价值时使用\",\"name\":\"GMV目标导向\"},{\"code\":\"optimTransforRatio\",\"hint\":\"以商品过去1个月转化率数据表现排序，建议目标为转化率时使用\",\"name\":\"转化率目标导向\"},{\"code\":\"weightInfo\",\"extralRule\":{\"type\":\"MetricLabels\",\"config\":[{\"code\":\"realIndex\",\"name\":\"实时指标\",\"metricList\":[{\"code\":\"rPriceScope\",\"name\":\"价格力\",\"type\":\"Input\"},{\"code\":\"rGmv\",\"name\":\"成交GMV\",\"type\":\"Input\"},{\"code\":\"rOrdnum\",\"name\":\"成交订单量\",\"type\":\"Input\"},{\"code\":\"rAdvPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"type\":\"Input\"}]},{\"code\":\"weightInfo2\",\"name\":\"离线指标\",\"metricList\":[{\"code\":\"skuPlusPreferIndex\",\"hint\":\"PLUS用户偏好指数是基于商品在PLUS用户下销售表现、流量表现、广告表现预估商品的PLUS偏好指数，指数越高，PLUS用户偏好程度越高\",\"name\":\"PLUS偏好指数\",\"type\":\"Input\"},{\"code\":\"skuDimGmvScore\",\"hint\":\"根据商品成交表现数据计算销售力得分，数值越大越好\",\"name\":\"销售力\",\"type\":\"Input\"},{\"code\":\"skuDimAdScore\",\"hint\":\"根据广告投入等数据计算营销力得分，数值越大越好。\",\"name\":\"营销力\",\"type\":\"Input\"},{\"code\":\"skuDimBrandScore\",\"hint\":\"根据品牌销售和广告等数据计算品牌力得分，数值越大越好\",\"name\":\"品牌力\",\"type\":\"Input\"},{\"code\":\"skuDimUserScore\",\"hint\":\"根据用户数据计算用户力得分，数值越大越好\",\"name\":\"用户力\",\"type\":\"Input\"},{\"code\":\"praiseCount\",\"hint\":\"\",\"name\":\"评论数\",\"type\":\"Input\"},{\"code\":\"clickUv\",\"hint\":\"商品对店铺引流uv数。\",\"name\":\"对店铺引流\",\"width\":\"100%\",\"dropList\":[{\"code\":\"clickUv\",\"name\":\"近1天\"},{\"code\":\"15ClickUv\",\"name\":\"近15天\"},{\"code\":\"30ClickUv\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"advPrice\",\"hint\":\"商品广告消耗金额\",\"name\":\"广告消耗\",\"width\":\"100%\",\"dropList\":[{\"code\":\"advPrice\",\"name\":\"近1天\"},{\"code\":\"15AdvPrice\",\"name\":\"近15天\"},{\"code\":\"30AdvPrice\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"exactPromiseRate\",\"hint\":\"商品物流时效按承诺时效妥投比率，数值越高越好。\",\"name\":\"物流时效履约率\",\"type\":\"Input\"},{\"code\":\"depositRate\",\"name\":\"定金支付率\",\"type\":\"Input\"},{\"code\":\"presaleOrderCount\",\"name\":\"预售订单量\",\"type\":\"Input\"},{\"code\":\"skuUserNewCate3InnerD180\",\"hint\":\"取近N天站内有成交订单的品类首购用户数累加\",\"name\":\"品类新用户数(站内)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3InnerD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3InnerD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3OuterD180\",\"hint\":\"取近N天站外有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(站外)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3OuterD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3OuterD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"skuUserNewCate3AllD180\",\"hint\":\"取近N天全站有成交订单的品类首购用户数累加。\",\"name\":\"品类新用户数(全站)\",\"width\":\"100%\",\"dropList\":[{\"code\":\"skuUserNewCate3AllD180\",\"name\":\"近180天\"},{\"code\":\"skuUserNewCate3AllD365\",\"name\":\"近365天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"tbd1\",\"hint\":\"活动爆品指数是通过算法模型学习商品供给侧表现、需求侧表现、流量侧用户与商品的交互行为、营销活动与商品的匹配行为共4大维度来预测商品在活动中的销售指数，并按照三级部门维度进行排名。排名及排名率越小，商品越靠前。\",\"width\":\"100%\",\"name\":\"爆品指数\",\"dropList\":[{\"code\":\"calSkuPreGmvOrderFirstDept\",\"name\":\"一级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateFirstDept\",\"name\":\"一级部门排名率\"},{\"code\":\"calSkuPreGmvOrderSecondDept\",\"name\":\"二级部门排名\"},{\"code\":\"calSkuPreGmvOrderRateSecondDept\",\"name\":\"二级部门排名率\"},{\"code\":\"calSkuPreGmvOrder\",\"name\":\"三级部门排名\"},{\"code\":\"calSkuPreGmvOrderRate\",\"name\":\"三级部门排名率\"}],\"type\":\"SelectAndInput\"},{\"code\":\"GMV\",\"width\":\"100%\",\"name\":\"成交金额\",\"dropList\":[{\"code\":\"GMV\",\"name\":\"近1天\"},{\"code\":\"skuD7DealAmt\",\"name\":\"近7天\"},{\"code\":\"skuGmv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"addCartRate\",\"width\":\"100%\",\"name\":\"加购率\",\"dropList\":[{\"code\":\"addCartRate\",\"name\":\"近1天\"},{\"code\":\"skuAddCardUvRatioD30\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"UV\",\"width\":\"100%\",\"name\":\"UV\",\"dropList\":[{\"code\":\"UV\",\"name\":\"近1天\"},{\"code\":\"skuUv7d\",\"name\":\"近7天\"},{\"code\":\"skuUv30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"praiseRate\",\"width\":\"100%\",\"name\":\"好评率\",\"dropList\":[{\"code\":\"praiseRate\",\"name\":\"近1天\"},{\"code\":\"skuD15GoodCommentRate\",\"name\":\"近15天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"orderCount\",\"width\":\"100%\",\"name\":\"订单量\",\"dropList\":[{\"code\":\"orderCount\",\"name\":\"近1天\"},{\"code\":\"skuD90DealSaleQtty\",\"name\":\"近90天\"},{\"code\":\"skuD180DealSaleQtty\",\"name\":\"近180天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"transforRatio\",\"width\":\"100%\",\"name\":\"转化率\",\"dropList\":[{\"code\":\"transforRatio\",\"name\":\"近1天\"},{\"code\":\"skuUvValue30d\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"uvHb\",\"width\":\"100%\",\"name\":\"UV环比\",\"dropList\":[{\"code\":\"skuUvD7Hb\",\"name\":\"近7天\"},{\"code\":\"skuUvD30Hb\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"l02Score31413\",\"hint\":\"微信域竞争力指数\",\"name\":\"微信域竞争力指数\",\"type\":\"Input\"},{\"code\":\"innerItemUv\",\"width\":\"100%\",\"name\":\"微信域近N天商详UV\",\"dropList\":[{\"code\":\"l2InnerItemUv7d31903\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUv15d31909\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemUv30d31908\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemUvRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详UV排名率\",\"dropList\":[{\"code\":\"l2InnerItemUvRank7d31919\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUvRank15d31933\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemUvRank30d31934\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天商详UV渗透率\",\"dropList\":[{\"code\":\"l2InnerItemPermeateRate15d31927\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemUv15d31909\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemPermeateRate30d31928\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详UV渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerItemPermeateRateRank7d31923\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemPermeateRateRank15d31941\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemPermeateRateRank30d31942\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemConverRate\",\"width\":\"100%\",\"name\":\"微信域近N天商详转化率\",\"dropList\":[{\"code\":\"l2InnerItemConverRate7d31904\",\"name\":\"近7天\"},{\"code\":\"l2InnerItemConverRate15d31910\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemConverRate30d31911\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerItemConverRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天商详转化率排名率\",\"dropList\":[{\"code\":\"l2InnerItemConverRateRank7d31920\",\"name\":\"近1天\"},{\"code\":\"l2InnerItemConverRateRank15d31935\",\"name\":\"近15天\"},{\"code\":\"l2InnerItemConverRateRank30d31936\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerParentOrdQtty\",\"width\":\"100%\",\"name\":\"微信域近N天成交父单量\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRate7d31917\",\"name\":\"近7天\"},{\"code\":\"l2InnerParentOrdQtty15d31912\",\"name\":\"近15天\"},{\"code\":\"l2InnerParentOrdQtty30d31913\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerParentOrdQttyRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交父单量排名率\",\"dropList\":[{\"code\":\"l2InnerParentOrdQttyRank7d31921\",\"name\":\"近7天\"},{\"code\":\"l2InnerParentOrdQttyRank15d31937\",\"name\":\"近15天\"},{\"code\":\"l2InnerParentOrdQttyRank30d31938\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerOrdsPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天成交父单量渗透率\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRate7d31917\",\"name\":\"近7天\"},{\"code\":\"l2InnerOrdsPermeateRate15d31929\",\"name\":\"近15天\"},{\"code\":\"l2InnerOrdsPermeateRate30d31930\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerOrdsPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交父单量渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerOrdsPermeateRateRank7d31924\",\"name\":\"近7天\"},{\"code\":\"l2InnerOrdsPermeateRateRank15d31943\",\"name\":\"近15天\"},{\"code\":\"l2InnerOrdsPermeateRateRank30d31944\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealAmt\",\"width\":\"100%\",\"name\":\"微信域近N天成交金额\",\"dropList\":[{\"code\":\"l2InnerDealAmt7d31906\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealAmt15d31914\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealAmt30d31915\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealAmtRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交金额排名率\",\"dropList\":[{\"code\":\"l2InnerDealAmtRank7d31922\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealAmtRank15d31939\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealAmtRank30d31940\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"innerDealPermeateRate\",\"width\":\"100%\",\"name\":\"微信域近N天成交金额渗透率\",\"dropList\":[{\"code\":\"l2InnerDealPermeateRate7d31918\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealPermeateRate15d31931\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealPermeateRate30d31932\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"},{\"code\":\"InnerDealPermeateRateRank\",\"width\":\"100%\",\"name\":\"三级类目微信域近N天成交金额渗透率排名率\",\"dropList\":[{\"code\":\"l2InnerDealPermeateRateRank7d31925\",\"name\":\"近7天\"},{\"code\":\"l2InnerDealPermeateRateRank15d31945\",\"name\":\"近15天\"},{\"code\":\"l2InnerDealPermeateRateRank30d31946\",\"name\":\"近30天\"}],\"type\":\"SelectAndInput\"}]}]},\"hint\":\"可在0.0-1.0区间范围内，自定义分配各指标的排序权重，权重总和需等于1\",\"name\":\"自定义排序指标\"},{\"code\":\"wxFactorScore\",\"hint\":\"综合评估商品微信域的转化效率、销量&流量、微信域渗透率、价格力度等，判断商品在微信域的竞争力分值；分值越高，竞争力越强\",\"name\":\"微信竞争力指数\"},{\"code\":\"newSelectionWeightInfo\",\"extralRule\":{\"type\":\"MetricLabels\",\"config\":[{\"code\":\"weightInfo2\",\"name\":\"指标\",\"metricList\":[{\"code\":\"l2ItemDetailFreeUv31961\",\"name\":\"近7天新品日均商详免费UV\",\"type\":\"Input\"},{\"code\":\"skuD7DealSaleNumDayAvg\",\"name\":\"近7天商品日均成交数量\",\"type\":\"Input\"},{\"code\":\"skuD7DealAmt\",\"name\":\"SKU近7天成交金额\",\"type\":\"Input\"},{\"code\":\"l2ConsumeAmt30d31874\",\"name\":\"新品近30天广告累计消耗金额\",\"type\":\"Input\"},{\"code\":\"l2ItemSearchUvCtr31963\",\"name\":\"近7天新品搜索UV点击率\",\"type\":\"Input\"},{\"code\":\"l2ItemRecommendUvCtr31964\",\"name\":\"近7天新品推荐UV点击率\",\"type\":\"Input\"},{\"code\":\"cate3SearchConversionRankRate7d\",\"name\":\"近7日搜索转化排名率\",\"type\":\"Input\"},{\"code\":\"sxTrans7d\",\"name\":\"7天商详转化排名率\",\"type\":\"Input\"}]}]},\"hint\":\"综合评估商品的成交、广告数据，以及在频道和搜推长的表现等，进而对商品进行排序，支持配置指标占比，适用于新品场域。\",\"name\":\"新品排序指标\"}]}\n";
//        Map<String, Object> sortItemMap = JSONArray.parseObject(js, Map.class);
//        System.out.println(JSON.toJSONString(parseSortItem(sortItemMap)));
    }


    /**
     * @param frontSortFields
     * @param moheSortFields
     * @return
     */
    private static List<String> getSortNames(List<Map<String, Object>> frontSortFields, List<Map<String, Object>> moheSortFields) {
        if (CollectionUtils.isEmpty(moheSortFields)) {
            return getSortNamesCodeNotInMohe(frontSortFields);
        } else {
            return getSortNamesCodeInMohe(frontSortFields, moheSortFields);
        }
    }


    /**
     * 排序规则的处理
     *
     * @param frontSortFields 前端入参中的排序规则
     * @param moheSortFields  魔盒上面配置的排序规则
     * @return
     */
    private static List<String> getSortNamesCodeInMohe(List<Map<String, Object>> frontSortFields, List<Map<String, Object>> moheSortFields) {
        List<String> sortNames = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(frontSortFields)) {
            Map<String, Object> sortField = frontSortFields.get(0);
            // 前端的排序规则内容
            List<Map<String, Object>> ruleContents = (List<Map<String, Object>>) sortField.get("ruleContent");
            // 魔盒处理
            Map<String, List<Map<String, Object>>> moheSortedFieldMap = moheSortFields.stream().collect(Collectors.groupingBy(item -> EasyUtils.toString(item.get("code"))));
            System.out.println("moheSortedFieldMap: \r");
            System.out.println(moheSortedFieldMap);
            if (CollectionUtils.isNotEmpty(ruleContents)) {
//                StringBuilder sb = new StringBuilder();
                for (Map<String, Object> ruleContent : ruleContents) {
                    StringBuilder sb = new StringBuilder();
                    // 入参中code
                    String code = EasyUtils.toString(ruleContent.get("code"));
                    System.out.println("入参 code : " + code);
                    // 选择或输入的值
                    List<Map<String, Object>> data = (List<Map<String, Object>>) ruleContent.get("data");

                    // 根据页面上的code从魔盒中获取数据
                    List<Map<String, Object>> moheList = null;
                    if ("realIndex".equals(code)) {
                        moheList = moheSortedFieldMap.get("sortItem");
                    } else {
                        moheList = moheSortedFieldMap.get(code);
                    }
                    if (CollectionUtils.isEmpty(moheList)) {
                        continue;
                    }
                    // 魔盒数据处理
                    Map<String, Object> moheMap = moheList.get(0);
                    //Input
                    //TimeLabel
                    //RadioGroup
                    //CheckBoxGroup
                    String type = EasyUtils.toString(moheMap.get("type"));
                    String name = EasyUtils.toString(moheMap.get("name"));
                    System.out.println("mohemap name :" + name);

                    switch (type) {
                        case "Input":
                            // 拼接
                            sb.append(name).append(":");
                            for (Map<String, Object> dataMap : data) {
                                String text = EasyUtils.toString(dataMap.get("text"));
                                sb.append(text).append(";");

                            }
                            break;
                        case "TimeLabel":
                            // 拼接
                            sb.append(name).append(":");
                            for (Map<String, Object> dataMap : data) {
                                String text = EasyUtils.toString(dataMap.get("text"));
                                sb.append(text).append(";");

                            }
                            break;
                        case "RadioGroup":
                            // 魔盒数据
                            List<Map<String, Object>> dataList = (List<Map<String, Object>>) moheMap.get("config");
                            //前端传值处理
                            Map<String, List<Map<String, Object>>> dataFront = data.stream().collect(Collectors.groupingBy(item -> EasyUtils.toString(item.get("text"))));

                            for (Map<String, Object> dataMap : dataList) {
                                String dataCode = (String) dataMap.get("code");
                                String dataName = (String) dataMap.get("name");
                                if (dataFront.containsKey(dataCode)) {
                                    sb.append(name).append(":");
                                    sb.append(dataName).append(";");
                                }
                            }
                            //  实时指标（自定义排序中）
                            if ("realIndex".equals(code)) {


                                List<Map<String, Object>> moheSortItemList = moheSortedFieldMap.get("sortItem");
                                if (CollectionUtils.isEmpty(moheSortItemList)) {
                                    break;
                                }
                                Map<String, Object> moheSortMap = parseSortItem(moheSortItemList.get(0));
                                // 实时指标集合
                                List<Map<String, Object>> realIndexList = (List<Map<String, Object>>) moheSortMap.get("realIndex");
                                Map<String, List<Map<String, Object>>> weightInfo2ListMap = realIndexList.stream().collect(Collectors.groupingBy(item -> EasyUtils.toString(item.get("code"))));
                                StringBuilder sbu = new StringBuilder();

                                for (Map<String, Object> dataMap : data) {

                                    for (Map.Entry<String, Object> enrty : dataMap.entrySet()) {
                                        String key = enrty.getKey();
                                        if (enrty.getValue() == null) {
                                            continue;
                                        }
                                        String value = enrty.getValue().toString();
                                        double d = Double.parseDouble(value);
                                        if (weightInfo2ListMap.containsKey(key) && value != null) {
                                            List<Map<String, Object>> maps = weightInfo2ListMap.get(key);
                                            String name1 = (String) maps.get(0).get("name");
                                            sbu.append(name1).append(":").append(d).append(";");
                                        }

                                    }
                                }
                                sb.append(sbu);

                            }
                            // 自定义排序
                            if ("sortItem".equals(code)) {
                                List<Map<String, Object>> moheSortItemList = moheSortedFieldMap.get("sortItem");
                                if (CollectionUtils.isEmpty(moheSortItemList)) {
                                    break;
                                }

                                Map<String, Object> moheSortMap = parseSortItem(moheSortItemList.get(0));
                                // 离线指标集合（自定义排序中）
                                List<Map<String, Object>> weightInfo2List = (List<Map<String, Object>>) moheSortMap.get("weightInfo2");
                                Map<String, List<Map<String, Object>>> weightInfo2ListMap = weightInfo2List.stream().collect(Collectors.groupingBy(item -> EasyUtils.toString(item.get("code"))));

                                // 排序指标(不含自定义排序)：点击率目标导向、GMV目标导向、转化率目标导向、微信竞争力指数
                                List<Map<String, Object>> notWeightInfoList = (List<Map<String, Object>>) moheSortMap.get("notWeightInfo");
                                Map<String, List<Map<String, Object>>> notWeightInfoListMap = notWeightInfoList.stream().collect(Collectors.groupingBy(item -> EasyUtils.toString(item.get("code"))));

                                // 判断是是自定义排序还是其他排序（点击率目标导向、GMV目标导向、转化率目标导向、微信竞争力指数）
                                Boolean isCustomSort = false;
                                for (Map<String, Object> dataMap : data) {
                                    //weightInfo
                                    String text = EasyUtils.toString(dataMap.get("text"));
                                    if (StringUtils.isNotEmpty(text) && ("weightInfo".equals(text) || "newSelectionWeightInfo".equals(text))) {
                                        isCustomSort = true;
                                        break;
                                    }
                                }

                                // 如果是自定义排序
                                if (isCustomSort) {
                                    StringBuilder sbu = new StringBuilder();
                                    for (Map<String, Object> dataMap : data) {
                                        //weightInfo
                                        String text = EasyUtils.toString(dataMap.get("text"));
                                        if ("weightInfo".equals(text) || "newSelectionWeightInfo".equals(text)) {
                                            dataMap.remove("text");
                                        }
                                        for (Map.Entry<String, Object> enrty : dataMap.entrySet()) {
                                            String key = enrty.getKey();
                                            String value = enrty.getValue().toString();
                                            double d = Double.parseDouble(value);
                                            if (weightInfo2ListMap.containsKey(key)) {
                                                List<Map<String, Object>> maps = weightInfo2ListMap.get(key);
                                                String name1 = (String) maps.get(0).get("name");
                                                sbu.append(name1).append(":").append(d).append(";");
                                            }
                                            System.out.println(key);
                                            System.out.println(d);

                                        }


                                    }
                                    sb.append(sbu);
                                }

                            }
                            break;
                        case "CheckBoxGroup":
                            // 前端选择或输入的值
                            if ("dasanV2".equals(code)) {
                                List<Map<String, Object>> realDataList = (List<Map<String, Object>>) ruleContent.get("data");
                                // 从魔盒中获取数据
                                List<Map<String, Object>> dasanV2MoheDataList = moheSortedFieldMap.get(code);
                                Map<String, Object> dasanV2MoheMap = dasanV2MoheDataList.get(0);
                                // 获取魔盒中name
                                String dasanV2Name = (String) dasanV2MoheMap.get("name");
                                sb.append(name).append(":");

                                List<Map<String, Object>> configList = (List<Map<String, Object>>) dasanV2MoheMap.get("config");
                                List<String> moheNameValueList = new ArrayList<>();
                                StringBuilder sb1 = new StringBuilder();
                                for (Map<String, Object> realMap : realDataList) {
                                    //mainBrandCode
                                    String fieldName = (String) realMap.get("fieldName");
                                    //high
                                    String text = (String) realMap.get("text");
                                    for (Map<String, Object> configMap : configList) {
                                        //mainBrandCode
                                        String configCode = (String) configMap.get("code");
                                        // 品牌打散
                                        String configName = (String) configMap.get("name");
                                        if (fieldName.equals(configCode)) {
                                            moheNameValueList.add(configName);
                                            sb1.append(configName).append(":");
                                            sb1.append(map1.get(text)).append(";");
                                        }

                                    }
                                }
                                sb.append(String.join(",", moheNameValueList)).append(";");
                                sb.append(sb1);
                            }

                            break;
                    }


                    sortNames.add(sb.toString());

                }

            }
        }
        return sortNames;
    }

    /**
     * 针对bi选品排序规则的处理（排序规则没有在魔盒上配置，只能单独处理）
     *
     * @param frontSortFields 前端入参中的排序规则
     * @return
     */
    private static List<String> getSortNamesCodeNotInMohe(List<Map<String, Object>> frontSortFields) {
        List<String> sortNames = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(frontSortFields)) {
            Map<String, Object> sortField = frontSortFields.get(0);
            List<Map<String, Object>> ruleContents = (List<Map<String, Object>>) sortField.get("ruleContent");
            if (CollectionUtils.isNotEmpty(ruleContents)) {

                for (Map<String, Object> ruleContent : ruleContents) {
                    StringBuilder sb = new StringBuilder();
                    // 入参中code
                    String code = EasyUtils.toString(ruleContent.get("code"));
                    // 选择或输入的值
                    List<Map<String, Object>> data = (List<Map<String, Object>>) ruleContent.get("data");
                    //  不在魔盒上面的配置
                    for (Map<String, Object> map : data) {
                        String text = EasyUtils.toString(map.get("text"));
                        // 针对排序规则的处理
                        if ("sortItem".equals(code)) {
                            sb.append("排序规则").append(":");
                            // 拼接的key
                            SortFieldEnum txtEnum = SortFieldEnum.getEnumBycode(code + "_" + text);
                            sb.append(txtEnum != null ? txtEnum.getName() : "").append(";");
                        } else {
                            if (SortFieldEnum.getEnumBycode(code) != null) {
                                if ("biUnlimit".equals(code)) {
                                    if ("0".equals(text)) {
                                        sb.append("优选top数量").append(":").append("10w").append(";");
                                    }
                                } else {
                                    // 类似calculateItem  这种 直接名称：text的值
                                    SortFieldEnum sfenum = SortFieldEnum.getEnumBycode(code);
                                    if ("1".equals(sfenum.getType())) {
                                        sb.append(sfenum.getName()).append(":");
                                        sb.append(text).append(";");
                                    }

                                }
                            }
                        }
                    }

                    if (StringUtils.isNotEmpty(sb)) {
                        sortNames.add(sb.toString());
                    }
                }
            }
        }
        return sortNames;
    }


    private static List<String> getSortNamesCodeNotInMohe2(List<Map<String, Object>> frontSortFields) {
        List<String> sortNames = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(frontSortFields)) {
            Map<String, Object> sortField = frontSortFields.get(0);
            List<Map<String, Object>> ruleContents = (List<Map<String, Object>>) sortField.get("ruleContent");
            if (CollectionUtils.isNotEmpty(ruleContents)) {

                for (Map<String, Object> ruleContent : ruleContents) {
                    StringBuilder sb = new StringBuilder();
                    // 入参中code
                    String code = EasyUtils.toString(ruleContent.get("code"));
                    // 选择或输入的值
                    List<Map<String, Object>> data = (List<Map<String, Object>>) ruleContent.get("data");
                    //  不在魔盒上面的配置
                    for (Map<String, Object> map : data) {
                        String text = EasyUtils.toString(map.get("text"));
                        // 针对排序规则的处理
                        if ("sortItem".equals(code)) {
                            sb.append("排序规则").append(":");
                            // 拼接的key
                            SortFieldEnum txtEnum = SortFieldEnum.getEnumBycode(code + "_" + text);
                            sb.append(txtEnum != null ? txtEnum.getName() : "").append(";");
                        } else {
                            if (SortFieldEnum.getEnumBycode(code) != null) {
                                if ("biUnlimit".equals(code)) {
                                    if ("0".equals(text)) {
                                        sb.append("优选top数量").append(":").append("10w").append(";");
                                    }
                                } else {
                                    // 类似calculateItem  这种 直接名称：text的值
                                    SortFieldEnum sfenum = SortFieldEnum.getEnumBycode(code);
                                    if ("1".equals(sfenum.getType())) {
                                        sb.append(sfenum.getName()).append(":");
                                        sb.append(text).append(";");
                                    }

                                }
                            }
                        }
                    }

                    if (StringUtils.isNotEmpty(sb)) {
                        sortNames.add(sb.toString());
                    }
                }
            }
        }
        return sortNames;
    }


    public static Map<String, Object> parseSortItem(Map<String, Object> sortItemMap) {
        Map<String, Object> resultMap = new HashMap<>();
        // 解析排序规则
        List<Map<String, Object>> config = (List<Map<String, Object>>) sortItemMap.get("config");
        System.out.println("parseSortItem config :"+JSON.toJSONString(config));
        // 解析非weightInfo数据
        List<Map<String, Object>> notWeightInfoCodeList = config.stream().filter(e -> (!e.get("code").equals("weightInfo") && !e.get("code").equals("newSelectionWeightInfo"))).collect(Collectors.toList());
        resultMap.put("notWeightInfo", notWeightInfoCodeList);
        // 解析weightInfo newSelectionWeightInfo数据 (如果魔盒上定时排序中的排序增加了新的自定义排序，这里需要把新的自定义排序的code加上)
        List<Map<String, Object>> weightInfoCodeList = config.stream().filter(e -> e.get("code").toString().equals("weightInfo") || e.get("code").toString().equals("newSelectionWeightInfo")).collect(Collectors.toList());
        List<Map<String, Object>> realIndexList= new ArrayList<>();
        List<Map<String, Object>> weightInfo2AllList= new ArrayList<>();
        for (Map<String, Object> weightInfoMap : weightInfoCodeList) {
            Map<String, Object> extralRuleMap = (Map<String, Object>) weightInfoMap.get("extralRule");
            List<Map<String, Object>> configList = (List<Map<String, Object>>) extralRuleMap.get("config");
            for (Map<String, Object> map : configList) {
                String code = (String) map.get("code");
                if ("realIndex".equals(code)) {
                    List<Map<String, Object>> metricList = (List<Map<String, Object>>) map.get("metricList");
                    realIndexList.addAll(metricList);


                }
                if ("weightInfo2".equals(code)) {
                    List<Map<String, Object>> weightInfo2List = new ArrayList<>();
                    List<Map<String, Object>> weightInfo2MetricList = (List<Map<String, Object>>) map.get("metricList");
                    for (Map<String, Object> weightInfo2Map : weightInfo2MetricList) {
                        HashMap<String, Object> map2 = new HashMap<>();
                        String weightInfo2Code = (String) weightInfo2Map.get("code");
                        String weightInfo2Name = (String) weightInfo2Map.get("name");
                        map2.put("code", weightInfo2Code);
                        map2.put("name", weightInfo2Name);
                        weightInfo2List.add(map2);

                        // 内含有dropList的然后name进行拼接,同时使用dropMap中的code
                        List<Map<String, Object>> dropList = (List<Map<String, Object>>) weightInfo2Map.get("dropList");
                        if (CollectionUtils.isNotEmpty(dropList)) {
                            for (Map<String, Object> dropMap : dropList) {
                                HashMap<String, Object> map3 = new HashMap<>();
                                map3.put("code", dropMap.get("code"));
                                map3.put("name", weightInfo2Name + "-" + dropMap.get("name"));
                                weightInfo2List.add(map3);
                            }

                        }
                        weightInfo2AllList.addAll(weightInfo2List);
                    }

                }
            }
        }
        // code去重
        // 先反转  然后保留最后一条
        Collections.reverse(weightInfo2AllList);
        List<Map<String, Object>> collect = weightInfo2AllList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(e -> e.get("code").toString()))), ArrayList::new));

        resultMap.put("realIndex", realIndexList);
        resultMap.put("weightInfo2", collect);

        return resultMap;
    }
}



