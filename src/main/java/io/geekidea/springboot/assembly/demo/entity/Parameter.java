package io.geekidea.springboot.assembly.demo.entity;

import java.io.Serializable;

/**
 * 内容精选参数
 *
 * rediskey:
 * 首先解析sid出现的数字顺序，假设sid = 642,sid[1] =6(skuid), sid[2] =4( shopid), sid[3] =2( cid2)。
 *    micro_${projectId}_${sid}_${sid[1]}_${sid[2]}_{sid[...]}_${streamline}_${pageIdx}
 *
 * (  示例：projectId=goodPic, sid=642, sid[1] = skuid = 435567,
 *          sid[2] = shopid = 100003478, sid[3] = cid2 = 1389, streamline=1, pageIdx=3
 *    则：     key= micro_goodPic_642_435567_100003478_1389_1_3  )
 * @author : yfzhangbin
 * @date : 2020/8/11 14:05
 */
public class Parameter implements Serializable {

    // ------------------以下参数至少填写一个---------------------

    private String cid1; // 一级类目id，对应查询位编号 1

    private String cid2; // 二级类目id，对应查询位编号 2

    private String cid3; // 三级类目id，对应查询位编号 3

    private String shopid; // 店铺id，对应查询位编号 4

    private String spuid; // SpuId，对应查询位编号 5

    private String skuid; // SkuId，对应查询位编号 6

    private String pin; // 用户编号，对应查询位编号 7

    private String tag; // 标签，对应查询位编号 8

    // ------------------以上参数至少填写一个----------------------

    private String project_id; // 项目id，必须

    private Integer pageIdx; // 页码，必须

    private String uuid; // 唯一标识，必须

    private String pid; // 产品位，评价：50001，啊哈：50002

    /**
     * 必须
     * 若sid为12，包含2个字段，sid[1] =1(cid1)和sid[2] =2(cid2)，表示需要顺序拼,1和2 对应的字段cid1和cid2得到key
     * 若sid为638，包含3个字段，sid[1]=6(Skuid),sid[2]=3(cid3),和sid[3]=8(tag)，表示需要顺序拼接，6,3,8对应的字段Skuid，cid3，tag得到key
     *
     * 以精选晒图为例：
     * 6:skuid
     * 65:skuid聚合spuid下其他sku的素材
     */
    private Integer sid; // 查询位，必须，比如，1，对应使用cid1进行查询，2对应使用cid2进行查询，以此类推

    private Integer streamline; // 精简，必须。0 表示只返回精简内容，1表示返回详细内容。

    private String token; // 令牌，必须，用户鉴权token

    private String ext; // 扩展信息，非必须。

    private String abtest; // AB实验项目id

    public String getCid1() {
        return cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public String getCid2() {
        return cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    public String getCid3() {
        return cid3;
    }

    public void setCid3(String cid3) {
        this.cid3 = cid3;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getSpuid() {
        return spuid;
    }

    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public Integer getPageIdx() {
        return pageIdx;
    }

    public void setPageIdx(Integer pageIdx) {
        this.pageIdx = pageIdx;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getStreamline() {
        return streamline;
    }

    public void setStreamline(Integer streamline) {
        this.streamline = streamline;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getAbtest() {
        return abtest;
    }

    public void setAbtest(String abtest) {
        this.abtest = abtest;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "cid1='" + cid1 + '\'' +
                ", cid2='" + cid2 + '\'' +
                ", cid3='" + cid3 + '\'' +
                ", shopid='" + shopid + '\'' +
                ", spuid='" + spuid + '\'' +
                ", skuid='" + skuid + '\'' +
                ", pin='" + pin + '\'' +
                ", tag='" + tag + '\'' +
                ", project_id='" + project_id + '\'' +
                ", pageIdx=" + pageIdx +
                ", uuid='" + uuid + '\'' +
                ", pid='" + pid + '\'' +
                ", sid=" + sid +
                ", streamline=" + streamline +
                ", token='" + token + '\'' +
                ", ext='" + ext + '\'' +
                ", abtest='" + abtest + '\'' +
                '}';
    }
}
