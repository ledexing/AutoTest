package org.com.httpclientdemo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultUserTokenHandler;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpClientDemo  {
    @Test
    public void demo1() throws IOException {
       String result=null;
       HttpGet Get=new HttpGet("http://www.biadu.com");
       //new一个对象来执行get请求
       HttpClient Client = new DefaultHttpClient();
       HttpResponse Response= Client.execute(Get);
       //转化成字符串
        result=EntityUtils.toString(Response.getEntity());
        System.out.println(result);

    }
}
