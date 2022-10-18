package io.geekidea.springboot.assembly.demo.model;

/**
 * @author jinmengyong3
 * @Description 指标
 * @date 2021/8/27 16:31
 */
public enum IndexEnum {
    QCR("QCR", "qcr_qlty_hit_red", "qcr_qlty_hit_", "Decimal"),//QCR
    QCR_NUMERATOR_RED("QCR分子", "qcr_qlty_hit_numerator", "qcr_qlty_hit_", "Decimal"),//QCR分子
    QCR_DENOMINATOR_RED("QCR分母", "qcr_qlty_hit_denominator", "qcr_qlty_hit_", "Decimal"),//QCR分母
    AFTER_SALE("售后申请率", "after_sale_red", "after_sale_", "Decimal"),//售后申请率
    AFTER_SALE_NUMERATOR("售后申请率分子", "after_sale_numerator", "after_sale_", "Decimal"),//售后申请率分子
    AFTER_SALE_DENOMINATOR("售后申请率分母", "after_sale_denominator", "after_sale_", "Decimal"),//售后申请率分母
    NEGATIVE_COMMENT("商品差评率_自营", "negative_commet_red", "negative_commet_", "Decimal"),//商品差评率_自营
    NEGATIVE_COMMENT_NUMERATOR("商品差评率分子_自营", "negative_commet_numerator", "negative_commet_", "Decimal"),//商品差评率分子_自营
    NEGATIVE_COMMENT_DENOMINATOR("商品差评率分母_自营", "negative_commet_denominator", "negative_commet_", "Decimal");//商品差评率分母_自营

    private String label; // 指标中文名称
    private String value; // 英文列名
    private String valueKey; // 指标组，字段名公共前缀
    private String valueType; // 值类型

    IndexEnum(String label, String value, String valueKey, String valueType) {
        this.label = label;
        this.value = value;
        this.valueKey = valueKey;
        this.valueType = valueType;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public String getValueKey() {
        return valueKey;
    }

    public String getValueType() {
        return valueType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
