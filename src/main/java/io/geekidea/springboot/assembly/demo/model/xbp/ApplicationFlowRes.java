package io.geekidea.springboot.assembly.demo.model.xbp;

import java.util.List;
import java.util.Map;

public class ApplicationFlowRes {
    private Integer id;
    private Integer stage;
    private Integer processId;
    private String username;
    private Integer status;
    private String statusText;
    private List<String> mailCopyAddresses;
    private String callbackRsp;
    private Integer apiId;
    private String token;
    private Map<String, String> userInfo;
    private List<ApplicationInfo> applicationInfo;
    private List<ApplicationFlowVO> applicationFlow;
    private Boolean isWfp;
    private Map<String, String> rtfContent;
    private String revokeReason;
    private String createTime;
    private String updateTime;
}