package com.didikeke.spunsugar.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.didikeke.spunsugar.client.domain.Item;
import com.didikeke.spunsugar.client.domain.User;


public class ObjUtilsTest {

    @Test
    public void testNewReader() throws IOException{
        String html = getHtml("login.html");
        User obj = ObjUtils.newUser(html);
        Assert.assertEquals("迪迪可可", obj.getName());
        Assert.assertEquals("1153927", obj.getId()); 
        Assert.assertEquals("2012-10-14", obj.getExpirationDate());
    }
    
    @Test
    public void testItems() throws IOException {
        String html = getHtml("items.html");
        List<Item> list = ObjUtils.newItemList(html); 
        Item item = list.get(0);
        Assert.assertEquals("2052451", item.getId());
        Assert.assertEquals("0000115032455", item.getBarcode());
        Assert.assertEquals("F0-05 416 2008", item.getCallNo());
        Assert.assertEquals("已续借1次", item.getRenewCount());
        Assert.assertEquals("i3319401", item.getRenewId());
        Assert.assertEquals("到期 11-04-15", item.getStatus());
        Assert.assertEquals("怪诞行为学 guai dan xing wei xue Predictably irrational (美)丹。艾瑞里(Dan Ariely)著 赵德亮，夏蓓洁", item.getTitle());

        Assert.assertEquals(2,list.size());
    }
    
    @Test
    public void testHolds() throws IOException {
    	String html = getHtml("holds.html");
        List<Item> list = ObjUtils.newItemList(html); 
        Item item = list.get(0);
        Assert.assertEquals("2244975", item.getId());
        Assert.assertEquals("织梦人 zhi meng ren 一个男孩穿越现实的哲学之旅 The dream weaver one boy's journey through the landsc", item.getTitle());
        Assert.assertEquals("到期 11-10-05", item.getStatus());

        Assert.assertEquals(1,list.size());
    }
    
    @Test 
    public void testReadingHistory() throws IOException {
    	String html = getHtml("readinghistory.html");
    	List<Item> list = ObjUtils.newItemList(html); 
        Item item = list.get(0);
        Assert.assertEquals("1981674", item.getId());
        Assert.assertEquals("心理实验室 xin li shi yan shi 走近实验心理学 周颖著", item.getTitle());
        Assert.assertEquals("周颖 zhou ying 著", item.getAuthor());
        Assert.assertEquals("2008-06-17", item.getDate());
        Assert.assertEquals("复本 1", item.getDetails());

        Assert.assertEquals(128,list.size());
    }
    
    
    private String getHtml(String filename) throws IOException{
        InputStream resourceAsStream = getClass().getResourceAsStream("/data/" + filename);
        String result = IOUtils.toString(resourceAsStream,"UTF-8");
        resourceAsStream.close();
        return result;
    }
    
}
