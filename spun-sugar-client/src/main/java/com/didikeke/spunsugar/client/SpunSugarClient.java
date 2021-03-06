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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.didikeke.spunsugar.client.domain.Item;
import com.didikeke.spunsugar.client.domain.User;
import com.didikeke.spunsugar.client.util.ObjUtils;

public class SpunSugarClient {
    
    //private final static int connectionTimeoutMillis = 10 * 1000;
    //private final static int socketTimeoutMillis = 30 * 1000;
    
    private HttpClient httpclient;
    
    private String username;
    private String password;
    
    private User user;
    
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
    
    /*
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() {                
        public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context)  {
            boolean isRedirect=false;
            try {
                isRedirect = super.isRedirected(request, response, context);
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            if (!isRedirect) {
                int responseCode = response.getStatusLine().getStatusCode();
                if (responseCode == 301 || responseCode == 302) {
                    return true;
                }
            }
            return false;
        }
    };*/
    
    public SpunSugarClient(String username, String password){
        this.username = username;
        this.password = password;
        initHttpclient();
    }
   
    private void initHttpclient(){
        HttpParams httpParams = new BasicHttpParams();
        //HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeoutMillis);
        //HttpConnectionParams.setSoTimeout(httpParams, socketTimeoutMillis);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        //client.setRedirectStrategy(redirectStrategy);  
        client.setRedirectHandler(new DefaultRedirectHandler());
        this.httpclient = client;
    }
    
    protected User login() throws IOException{
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("name", username));
        formparams.add(new BasicNameValuePair("code", password));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        HttpPost httppost = new HttpPost("http://ztiii.zjlib.cn/patroninfo*chx");
        //httppost.setHeader("Set-Cookie", cookies);
        httppost.setEntity(entity);
        String html = httpclient.execute(httppost,responseHandler); 
        User user = ObjUtils.newUser(html);
        if(null == user){
            throw new SpunSugarLoginException("登录失败");
        }
        
        return user;
    }
    
    public User getUser() throws IOException{
    	if(null == user){
    		user = login();
    	}
    	return user;
    }
    
    public List<Item> getItems() throws IOException{
    	 return getPathItems("/items",1);          
    }
    
    public List<Item> getHolds() throws IOException {
        return getPathItems("/holds",1);
    }
    
    public List<Item> getReadingHistory() throws IOException {
    	return getPathItems("/readinghistory",1); 
    }
    
    protected List<Item> getPathItems(String path,int retry) throws IOException{    	
    	String id = getUser().getId();    	    	
    	HttpGet httpget = new HttpGet("http://ztiii.zjlib.cn/patroninfo~S0*chx/" 
                + id + path);
    	String html = httpclient.execute(httpget,responseHandler);
    	if(retry > 0 && isSessionTimeOut(html)){
    	    retry--;
    	    //re-login
    	    user = login();
    	    return getPathItems(path,retry);
    	}
        List<Item> result = ObjUtils.newItemList(html);
        return result;  
    }
    
    private boolean isSessionTimeOut(String html){
        return null == html || !html.contains(username);
    }
}
