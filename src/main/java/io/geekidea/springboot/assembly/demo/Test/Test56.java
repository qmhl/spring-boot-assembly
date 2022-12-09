package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.RelateAnalyse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class Test56 {
    public static final String YOY_MOM="--";
    public static final String NAN="nan";
    public static void main(String[] args) {

        String json = "{\"id\":\"702695\",\"name\":\"隽悦（JUANYUE）\",\"value\":\"2\"}";
        String json2 = "[{\"id\":\"439739\",\"name\":\"骞煊\",\"value\":\"1\"},{\"id\":\"732086\",\"name\":\"新塑诚\",\"value\":\"1\"},{\"id\":\"793020\",\"name\":\"柔鲜\",\"value\":\"1\"},{\"id\":\"130259\",\"name\":\"诺美姿（NUOMEIZI）\",\"value\":\"1\"},{\"id\":\"57695\",\"name\":\"诺普声（Nobsound）\",\"value\":\"1\"},{\"id\":\"131158\",\"name\":\"松盛（SONGSUN）\",\"value\":\"1\"},{\"id\":\"8235\",\"name\":\"亨博（hengbo）\",\"value\":\"1\"},{\"id\":\"793661\",\"name\":\"安哲罗\",\"value\":\"1\"},{\"id\":\"811887\",\"name\":\"酷罗\",\"value\":\"1\"},{\"id\":\"624158\",\"name\":\"卸岭府\",\"value\":\"1\"},{\"id\":\"513757\",\"name\":\"博洁（BOONE JI）\",\"value\":\"1\"},{\"id\":\"353732\",\"name\":\"杉贝\",\"value\":\"1\"},{\"id\":\"678175\",\"name\":\"若幻\",\"value\":\"1\"},{\"id\":\"369420\",\"name\":\"爱拓思\",\"value\":\"1\"},{\"id\":\"210553\",\"name\":\"纳丽雅（Naliya）\",\"value\":\"1\"},{\"id\":\"3060\",\"name\":\"艾力斯特（Irest）\",\"value\":\"1\"},{\"id\":\"585962\",\"name\":\"韵果\",\"value\":\"1\"},{\"id\":\"90820\",\"name\":\"暖煌\",\"value\":\"1\"},{\"id\":\"484666\",\"name\":\"众夏\",\"value\":\"1\"},{\"id\":\"702695\",\"name\":\"隽悦（JUANYUE）\",\"value\":\"1\"},{\"id\":\"453327\",\"name\":\"obowAl\",\"value\":\"1\"},{\"id\":\"45692\",\"name\":\"茗振（MZ）\",\"value\":\"0.8\"},{\"id\":\"252282\",\"name\":\"金妙\",\"value\":\"0.75\"},{\"id\":\"803572\",\"name\":\"jakchr\",\"value\":\"0.75\"},{\"id\":\"47869\",\"name\":\"傲胜（OSIM）\",\"value\":\"0.6666666666666666\"},{\"id\":\"556129\",\"name\":\"WORLD KITCHEN\",\"value\":\"0.6\"},{\"id\":\"691975\",\"name\":\"艾德勒\",\"value\":\"0.5\"},{\"id\":\"92594\",\"name\":\"现代\",\"value\":\"0.5\"},{\"id\":\"166371\",\"name\":\"艾普莱斯（AIRPLUS）\",\"value\":\"0.5\"},{\"id\":\"510282\",\"name\":\"鑫富康\",\"value\":\"0.5\"},{\"id\":\"569762\",\"name\":\"航通（HANGTONG）\",\"value\":\"0.5\"},{\"id\":\"100582\",\"name\":\"诺克司（KNOCS）\",\"value\":\"0.5\"},{\"id\":\"480817\",\"name\":\"JHOTOO\",\"value\":\"0.3333333333333333\"},{\"id\":\"376589\",\"name\":\"Finetek\",\"value\":\"0.3125\"},{\"id\":\"801321\",\"name\":\"nutmrk\",\"value\":\"0.125\"},{\"id\":\"384333\",\"name\":\"XAXR\",\"value\":\"0.125\"}]";

        RelateAnalyse obj = JSON.parseObject(json, RelateAnalyse.class);
        List<RelateAnalyse> list = JSON.parseArray(json2, RelateAnalyse.class);
        System.out.println(calYoyMomForRelate(obj,list));
        System.out.println(formatYoYMoM(calYoyMomForRelate(obj,list)));
    }


    private static String calYoyMomForRelate(RelateAnalyse res, List<RelateAnalyse> resYoyList) {
        log.info("calYoyMomForRelate res :{}  resYoyList :{}", JSON.toJSONString(res), JSON.toJSONString(resYoyList));

        String yoyRes = YOY_MOM;
        if (!CollectionUtils.isEmpty(resYoyList)) {
            //id作为key
            Map<String, RelateAnalyse> collect = resYoyList.stream().collect(Collectors.toMap(RelateAnalyse::getId, Function.identity(), (k, v) -> k));
            if (collect.containsKey(res.getId())) {
                RelateAnalyse resYoy = collect.get(res.getId());
                if (resYoy != null) {
                    if (!YOY_MOM.equals(res.getValue()) && !YOY_MOM.equals(resYoy.getValue())
                            && !NAN.equals(resYoy.getValue()) && !NAN.equals(resYoy.getValue())) {
                        log.info("calYoyMomForRelate res :{}  resYoy :{}", JSON.toJSONString(res), JSON.toJSONString(resYoy));
                        yoyRes = calYoyMom(Double.valueOf(res.getValue()), Double.valueOf(resYoy.getValue()));

                    }
                }

            }
        }
        return yoyRes;
    }


    private static String calYoyMom(Double nowValue, Double momValue) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (nowValue == null || nowValue.isNaN()) {
            return YOY_MOM;
        }
        if (momValue == null || momValue.equals(0D) || momValue.isNaN()) {
            return YOY_MOM;
        }
        return df.format((nowValue - momValue) / momValue);
    }


    public static String formatYoYMoM(String value) {
        String str="";
        if (!YOY_MOM.equals(value) && !NAN.equals(value)) {
            DecimalFormat df = new DecimalFormat("0.00");
            str = df.format(Double.valueOf(value) * 100) + "%";
        }else{
            str = YOY_MOM;
        }
        return str;
    }

}



