package io.geekidea.springboot.assembly.demo.entity;

import lombok.Builder;

import java.util.Date;

@Builder
public class SymbolSubmissionContentFailureRecord {
    /**
     * 主键
     */
    private Long id;

    /**
     * 实体id
     */
    private String entityId;

    /**
     * 标签id
     */
    private Long symbolId;

    /**
     * 操作类型：0-新增，1-修改，2-状态变更
     */
    private Integer opType;

    /**
     * 消息体
     */
    private String message;

    /**
     * 失败原因
     */
    private String failureReason;

    /**
     * 状态：0-失败，1-成功
     */
    private Integer status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更改人
     */
    private String modifier;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 主键
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 实体id
     * @return entity_id 实体id
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * 实体id
     * @param entityId 实体id
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }

    /**
     * 标签id
     * @return symbol_id 标签id
     */
    public Long getSymbolId() {
        return symbolId;
    }

    /**
     * 标签id
     * @param symbolId 标签id
     */
    public void setSymbolId(Long symbolId) {
        this.symbolId = symbolId;
    }

    /**
     * 操作类型：0-新增，1-修改，2-状态变更
     * @return op_type 操作类型：0-新增，1-修改，2-状态变更
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     * 操作类型：0-新增，1-修改，2-状态变更
     * @param opType 操作类型：0-新增，1-修改，2-状态变更
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     * 消息体
     * @return message 消息体
     */
    public String getMessage() {
        return message;
    }

    /**
     * 消息体
     * @param message 消息体
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * 失败原因
     * @return failure_reason 失败原因
     */
    public String getFailureReason() {
        return failureReason;
    }

    /**
     * 失败原因
     * @param failureReason 失败原因
     */
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason == null ? null : failureReason.trim();
    }

    /**
     * 状态：0-失败，1-成功
     * @return status 状态：0-失败，1-成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0-失败，1-成功
     * @param status 状态：0-失败，1-成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更改人
     * @return modifier 更改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 更改人
     * @param modifier 更改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 更新时间
     * @return modify_time 更新时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 更新时间
     * @param modifyTime 更新时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}