package com.sire.storage.ModuleEnvironment.Http;

import com.alibaba.fastjson.JSON;
import com.sire.storage.ModuleEnvironment.Utils.ObjectMapConversionUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class HttpAPIService {

    public static final String UTF_8 = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(HttpAPIService.class);
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig config;

    /*****************************GET************************/

    /**
     * 无参GET请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url) throws Exception {
        // 声明 Http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), UTF_8));
    }

    /**
     * 无参数GET请求，自动解析json实体
     *
     * @param url
     * @param result
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T doGet(String url, Class<T> result) throws Exception {
        HttpResult response = doGet(url);
        T jsonEntity = null;
        if (response.getHttpcode() == 200) {
            jsonEntity = JSON.parseObject(response.getHttpBody(), result);
        } else {
            logger.error("http错误码：" + response.getHttpcode() + ";错误信息：" + response.getHttpBody());
        }
        return jsonEntity;
    }


    /**
     * 带键值对参数GET请求
     * 带键值对参数的GET请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());

    }

    /**
     * 带键值对参数GET请求，自动解析Json实体
     *
     * @param url
     * @param map
     * @param result
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T doGet(String url, Map<String, Object> map, Class<T> result) throws Exception {
        HttpResult response = doGet(url, map);
        T jsonEntity = null;
        if (response.getHttpcode() == 200) {
            jsonEntity = JSON.parseObject(response.getHttpBody(), result);
        } else {
            logger.error("http错误码：" + response.getHttpcode() + ";错误信息：" + response.getHttpBody());
        }
        return jsonEntity;
    }

    /**
     * 带对象参数GET请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url, Object params) throws Exception {
        return doGet(url, ObjectMapConversionUtils.object2Map(params));
    }

    /**
     * 带对象参数GET请求，自动解析json实体
     *
     * @param url
     * @param params
     * @param result
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T doGet(String url, Object params, Class<T> result) throws Exception {
        HttpResult response = doGet(url, params);
        T jsonEntity = null;
        if (response.getHttpcode() == 200) {
            jsonEntity = JSON.parseObject(response.getHttpBody(), result);
        } else {
            logger.error("http错误码：" + response.getHttpcode() + ";错误信息：" + response.getHttpBody());
        }
        return jsonEntity;
    }


    /*****************************POST************************/

    /**
     * 不带参数post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url, new HashMap<>());
    }

    /**
     * 不带参数请求，自动解析json实体
     *
     * @param url
     * @param result
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T doPost(String url, Class<T> result) throws Exception {
        HttpResult response = doPost(url);
        T jsonEntity = null;
        if (response.getHttpcode() == 200) {
            jsonEntity = JSON.parseObject(response.getHttpBody(), result);
        } else {
            logger.error("http错误码：" + response.getHttpcode() + ";错误信息：" + response.getHttpBody());
        }
        return jsonEntity;
    }

    /**
     * 带键值对参数POST请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        setHttpFormParams(map, httpPost);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), UTF_8));
    }

    /**
     * 带键值对参数POST请求，自动解析json实体
     *
     * @param url
     * @param map
     * @param result
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T doPost(String url, Map<String, Object> map, Class<T> result) throws Exception {
        HttpResult response = doPost(url, map);
        T jsonEntity = null;
        if (response.getHttpcode() == 200) {
            jsonEntity = JSON.parseObject(response.getHttpBody(), result);
        } else {
            logger.error("http错误码：" + response.getHttpcode() + ";错误信息：" + response.getHttpBody());
        }
        return jsonEntity;
    }

    /**
     * 带对象参数POST请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Object params) throws Exception {
        return this.doPost(url, ObjectMapConversionUtils.object2Map(params));
    }

    /**
     * 带对象参数POST请求，自动解析json实体
     *
     * @param url
     * @param params
     * @param result
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T doPost(String url, Object params, Class<T> result) throws Exception {
        HttpResult response = doPost(url, params);
        T jsonEntity = null;
        if (response.getHttpcode() == 200) {
            jsonEntity = JSON.parseObject(response.getHttpBody(), result);
        } else {
            logger.error("http错误码：" + response.getHttpcode() + ";错误信息：" + response.getHttpBody());
        }
        return jsonEntity;
    }


    public <T> T doPostString(String url, Map<String, Object> params, Map<String, String> headers, Class<T> result) throws Exception {
        HttpResult response = doPostString(url, params, headers);
        T jsonEntity = null;
        if (response.getHttpcode() == 200) {
            jsonEntity = JSON.parseObject(response.getHttpBody(), result);
        } else {
            logger.error("http错误码：" + response.getHttpcode() + ";错误信息：" + response.getHttpBody());
        }
        return jsonEntity;
    }

    public <T> T doPostString(String url, Object params, Map<String, String> headers, Class<T> result) throws Exception {
        HttpResult response = doPostString(url, ObjectMapConversionUtils.object2Map(params), headers);
        T jsonEntity = null;
        if (response.getHttpcode() == 200) {
            jsonEntity = JSON.parseObject(response.getHttpBody(), result);
        } else {
            logger.error("http错误码：" + response.getHttpcode() + ";错误信息：" + response.getHttpBody());
        }
        return jsonEntity;
    }

    private HttpResult doPostString(String url, Map<String, Object> params, Map<String, String> headers) throws Exception {

        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        if (headers != null) {
            headers.forEach((key, value) -> httpPost.addHeader(key, value));
        }
        // 加入配置信息
        httpPost.setConfig(config);
        String stringParams = JSON.toJSONString(params);
        StringEntity stringEntity = new StringEntity(stringParams, "utf-8");
        httpPost.setEntity(stringEntity);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), UTF_8));
    }

    private void setHttpFormParams(Map<String, Object> params, HttpPost httpPost) throws UnsupportedEncodingException {
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (params != null && params.size() > 0) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, UTF_8);
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
    }


}