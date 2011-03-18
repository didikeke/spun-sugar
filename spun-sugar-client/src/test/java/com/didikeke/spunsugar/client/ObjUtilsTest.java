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
    public void testNewItemList() throws IOException{
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
    
    
    private String getHtml(String filename) throws IOException{
        InputStream resourceAsStream = getClass().getResourceAsStream("/data/" + filename);
        String result = IOUtils.toString(resourceAsStream,"UTF-8");
        resourceAsStream.close();
        return result;
    }
}
