package io.geekidea.springboot.assembly.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店铺排行榜指标实体类
 *
 * @author zhangqi1092
 * @date 2022-09-14 09:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopScoreInfo {

    /**
     * 英文名称
     */
    private String name;
    /**
     * 当期值
     */
    private String score;
    /**
     * 同比的增长率
     */
    private String yoyRate;
    /**
     * 环比的增长率
     */
    private String momRate;

}

