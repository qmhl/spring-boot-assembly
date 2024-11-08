package io.geekidea.springboot.assembly.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 用户意图洞察查询条件
 *
 * @author: yubin
 * @create: 2021-08-18 10:18
 */
@Data
@ToString
public class IntentInsightQuery extends PermissionCheckParam {

    private List<Integer> cateIds;  // 类目id
    private Integer cateLevel;      // 类目层级
    private Integer cateId;  // 类目id(仅用于搜索query模糊查询)
    private Integer type;           // 词类型：0、全部、1品牌词、2基础词、3场景词、4营销词
    private Integer time;           // 时间粒度：1近一周、2近一月、3近一季度、4近半年、5近一年
    private String keyword;         // 查询词
    private List<String> keywords;  // 查询词
    private Integer character;      // 词特征：0普通、1热词、2暴增词
    private String erp;             // 当前用户
    private String dt;              // 时间分区
    private Integer type1;    // 词类型1：0、全部、1.产品词、2品牌词、3属性词、4场景词、5营销词
    private Integer type2;    // 词类型2：0、全部、1.产品词、2品牌名称、3品牌系列、4属性名称、5人群、6人群、7时间、8地点、9场景事件、10价格类、11买赠类、12权益类、13立意类
    private Integer type3;    // 词类型3：0、全部、1.产品词、2品牌名称、3品牌系列、4职业人群 、5.社会角色、6特征兴趣人群、7年龄阶段、8人体部位、9时间段、10节日、11季节/天气、12纪念日 、13地理地点、14生活地点、15生活事件、16职场办公事件、17特殊场景事件、18价格类、19买赠类、20权益类、21立意类
    private String type1Name;    // 词类型1：0、全部、1.产品词、2品牌词、3属性词、4场景词、5营销词
    private String type2Name;    // 词类型2：0、全部、1.产品词、2品牌名称、3品牌系列、4属性名称、5人群、6人群、7时间、8地点、9场景事件、10价格类、11买赠类、12权益类、13立意类
    private String type3Name;    // 词类型3：0、全部、1.产品词、2品牌名称、3品牌系列、4职业人群 、5.社会角色、6特征兴趣人群、7年龄阶段、8人体部位、9时间段、10节日、11季节/天气、12纪念日 、13地理地点、14生活地点、15生活事件、16职场办公事件、17特殊场景事件、18价格类、19买赠类、20权益类、21立意类
    private String order;           // 排序参数 heat desc/asc

    final List<Integer>  innerList = new LinkedList();
    public IntentInsightQuery() {
    }

    public void setCateIds(List<Integer> cateIds) {
        this.cateIds = cateIds;
    }

    public void setCateLevel(Integer cateLevel) {
        this.cateLevel = cateLevel;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void setCharacter(Integer character) {
        this.character = character;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public void setType1(Integer type1) {
        this.type1 = type1;
    }

    public void setType2(Integer type2) {
        this.type2 = type2;
    }

    public void setType3(Integer type3) {
        this.type3 = type3;
    }

    public void setType1Name(String type1Name) {
        this.type1Name = type1Name;
    }

    public void setType2Name(String type2Name) {
        this.type2Name = type2Name;
    }

    public void setType3Name(String type3Name) {
        this.type3Name = type3Name;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public IntentInsightQuery(List<Integer> cateIds, Integer cateLevel, Integer cateId, Integer type, Integer time, String keyword, List<String> keywords, Integer character, String erp, String dt, Integer type1, Integer type2, Integer type3, String type1Name, String type2Name, String type3Name, String order) {
        System.out.println("进入IntentInsightQuery 初始化");
        this.cateIds = cateIds;
        this.cateLevel = cateLevel;
        this.cateId = cateId;
        this.type = type;
        this.time = time;
        this.keyword = keyword;
        this.keywords = keywords;
        this.character = character;
        this.erp = erp;
        this.dt = dt;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type1Name = type1Name;
        this.type2Name = type2Name;
        this.type3Name = type3Name;
        this.order = order;

        if(cateLevel == 1){
            this.innerList.add(cateIds.get(0));
            this.innerList.add(null);
            this.innerList.add(null);
            super.setCateList(Collections.singletonList(this.innerList));
        }
        if(cateLevel == 2){
            this.innerList.add(null);
            this.innerList.add(cateIds.get(0));
            this.innerList.add(null);
            super.setCateList(Collections.singletonList(this.innerList));        }
        if(cateLevel == 3){
            this.innerList.add(null);
            this.innerList.add(null);
            this.innerList.add(cateIds.get(0));
            super.setCateList(Collections.singletonList(this.innerList));        }
    }
}
