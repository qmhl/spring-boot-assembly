package io.geekidea.springboot.assembly.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ReportTemplate {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 模版类型：1-品类洞察 2-店铺体验报告 3-部门体验报告 4-店铺排行榜报告 ……
     */
    private String templateType;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 描述
     */
    private String desc;

    /**
     * 使用场景
     */
    private String scene;

    /**
     * 订阅次数
     */
    private Long subCount;

    /**
     * 收件人组类型：shop-按店铺类型 dept-按部门类型
     */
    private String receiverGroupType;

    /**
     * 删除状态，0：未删除、1：已删除
     */
    private Integer isDeleted;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}