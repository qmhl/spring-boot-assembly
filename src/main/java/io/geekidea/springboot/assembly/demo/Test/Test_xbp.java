//package io.geekidea.springboot.assembly.demo.Test;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.DigestUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Slf4j
//public class Test_xbp {
//    public static void main(String[] args) {
//
//        String sign="65cf86617f";
//        String apiUser="label_dispatch_submit";
//        Long time = System.currentTimeMillis();
//
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        headers.put("Xbp-Api-Timestamp", time.toString());
//        headers.put("Xbp-Api-Session",DigestUtils.md5DigestAsHex((time.toString() + sign).getBytes()));
//        headers.put("Xbp-Api-User", apiUser);
//
//        //1、创建xbp任务
//        // {"code":0,"msg":"SUCCESS","data":{"ticketId":1782467}}
//        String createRes = createXbp(headers);
//        log.info("XbpServiceImpl创建xbp流程结果：{}", createRes);
//
////        //2、审批
////        // 文档：http://xbp-help.jd.com/open-api/http-list#31-%E5%AE%A1%E6%89%B9
//        // 一审 {"code":0,"msg":"SUCCESS","data":true}
////        String approve1Res = approve1Xbp(headers);
////        log.info("XbpServiceImpl xbp 一审流程结果：{}", approve1Res);
////
//        // 二审 【二审完之后会回调/labelXbp/recall接口】
////        String approve2Res = approve2Xbp(headers);
////        log.info("XbpServiceImpl xbp 二审流程结果：{}", approve2Res);
//
//
//    }
//
//    private static String createXbp(Map<String, String> headers) {
//        String requestBody = "{\n" +
//                "    \"applicationInfo\": {\n" +
//                "        \"标签有效期\": \"2099-09-09 00:00:00\",\n" +
//                "        \"标签中文名称\": \"zq_test_规则标签\",\n" +
//                "        \"标签英文名称\": \"el_1_5370\",\n" +
//                "        \"标签介绍\": \"过去一年618和双11购买的金额占全年购买金额的比例在前30%的用户，他们热衷于在大促期间囤货，是大促营销的重点人群。\",\n" +
//                "        \"标签id\": \"5370\",\n" +
//                "        \"提交人erp\": \"zhangqi1099\",\n" +
//                "        \"标签主题分类\": \"用户偏好-活动偏好-促销偏好\"\n" +
//                "    },\n" +
//                "    \"approvers\": {\n" +
//                "        \"一级审批\": [\n" +
//                "            \"zhangqi1099\",\n" +
//                "            \"xiafuzhao1\"\n" +
//                "        ],\n" +
//                "        \"二级审批\": [\n" +
//                "            \"zhangqi1099\"\n" +
//                "        ]\n" +
//                "    },\n" +
//                "    \"processId\": 7735,\n" +
//                "    \"sign\": \"65cf86617f\",\n" +
//                "    \"username\": \"zhangqi1099\"\n" +
//                "}";
//        String createXbpUrl = "http://xbp-api-pre.jd.com/api/api/ticket/create";
//        String result = HttpClientUtils.postWithHeader(createXbpUrl, requestBody, headers);
//        return result;
//    }
//
//
//    private static String approve1Xbp(Map<String, String> headers) {
//        // http://xbp-help.jd.com/open-api/http-list#31-%E5%AE%A1%E6%89%B9
//        String approveXbpUrl="http://xbp-api-pre.jd.com/api/api/ticket/operate";
//        // 一审
//        String approveReqBody="{\n" +
//                "    \"ticketId\": 1782473,\n" +
//                "    \"stage\": 0,\n" +
//                "    \"status\": -1,\n" +
//                "    \"username\": \"zhangqi1099\",\n" +
//                "    \"opinion\": \"\"\n" +
//                "}";
//        String result = HttpClientUtils.postWithHeader(approveXbpUrl, approveReqBody, headers);
//        return result;
//    }
//
//    private static String approve2Xbp(Map<String, String> headers) {
//        // http://xbp-help.jd.com/open-api/http-list#31-%E5%AE%A1%E6%89%B9
//        String approveXbpUrl="http://xbp-api-pre.jd.com/api/api/ticket/operate";
//        // 二审
//        String approveReqBody="{\n" +
//                "    \"ticketId\": 1782467,\n" +
//                "    \"stage\": 1,\n" +
//                "    \"status\": 1,\n" +
//                "    \"username\": \"zhangqi1099\",\n" +
//                "    \"opinion\": \"\"\n" +
//                "}";
//        String result = HttpClientUtils.postWithHeader(approveXbpUrl, approveReqBody, headers);
//        return result;
//    }
//
//
//
//
//
//}
