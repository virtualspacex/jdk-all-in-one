/*
 * @Author: keiki
 * @Date: 2020-12-20 12:47:32
 * @LastEditTime: 2021-01-01 15:31:10
 * @LastEditors: keiki
 * @Description: 
 */
package cn.com.virtualspacex.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import cn.com.virtualspacex.constants.Constant;

/**
 * 处理请求
 */
public class HttpUtil {

//    private static CloseableHttpClient httpClient = null;
//
//    /**
//     * 初始化 获得Http客户端
//     */
//    private static void init() {
//        if (httpClient == null) {
//            httpClient = HttpClientBuilder.create().build();
//        }
//    }

    /**
     *
     * @param url
     * @param jsonParameter
     * @return  响应json实体 响应实体为null则请求失败
     */
    public HttpResponse doPost(String url, String jsonParameter, String token) throws Exception{
//        init();
    	CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        HttpResponse httpsResponse = null;
        
        // 设置请求和传输超时时间
        // httpPost.setConfig(requestConfig);
        
        StringEntity entity = new StringEntity(jsonParameter, Constant.ENCODING_UTF8);
        
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader(new BasicHeader(Constant.HTTP_CONTENT_TYPE, Constant.HEAD_CONTNT_TYPE_JSON));
        if(token != null) {
        	httpPost.setHeader(new BasicHeader("token" , token));
        }
        
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            httpsResponse = HttpResponse.getHttpResponse(response);
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return httpsResponse;
    }

//    //关闭连接
//    public static void close() {
//        try {
//            // 释放资源
//            if (httpClient != null) {
//                httpClient.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
