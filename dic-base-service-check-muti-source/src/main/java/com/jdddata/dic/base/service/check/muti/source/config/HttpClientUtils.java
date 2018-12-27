package com.jdddata.dic.base.service.check.muti.source.config;

import com.jdddata.dic.common.util.ConvertUtils;
import com.jdddata.dic.common.util.HttpClientUtil;
import com.jdddata.dic.common.util.MyJsonUtils;
import com.jdddata.dic.common.util.NewRequestUtils;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by zhengjiexiang on 2018/12/21
 */
public class HttpClientUtils {

    public static Map httpPost(String url, Request request) {
        try {
            Map<String,Object> paramsPost = new HashMap<>();
            paramsPost.put("request", NewRequestUtils.encryptRequest(MyJsonUtils.bean2Json(request)));
            String responseContent = httpPostRequest(url,paramsPost,10000,10000,10000);
            return MyJsonUtils.json2Map(responseContent);
        } catch (Exception e) {
            XxlJobLogger.log("HTTP 请求失败" + e.getMessage());
        }
        return null;
    }

    private static String httpPostRequest(String httpUrl, Map<String, Object> params, int socketTimeout, int connectTimeout, int connectionRequestTimeout) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(httpUrl);
        httpPost.setHeader("pCode", "job");
        if (MapUtils.isNotEmpty(params)) {
            List<NameValuePair> nvps = new ArrayList();
            Iterator var8 = params.entrySet().iterator();

            while(var8.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var8.next();
                nvps.add(new BasicNameValuePair((String)entry.getKey(), ConvertUtils.convertObjectToString(entry.getValue())));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
        return sendHttpPost(httpPost, requestConfig);
    }

    private static String sendHttpPost(HttpPost httpPost, RequestConfig requestConfig) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;

        try {
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            closeHttpConnection(response, httpClient);
        }

        return responseContent;
    }

    private static void closeHttpConnection(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        try {
            if (response != null) {
                response.close();
            }

            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

}
