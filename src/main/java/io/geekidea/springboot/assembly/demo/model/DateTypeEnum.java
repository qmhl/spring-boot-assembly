package io.geekidea.springboot.assembly.demo.model;

/**
 * @author wangnan122
 * @Description
 * @date 2022/3/25 15:25
 */
public enum DateTypeEnum {
    CUSTOM(0, "自定义日期筛选"),   //包括近一周、近一月、近三月、近半年以及自定义选择的日期
    WEEK(1, "按周筛选"),    //按周筛选
    MONTH(2, "按月筛选");   //按月筛选

    private Integer key;
    private String value;

    DateTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(String key) {
        for (DateTypeEnum typeEnum : DateTypeEnum.values()) {
            if (typeEnum.getKey().equals(key) ) {
                return typeEnum.getValue();
            }
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
