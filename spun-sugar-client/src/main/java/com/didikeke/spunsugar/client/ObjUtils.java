package com.didikeke.spunsugar.client;

import org.apache.commons.lang.StringUtils;

import com.didikeke.spunsugar.client.domain.User;

public class ObjUtils {

    public static User newUser(String html){
        String name = StringUtils.substringBetween(html,"<div class=\"patNameAddress\">\n<strong>", "</strong>");
        String id = StringUtils.substringBetween(html, "http://ztiii.zjlib.cn/patroninfo~S0*chx/", "/holds");
        String expirationDate = StringUtils.substringBetween(html,"有效日期:","<br>");
        if(null == name || null == id || null == expirationDate){
            return null;
        }
        User result = new User(id,name,expirationDate);
        return result;
    }
}
