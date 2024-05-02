package io.geekidea.springboot.assembly.demo.constant;

/**
 * @author jinmengyong3
 * @Description 时间粒度
 * @date 2021/8/27 16:31
 */
public enum TimeDimEnum {
    WEEK("周", 1),//周粒度
    MONTH("月", 2);//月粒度

    private String label;
    private Integer value;

    TimeDimEnum(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Integer getValue() {
        return value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
