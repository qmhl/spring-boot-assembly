package io.geekidea.springboot.assembly.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangqi
 * @Description: 关联分析
 * @date 2022/10/22 15:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelateAnalyse {

    /**
     * 关联分析展示的 体验要素 或者 店铺 或者 商品 或者品牌的名称的id
     */
    private String id;

    /**
     * 关联分析展示的 体验要素 或者 店铺 或者 商品 或者品牌的名称
     */
    private String name;
    /**
     * 指标值
     */
    private String value;

    /**
     * 指标值-同比
     */
    private String yoyValue;

    /**
     * 指标值-环比
     */
    private String momValue;

    /**
     * 体验要素占比
     */
    private String rate;

    /**
     * 体验要素BER
     */
    private String ber;

    /**
     * 该指标对应的五级问题集合
     */
    private List<String> fifthList;

    /**
     * 每个五级问题对应的数量，与上述五级问题集合一一对应
     */
    private List<String> values;

    /**
     * 每个五级问题对应的比例
     */
    private List<String> rates;

    /**
     * 每个五级问题对应的ber
     */
    private List<String> bers;

    /**
     * 每个五级问题对应的同比
     */
    private List<String> yoyValues;

    /**
     * 每个五级问题对应的环比
     */
    private List<String> momValues;
}
