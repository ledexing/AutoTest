package org.com.httpclientsttingtest;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;


public class MyHttpClientGetTest {
    private String url;
    private ResourceBundle Bundle;
    private CookieStore store;
    @BeforeTest
    public void beForeMethodget(){
        //从配置文件中获取数据
        Bundle =ResourceBundle.getBundle("httpclientstting");
        url = Bundle.getString("dev.url");
    }
    @Test
    public void myHttpCookiesTest() throws IOException {
        //拼接url
        String testurl = this.url +Bundle.getString("getcookies.url");

        HttpGet get= new HttpGet(testurl);
        //new一个对象来执行get请求
        // HttpClient Client = new DefaultHttpClient();

        //获取cookies使用DefaultHttpClient的实例
        DefaultHttpClient Client = new DefaultHttpClient();
        HttpResponse response=Client.execute(get);
        //转化成字符串
         String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //获取cookies的返回一个list
        this.store = Client.getCookieStore();
        List<Cookie> cookie= store.getCookies();
        System.out.println(cookie);
        //遍历list输出结果
        for(Cookie a :cookie){
         String name=a.getName();
         String value=a.getValue();
            System.out.println("cookie的name= "+name+" ; cookie的value= "+value);
        }

    }
    @Test(dependsOnMethods = "myHttpCookiesTest")
    public void setCookieTest () throws IOException {
        String result1 =null;
        String setcookieurl=Bundle.getString("test.setcookies");
        String testurl=this.url+setcookieurl;

        HttpGet get= new HttpGet(testurl);
        //new一个对象来执行get请求
        DefaultHttpClient Client = new DefaultHttpClient();
        //set上面获取到的cookie
        Client.setCookieStore(this.store);
        HttpResponse response=Client.execute(get);
        int status=response.getStatusLine().getStatusCode();
        if (status==200){
        //转化成字符串
         result1 = EntityUtils.toString(response.getEntity());
        System.out.println(result1);
        }else {
            System.out.println("访问失败了");
        }
    }

}
