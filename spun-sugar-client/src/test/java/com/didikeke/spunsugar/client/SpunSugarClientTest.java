package com.didikeke.spunsugar.client;

import java.io.IOException;

import org.junit.Test;

public class SpunSugarClientTest {
    
    private String username="xx";
    private String password="xxxx";
    
    @Test
    public void testLogin() throws IOException{
        SpunSugarClient client = new SpunSugarClient(username,password);
        //client.login();
    }

}
