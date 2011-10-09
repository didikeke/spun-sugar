package com.didikeke.spunsugar.client.util;

import static com.didikeke.spunsugar.client.util.TextUtils.pickText;

import java.util.ArrayList;
import java.util.List;

import com.didikeke.spunsugar.client.domain.Item;
import com.didikeke.spunsugar.client.domain.User;

public class ObjUtils {
	
    public static User newUser(String html){  
    	String name = pickText(html,"<div class=\"patNameAddress\">.*?<strong>(.+?)</strong>",1);
    	String id = pickText(html,"/patroninfo~S0\\*chx/(.+?)/top/",1);
    	String expirationDate = pickText(html,"有效日期:(.+?)<br",1);
        if(null == name || null == id || null == expirationDate){
            return null;
        }
        User result = new User(id,name,expirationDate);
        return result;
    }

    
    public static List<Item> newItemList(String html) {
    	List<Item> result = new ArrayList<Item>();
    	String[] itemEntries = html.split("<tr class=\"patFuncEntry\">");
		// int i =1 ,是因为第1个部分不是图书信息，所以略过
		for (int i = 1; i < itemEntries.length; i++) {
			Item item = newItem(itemEntries[i]);
			result.add(item);
		}
    	return result;
    }

    
    public static Item newItem(String html){
        
        String renewId = pickText(html,"value=\"(.+?)\" />",1);
        String id = pickText(html,"/item&(.+?)\">",1);
        String title = pickText(html,"/item&"+id+"\">(.+?)</a>",1);
        String barcode = pickText(html,"<td align=\"left\" class=\"patFuncBarcode\">(.+?)</td>",1);
        String renewCount = pickText(html,"<span  class=\"patFuncRenewCount\">(.+?)</span>",1);
        String status = pickText(html,"class=\"patFuncStatus\">(.+?)<",1);
        String callNo = pickText(html,"<td align=\"left\" class=\"patFuncCallNo\">(.+?)</td>",1);
        
        String author = pickText(html,"class=\"patFuncAuthor\">(.+?)<",1);
        String date = pickText(html,"class=\"patFuncDate\">(.+?)<",1);
        String details = pickText(html,"class=\"patFuncDetails\">(.+?)<",1);
        
        if(null == id){
        	//for readingHistory
        	id = pickText(html,"/record=b(.+?)~S0\\*chx",1);
        }
        if(null == title){
        	title = pickText(html,"~S0\\*chx\">(.+?)<",1);
        }
        
        Item item = new Item();
        item.setRenewId(renewId);
        item.setId(id);
        item.setTitle(title);
        item.setBarcode(barcode);
        item.setRenewCount(renewCount);
        item.setStatus(status);
        item.setCallNo(callNo);
        
        item.setAuthor(author);
        item.setDate(date);
        item.setDetails(details);
        return item;
    }
}
