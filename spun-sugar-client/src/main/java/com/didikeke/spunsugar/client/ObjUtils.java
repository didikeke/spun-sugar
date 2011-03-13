package com.didikeke.spunsugar.client;

import org.apache.commons.lang.StringUtils;

import com.didikeke.spunsugar.client.domain.Reader;

public class ObjUtils {

    public static Reader newReader(String html){
        String name = StringUtils.substringBetween(html,"<div class=\"patNameAddress\">\n<strong>", "</strong>");
        String id = StringUtils.substringBetween(html, "http://ztiii.zjlib.cn/patroninfo~S0*chx/", "/holds");
        String validDate = StringUtils.substringBetween(html,"有效日期:","<br>");
        Reader result = new Reader(id,name,validDate);
        return result;
    }
}
