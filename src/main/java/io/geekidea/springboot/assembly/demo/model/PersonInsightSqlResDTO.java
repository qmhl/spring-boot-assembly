package io.geekidea.springboot.assembly.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangqi
 * @Description: 查询sql的结果类
 * @date 2022/7/26 15:35
 */
@Data
@AllArgsConstructor
public class PersonInsightSqlResDTO {
    /**
     * 人群类型：pre-搜索人群  post-成交人群  all-全部
     */
    private String type;

    /**
     * 维度key 如：性别中的男、女
     */
    private String dimKey;
    /**
     * 占比
     */
    private Double percent;
    /**
     * tgi(这是品类粒度的，关键词粒度的需要单算)
     */
    private Double tgi;


}
