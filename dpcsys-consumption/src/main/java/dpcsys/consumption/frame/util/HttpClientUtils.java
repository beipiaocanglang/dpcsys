package dpcsys.consumption.frame.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) @2016 Webank Group Holding Limited
 */
public class HttpClientUtils {

	public static final String ERROR = "ERROR";

    /**
     * GET请求
     * @param httpClient
     * @param url
     * @param params
     * @return
     */
    public static String doGet(CloseableHttpClient httpClient, String url, Map<String,Object> params) {
    	if(StringUtils.isNotBlank(url) && null != httpClient){
    		CloseableHttpResponse response = null;
    		HttpGet httpGet = null;
    		try {
    			String apiUrl = url;  
        		StringBuffer param = new StringBuffer();  
        		int i = 0;
        		if(null != params && params.size() > 0){
        			for (String key : params.keySet()) {  
        				if (i == 0 && apiUrl.indexOf("?") == -1)  
        					param.append("?");  
        				else{
        					if(i == 0){
        						String paramSub = apiUrl.substring(apiUrl.indexOf("?")+1,apiUrl.length());
        						if(null != paramSub && paramSub.trim().indexOf("=") != -1 && !apiUrl.trim().endsWith("&")){
        							param.append("&");
        						}
        					}else param.append("&");
        				}
        				param.append(key).append("=").append((null == params.get(key))?"":URLEncoder.encode(params.get(key).toString(), "utf-8"));  
        				i++;  
        			}  
        			apiUrl += param; 
        		}
        		httpGet = new HttpGet(apiUrl);
        		//httpClient = createHttpClient();
        		response = httpClient.execute(httpGet);
        		int statusCode = response.getStatusLine().getStatusCode();
                String responseEntity = EntityUtils.toString(response.getEntity(),"UTF-8");
        		System.err.println("doGet("+apiUrl+") status code is:"+statusCode);
        		return responseEntity;
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				try {
					if(null != httpGet) httpGet.abort();
					if(null != response) response.close();
					if(null != httpClient) httpClient.close();
				} catch (Exception e2) {}
			}
    	}
    	return ERROR;
    }
    
    /**
     * POST请求
     * @param httpClient 
     * @param url
     * @param params
     * @return
     */
    public static String doPost(CloseableHttpClient httpClient, String url, Map<String,Object> params) {
    	//CloseableHttpClient httpClient = createHttpClient();
    	if(null != httpClient && StringUtils.isNotBlank(url)){
    		String httpStr = null;  
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            try {  
                List<NameValuePair> pairList = null;
                if(null != params && params.size() > 0){
                	pairList = new ArrayList<NameValuePair>(params.size());
                	for (Map.Entry<String, Object> entry : params.entrySet()) {  
                		NameValuePair pair = new BasicNameValuePair(entry.getKey(),entry.getValue().toString());
                		pairList.add(pair);
                	}
                }
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
                response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                httpStr = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {  
                e.printStackTrace();
                return ERROR;
            } finally {  
                try {  
                	if (response != null) EntityUtils.consume(response.getEntity());
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return httpStr;
    	}
        return ERROR;
    }
}
