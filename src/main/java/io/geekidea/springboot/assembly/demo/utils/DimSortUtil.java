package io.geekidea.springboot.assembly.demo.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangqi
 * @Description: ***
 * @date 2022/7/30 11:05
 */
public class DimSortUtil {

    /**
     * 维度排序
     */
    private static final Map<String, List<String>> DIM_MAP;

    private static final List<String> SEX_LIST = Arrays.asList("男", "女");
    private static final List<String> AGE_LIST = Arrays.asList("0~15岁", "16~20岁", "21~25岁", "26~30岁", "31~35岁", "36~40岁", "41~45岁", "46~50岁", "51~55岁", "56~60岁", "61~65岁", "66~70岁", "71岁及以上");
    private static final List<String> CITY_LIST = Arrays.asList("一线", "二线", "三线", "四线", "五线", "六线-1");
    private static final List<String> PROFESSION_LIST = Arrays.asList("金融从业者", "医务人员", "公司职员", "教职工", "工人", "学生", "农民", "个体或服务业", "城市其他职业", "农村其他职业");
    private static final List<String> PLUS_LIST = Arrays.asList("试用中", "未激活", "正式中", "试用过期", "正式过期");
    private static final List<String> PURCHASE_LIST = Arrays.asList("收入很少", "蓝领", "小白领", "高级白领", "土豪");
    private static final List<String> TEN_GROUP_LIST = Arrays.asList("学生一族", "小镇中产", "小镇中年", "小镇家庭", "小镇青年", "都市Z世代", "都市中产", "都市家庭", "都市蓝领", "银发一族");
    private static final List<String> LOVE_SEC_KILL_LIST = Arrays.asList("精确", "较精确", "较广泛", "广泛");
    private static final List<String> SENSE_LIST = Arrays.asList("不敏感", "轻度敏感", "中度敏感", "高度敏感", "极度敏感");


    static {
//             查询维度:
//      "sex", "age", "cityLevel", "profession", "plus", "purchaseLevel",
//      "tenGroup", "loveSecKillLevel", "senseComment", "sensePromotion"
        DIM_MAP = new HashMap<String, List<String>>();
        DIM_MAP.put("sex", SEX_LIST);
        DIM_MAP.put("age", AGE_LIST);
        DIM_MAP.put("cityLevel", CITY_LIST);
        DIM_MAP.put("profession", PROFESSION_LIST);
        DIM_MAP.put("plus", PLUS_LIST);
        DIM_MAP.put("purchaseLevel", PURCHASE_LIST);
        DIM_MAP.put("tenGroup", TEN_GROUP_LIST);
        DIM_MAP.put("loveSecKillLevel", LOVE_SEC_KILL_LIST);
        DIM_MAP.put("senseComment", SENSE_LIST);
        DIM_MAP.put("sensePromotion", SENSE_LIST);

    }


    public static Map<String, List<String>> getDimMap() {
        return DIM_MAP;
    }

}
