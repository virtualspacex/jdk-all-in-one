package com.virtualspacex.util.io.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    
    /**
    * Http get请求
    * @param httpUrl 连接
    * @return 响应数据
    */
    public static String doRequest(HttpRequestEntity requestEntity){
    //链接
    HttpURLConnection connection = null;
    InputStream is = null;
    BufferedReader br = null;
    StringBuffer result = new StringBuffer();
    try {
        //创建连接
        URL url = new URL(requestEntity.url);
        connection = (HttpURLConnection) url.openConnection();

        //设置请求方式
        connection.setRequestMethod(requestEntity.method);
        //设置连接超时时间
        connection.setReadTimeout(requestEntity.readTimeout);

        //开始连接
        connection.connect();
        //获取响应数据
        if (connection.getResponseCode() == 200) {
            //获取返回的数据
            is = connection.getInputStream();
            if (null != is) {
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String temp = null;
                while (null != (temp = br.readLine())) {
                    result.append(temp);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //关闭远程连接
        connection.disconnect();
    }
    return result.toString();
    }

    public static class HttpRequestEntity{
        public String url;

        public String method = "GET";

        public int connectTimeout = 60000;

        public int readTimeout = 60000;

        public boolean allowUserInteraction = false;

        public int chunkLength = -1;
    }

    public class HttpResponseEntity{

    }
    
}
