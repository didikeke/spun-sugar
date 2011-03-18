package com.didikeke.spunsugar.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.didikeke.spunsugar.client.domain.Item;
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

    public static List<Item> newItemList(String html) {
        String itemsHtml = StringUtils.substringBetween(html, "<table border=\"0\" class=\"patFunc\"> ","</table>");
        String[] itemEntries = itemsHtml.split("<tr class=\"patFuncEntry\">");
        List<Item> result = new ArrayList<Item>();
        //int i =1 ,是因为第1个部分不是图书信息，所以略过
        for(int i=1; i< itemEntries.length; i++){
            Item item = newItem(itemEntries[i]);
            result.add(item);
        }
        return result;
    }
    
    public static Item newItem(String html){
        
        String renewId = StringUtils.substringBetween(html,"value=\"","\" />");
        String id = StringUtils.substringBetween(html, "/item&", "\">");
        String title = StringUtils.trim(StringUtils.substringBetween(html, "/item&"+id+"\">","</a>"));
        String barcode = StringUtils.trim(StringUtils.substringBetween(html, "<td align=\"left\" class=\"patFuncBarcode\">", "</td>"));
        String renewCount = StringUtils.substringBetween(html,"<span  class=\"patFuncRenewCount\">","</span>");
        String status=StringUtils.trim(StringUtils.substringBetween(html,"<td align=\"left\" class=\"patFuncStatus\">","<span"));
        String callNo = StringUtils.trim(StringUtils.substringBetween(html,"<td align=\"left\" class=\"patFuncCallNo\">","</td>"));
        
        Item item = new Item();
        item.setRenewId(renewId);
        item.setId(id);
        item.setTitle(title);
        item.setBarcode(barcode);
        item.setRenewCount(renewCount);
        item.setStatus(status);
        item.setCallNo(callNo);
        return item;
    }
    
}
