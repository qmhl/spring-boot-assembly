package io.geekidea.springboot.assembly.demo.utils;


import com.alibaba.excel.util.MapUtils;
import com.alibaba.fastjson.JSON;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author
 */
@Slf4j
public class HttpClientUtils {
    // 创建socket的上限是200
    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 200;
    //对每个指定连接的服务器（指定的ip）可以创建并发200 socket进行访问
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 200;
    // 设置请求超时时间为60秒
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (60 * 1000);
    private static final String DEFAULT_UTF8_CHARSET = StandardCharsets.UTF_8.name();
    //连接池管理
    private static PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

    static {
        manager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        manager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
    }

    /**
     * 根据连接池配置，创建默认的httpClient
     */
    private static CloseableHttpClient createDefaultHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(manager)
                .build();
    }

    public static String get(String url) {
        CloseableHttpClient httpClient = createDefaultHttpClient();
        HttpGet request = createHttpGet(url);

        CloseableHttpResponse response = null;
        String result;
        try {
            response = httpClient.execute(request);
            result = parse(response);
        } catch (Exception ex) {
            log.error("执行http get请求失败", ex);
            throw new RuntimeException("执行http get请求失败");
        } finally {
            request.releaseConnection();
            close(response);
        }
        return result;
    }

    /**
     * 发送json参数的Post请求
     */
    public static String post(String url, String params) {
        CloseableHttpClient httpClient = createDefaultHttpClient();

        HttpPost request = createHttpPost(url, params, ContentType.APPLICATION_JSON);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Accept", "application/json, text/plain, */*");

        CloseableHttpResponse response = null;
        String result;

        try {
            log.warn("请求http入参:{}", params);
            response = httpClient.execute(request);
            result = parse(response);
            log.warn("请求http结果:{}", JSON.toJSONString(result));
        } catch (Exception ex) {
            log.error("执行http post请求失败", ex);
            throw new RuntimeException("执行http post请求失败");
        } finally {
            request.releaseConnection();
            close(response);
        }

        return result;
    }
  /**
     * 发送json参数的Post请求
     */
    public static String post(String url, String params, Map<String,String> headers) {
        CloseableHttpClient httpClient = createDefaultHttpClient();

        HttpPost request = createHttpPost(url, params, ContentType.APPLICATION_JSON);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Accept", "application/json, text/plain, */*");
        if (headers !=null ) {
            Set<Map.Entry<String, String>> entries = headers.entrySet();

            for (Map.Entry<String, String> e : entries) {
                String value = e.getValue();
                String key = e.getKey();
                request.addHeader(key, value);
            }
        }
        CloseableHttpResponse response = null;
        String result;

        try {
            log.warn("请求http入参:{}", params);
            response = httpClient.execute(request);
            result = parse(response);
            log.warn("请求http结果:{}", JSON.toJSONString(result));
        } catch (Exception ex) {
            log.error("执行http post请求失败", ex);
            throw new RuntimeException("执行http post请求失败");
        } finally {
            request.releaseConnection();
            close(response);
        }

        return result;
    }

    /**
     * 发送表单格式的Post请求
     */
    public static String postForm(String url, Map<String, String> paramMap, Map<String,String> headers) {
        CloseableHttpClient httpClient = createDefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        setRequestConfig(httpPost);
        // 添加请求头
        ContentType APPLICATION_FORM_URLENCODED_UTF8 = ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8);
        httpPost.addHeader("Content-Type", APPLICATION_FORM_URLENCODED_UTF8.toString());
        if (headers !=null) {
            Set<Map.Entry<String, String>> entries = headers.entrySet();
            for (Map.Entry<String, String> e : entries) {
                String value = e.getValue();
                String key = e.getKey();
                httpPost.addHeader(key, value);
            }
        }

        CloseableHttpResponse response = null;
        String result;
        try {

            // add key-value pair to entity : 设置参数到请求体中
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(entity);
            }

            log.info("请求http postForm入参uri:{}, {} ", httpPost.getURI(),JSON.toJSONString(httpPost.getEntity()));
            response = httpClient.execute(httpPost);
            result = parse(response);
            log.info("请求http postForm结果:{}", JSON.toJSONString(result));
        } catch (Exception ex) {
            log.error("执行http postForm请求失败 exception >>> ", ex);
            throw new RuntimeException("执行http postForm请求失败");
        } finally {
            httpPost.releaseConnection();
            close(response);
        }
        return result;
    }


    private static void close(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("关闭Http请求时发生异常");
        }
    }


    /**
     * 对http请求进行基本设置
     *
     * @param httpRequestBase
     *            http请求
     */
    private static void setRequestConfig(HttpRequestBase httpRequestBase) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS)
                .setConnectTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS).setSocketTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS).build();
        httpRequestBase.setConfig(requestConfig);
    }

    private static HttpPost createHttpPost(String url, String param, ContentType contentType) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(
                DEFAULT_READ_TIMEOUT_MILLISECONDS).setConnectTimeout(
                DEFAULT_READ_TIMEOUT_MILLISECONDS).build();
        HttpEntity uefEntity = new StringEntity(param, contentType);
        httpPost.setEntity(uefEntity);
        httpPost.setConfig(requestConfig);

        return httpPost;
    }

    private static HttpGet createHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(
                DEFAULT_READ_TIMEOUT_MILLISECONDS).setConnectTimeout(
                DEFAULT_READ_TIMEOUT_MILLISECONDS).build();

        httpGet.setConfig(requestConfig);

        return httpGet;
    }

    private static String parse(CloseableHttpResponse httpResponse) throws Exception {

        try {
            // 获取状态码
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
//            if (statusCode != HttpStatus.SC_OK) {
//                String errorMsg = String.format(" Http Status Code: %d ", statusCode);
//                throw new HttpException(errorMsg);
//            }

            // 获取实体的值
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity == null) {
                throw new HttpException("DefaultRestClient请求出现异常");
            }

            String entityJson = EntityUtils.toString(httpEntity, DEFAULT_UTF8_CHARSET);
            EntityUtils.consume(httpEntity);

            return entityJson;

        } finally {
            httpResponse.close();
        }
    }




    /**http下载*/
    public static void httpDownload(String httpUrl, File file){
        // 下载网络文件
        int byteread;
        URL url;
        try {
            url = new URL(httpUrl);
            // 修复安全 https://joyspace.jd.com/page/0Pmbn54XDvAgcJjKjDby
            boolean safe = jdSsrfCheck(url);
            if (safe) {
                URLConnection conn = url.openConnection();
                InputStream inStream = conn.getInputStream();
                FileOutputStream fs = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                if (fs != null) {
                    fs.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            }else {
                log.info("url不安全！：{}",httpUrl);
            }
        } catch (IOException e) {
            log.error("下载失败！url:{}",httpUrl,e);
        }
    }

    /**http下载*/
    public static List<String> httpDownloadToList(String httpUrl){
        List<String> skus = new ArrayList<>();
        if (StringUtils.isEmpty(httpUrl)){
            return skus;
        }
        // 下载网络文件
        URL url;
        try {
            url = new URL(httpUrl);
            // 修复安全 https://joyspace.jd.com/page/0Pmbn54XDvAgcJjKjDby
            boolean safe = jdSsrfCheck(url);
            if (safe) {
                URLConnection conn = url.openConnection();
                InputStream inStream = conn.getInputStream();
                Scanner scanner = new Scanner(inStream);
                while (scanner.hasNextLine()) {
                    skus.add(StringUtils.trim(scanner.nextLine()));
                }
                inStream.close();
            }else {
                log.info("url不安全！：{}",httpUrl);
            }
        } catch (IOException e) {
            log.error("下载失败！url:{}",httpUrl,e);
        }
        return skus;
    }
//    http://selection-platform.s3-internal.cn-north-1.jdcloud-oss.com/xycenter/202306151431501058019414714277829520.txt
    public static boolean jdSsrfCheck(URL urlObj){
        //定义请求协议白名单列表
        String[] allowProtocols = new String[]{"http", "https"};
        //定义请求域名白名单列表
        String[] allowDomains = new String[]{"www.jd.com","storage.jd.local","selection-platform.s3.cn-north-1.jdcloud-oss.com",
        "selection-platform.s3-internal.cn-north-1.jdcloud-oss.com"};
        //定义请求端口白名单列表
        int[] allowPorts = new int[]{80, 443};
        boolean ssrfCheck = false, protocolCheck = false, domianCheck = false;

        // 首先进行协议校验，若协议校验不通过，SSRF校验不通过
        String protocol = urlObj.getProtocol();
        for(String item : allowProtocols){
            if(protocol.equals(item)){
                protocolCheck = true;
                break;
            }
        }
        // 协议校验通过后，再进行域名校验，反之不进行域名校验，SSRF校验不通过
        if(protocolCheck){
            String host = urlObj.getHost();
            for(String domain: allowDomains){
                if(domain.equals(host)){
                    domianCheck = true;
                    break;
                }
            }
        }
        //域名校验通过后，再进行端口校验，反之不进行端口校验，SSRF校验不通过
        if(domianCheck){
            int port = urlObj.getPort();
            if(port == -1) {
                port = 80;
            }
            for (Integer item : allowPorts) {
                if (item == port) {
                    ssrfCheck = true;
                    break;
                }
            }
        }
        if(ssrfCheck){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
//        List<String> stringList = httpDownloadToList("http://selection-platform.s3-internal.cn-north-1.jdcloud-oss.com/xycenter/202306151431501058019414714277829520.txt");
//        System.out.println(stringList.size());
        Map<String,String> headers = new HashMap<>();
        headers.put("Cookie","shshshfpa=7c2bc937-075e-cb3a-86d5-0935e74d180b-1618897707; shshshfpx=7c2bc937-075e-cb3a-86d5-0935e74d180b-1618897707; pinId=V3-W7QA0j-tbdqVHa-w8xw; TrackID=11S9zLolfsSlVHIg6RbA9xgzMoujoJUtuNi_Q8O4FNRt9_GYHWve30FdLgN-Fdo9JEGRfh2uEgdLqZXdcFfVyBw1415SK8xYaZP3IUxBHaxdPIYpA3vaOWG32BjeIrKyA; 3AB9D23F7A4B3CSS=jdd03HVTBDVR4D7KXKI5CYDWLEBLLM5LK3XQALEEENYM5PQTXAXSXXPLT2G3QSN2VMKESVPMZ7GYZSQ74VAL77V5WJMJ5ZQAAAAMHXUHJH7AAAAAADHVCEXDKABU7WYX; jd.erp.lang=zh_CN; jdd69fo72b8lfeoe=P7JRLYW5HKLQLIAO7TEAEG6U3ZS547N7HSB7D5FD43G76DOFSPFJIZKZUNPUAD33YNQWXCIZSDGPN6SRMB2DC7PJJM; logbook_u=guochunyuan; shshshfp=48a7ad1616f138bff754c62ba6f69fd6; shshshfpb=f%2FMd1rxDlCr8aszNg5iwt1A%3D%3D; mba_muid=16787974359061074674965; smartops.jd.com=61d78ee10b6707939f8306586d4ac0fb; idc.jd.com=de02a7393342b7beb58c2b2c27425977; smartops_user_type=0; idc_user_type=2; qid_uid=ddbee25f-dcb4-43c6-a217-2d124a068e16; qid_fs=1686118879594; qid_ls=1686118879594; qid_ts=1686118879601; qid_vis=1; SSOID=\"e3aebd0e5f2c1262d54d934b82bdfea09f1cd811c1c37fe86b4cef05f5647c79b3e8d5f0075ef39dccf04f8dd4408fa0,guochunyuan\"; __jdv=137579179|direct|-|none|-|1687745564215; 3AB9D23F7A4B3C9B=JQYUUGURZB6M4XIIDO6OAEEDL3YE3NHB7VDTDTADNERSJEJV2KZTQCROX33KYGLEVY6EFT7AKKRS2A7RHHL7XU5IUQ; token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlE5ZHhnd0F3QzIxSERhRXoybWlVIiwiaWF0IjoxNjg4Mjc3Mzc5LCJleHAiOjE2ODg4ODIxNzl9.1fUWmwfSmRLDn2VKIamkUpVoZ1G174FgZjmlTVNoAPg; ssa.selection-server-web=57761f6ab758efa8b50d4458b83caeb24a28376cab051d87371d8eec73a8a1ff9bf888e289e81aa9e5943c48ccc2d135bbb102058dd6bddfaf400fc84ecfa2387b9502bdbe36966ac5f51e267831a4e461f12c407b9971cef0892cb8a8dd6268bda9247075392cb625ebaafdd80afdf212a61a125d23a365e5844d83cd6a6051; focus-team-id=00046419; focus-client=WEB; __jdu=16883554762821913951142; sso.jd.com=BJ.89CBDD51D257A3ECCB130DCD43690879.5920230704103025; ssa.global.ticket=B33559495D5A35B39A1B60366731F061; focus-token=8a386e2ac6c3e4c32f5e950e539bd957; __jda=76794896.16883554762821913951142.1688355476.1688372699.1688437918.4; __jdc=76794896; __jdb=76794896.3.16883554762821913951142|4.1688437918; ssa.label_platform_web.state=2tHw0Wy-WKmrivOdq-Dbqy1zb0XpJGTzGRw7CFXuXvY");
        String params = "{\n" +
                "  \"roleType\": -1,\n" +
                "  \"dimType\": 1,\n" +
                "  \"scene\": 2,\n" +
                "  \"businessType\": \"29\",\n" +
                "  \"poolType\": 29,\n" +
                "  \"jobIds\": \"\"\n" +
                "}";
        String post = post("http://test.xpyx.jd.com/@@/xpyx/labelService/service/queryIntegrationSelectionRule", params, headers);
        System.out.println(post);
    }

}