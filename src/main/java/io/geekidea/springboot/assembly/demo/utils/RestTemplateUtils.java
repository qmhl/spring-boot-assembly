package io.geekidea.springboot.assembly.demo.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangqi
 * @Description: ***
 * @date 2021/6/8 13:50
 */
@Slf4j
public class RestTemplateUtils {

    /**
     * 根据get方式请求接口
     *
     * @param url          请求路径
     * @param map          url后面的参数
     * @param restTemplate
     * @param headers      请求头
     * @return
     */
    public static String requestByGet(String url, HashMap<String, Object> map, RestTemplate restTemplate, HttpHeaders headers) {

        // header填充
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(null, headers);
        // 拼接参数
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<String> responseEntity;
        //如果存在參數
        if (!map.isEmpty()) {
            for (Map.Entry<String, Object> e :
                    map.entrySet()) {
                //构建查询参数
                builder.queryParam(e.getKey(), e.getValue());
            }
            //拼接好参数后的URl//test.com/url?param1={param1}&param2={param2};
            String reallyUrl = builder.build().toString();
            log.info("请求的url ：{}", reallyUrl);
            responseEntity = restTemplate.exchange(reallyUrl, HttpMethod.GET, request, String.class);
        } else {
            log.info("请求的url ：{}", url);

            responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        }
        String body = responseEntity.getBody();
        return body;
    }

    /**
     * post--表单方式
     * 参考BdpTableController 中写法
     *
     * @param url
     * @param form
     * @param restTemplate
     * @param headers
     * @return
     */
    public static String requestFormByPost(String url, MultiValueMap<String, Object> form, RestTemplate restTemplate, HttpHeaders headers) {
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(form, headers);
        log.info("请求的入参：{}", JSON.toJSONString(request));
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String body = response.getBody();
        return body;
    }

    /**
     * post-json方式
     * 参考BdpTableController 中写法
     *
     * @param url
     * @param form
     * @param restTemplate
     * @param headers
     * @return
     */
    public static String requestJsonByPost(String url, MultiValueMap<String, String> form, RestTemplate restTemplate, HttpHeaders headers) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String body = response.getBody();
        return body;
    }


}
