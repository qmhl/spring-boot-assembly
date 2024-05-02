package io.geekidea.springboot.assembly.demo.constant;

/**
 * @author chenfei159
 * @Description 查询周期类型
 * @date 2022/3/25 15:25
 */
public enum QueryCycleEnum {
    ONLY(0, "仅一次"),
    MONTH(1,"按月"),
    QUARTER(2,"按季度"),
    HALFYEAR(3,"按半年");


    private Integer key;
    private String value;

    QueryCycleEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(Integer key) {
        for (QueryCycleEnum typeEnum : QueryCycleEnum.values()) {
            if (typeEnum.getKey().toString().equals(key.toString())) {
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
