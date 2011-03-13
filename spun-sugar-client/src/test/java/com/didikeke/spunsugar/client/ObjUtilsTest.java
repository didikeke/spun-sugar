package com.didikeke.spunsugar.client;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.didikeke.spunsugar.client.domain.Reader;


public class ObjUtilsTest {

    @Test
    public void testNewReader() throws IOException{
        String html = getHtml("login.html");
        Reader obj = ObjUtils.newReader(html);
        Assert.assertEquals("迪迪可可", obj.getName());
        Assert.assertEquals("1153927", obj.getId()); 
        Assert.assertEquals("2012-10-14", obj.getValidDate());
    }
    
    
    private String getHtml(String filename) throws IOException{
        InputStream resourceAsStream = getClass().getResourceAsStream("/data/" + filename);
        String result = IOUtils.toString(resourceAsStream,"UTF-8");
        resourceAsStream.close();
        return result;
    }
}
