/*
 * @fileName: HttpsResponse.java
 * @Description: 
 * @date: 2020/3/17
 * @Copyright: Copyright (c) 2020,Fuji Electric (Hangzhou) Software Co., Ltd. All rights reserved.
 */
package cn.com.virtualspacex.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import cn.com.virtualspacex.constants.Constant;

/**
 * @ClassName: HttpsResponse
 * @Description: httpsリクエスト、リターンのレスポンス
 * @author: wei-yushan
 */
@SuppressWarnings("deprecation")
public class HttpResponse {
    public final Header[] headers;
    public final String entity;
    public final HttpParams params;
    public final StatusLine statusLine;
    
    private HttpResponse(Header[] headers, HttpEntity entity, HttpParams params, StatusLine statusLine) throws Exception{
    	this.headers = headers;
		this.entity = EntityUtils.toString(entity, Constant.ENCODING_UTF8);
    	this.params = params;
    	this.statusLine = statusLine;
    }
    
    public static HttpResponse getHttpResponse(CloseableHttpResponse httpsResponse) throws Exception{
    	return new HttpResponse(httpsResponse.getAllHeaders(), 
    			httpsResponse.getEntity(), 
    			httpsResponse.getParams(), 
    			httpsResponse.getStatusLine());
    }
}
