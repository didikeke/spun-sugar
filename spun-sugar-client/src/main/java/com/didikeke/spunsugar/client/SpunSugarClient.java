package com.didikeke.spunsugar.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class SpunSugarClient {
    
    private final static int connectionTimeoutMillis = 10 * 1000;
    private final static int socketTimeoutMillis = 30 * 1000;
    
    private HttpClient httpclient;
    
    private String username;
    private String password;
    
    private ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
        public String handleResponse(HttpResponse response)
                throws ClientProtocolException, IOException {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            } else {
                return "";
            }
        }
    };
    
    public SpunSugarClient(String username, String password){
        this.username = username;
        this.password = password;
        initHttpclient();
    }
   
    private void initHttpclient(){
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeoutMillis);
        HttpConnectionParams.setSoTimeout(httpParams, socketTimeoutMillis);
        this.httpclient = new DefaultHttpClient(httpParams);
    }
    
    public void login() throws IOException{
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("name", username));
        formparams.add(new BasicNameValuePair("code", password));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        HttpPost httppost = new HttpPost("http://ztiii.zjlib.cn/patroninfo*chx");
        //httppost.setHeader("Set-Cookie", cookies);
        httppost.setEntity(entity);
        String result = httpclient.execute(httppost,responseHandler); 
        System.out.println(result);
    }
    
}
