package com.didikeke.spunsugar.client;

import junit.framework.Assert;

import org.junit.Test;

import com.didikeke.spunsugar.client.util.TextUtils;

public class TextUtilsTest {

    @Test 
    public void testTrimDate(){
        String date1 = TextUtils.trimDate("2008-06-17");
        Assert.assertEquals("08-06-17", date1);
        
        String date2 = TextUtils.trimDate("到期 11-10-05");
        Assert.assertEquals("11-10-05", date2);
    }
    
}
