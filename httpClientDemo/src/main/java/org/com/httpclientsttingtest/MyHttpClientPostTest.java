package org.com.httpclientsttingtest;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
public class MyHttpClientPostTest {
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
    public void myHttpCookiesTest1() throws IOException {
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
    @Test(dependsOnMethods = "myHttpCookiesTest1")
    public void postInterfaceTest () throws IOException {
        //拼接测试地址url
        String postcookieurl=Bundle.getString("test.postcookies");
        String testurl=this.url+postcookieurl;
        //new一个Client对象来执行post请求
        DefaultHttpClient Client = new DefaultHttpClient();
        //声明请求的方法
        HttpPost post= new HttpPost(testurl);
        //添加参数
        JSONObject param= new JSONObject();
        param.put("name","xiaokele");
        param.put( "age","3");
        //将参数信息带入post方法中
        StringEntity entity= new StringEntity(param.toString());
        post.setEntity(entity);
        //添加请求头信息
        post.setHeader("content-type","application/json");
        //set上面获取到的cookie
        Client.setCookieStore(this.store);
        //执行post方法，拿到返回结果
        HttpResponse response=Client.execute(post);
        String result =  EntityUtils.toString(response.getEntity());
        System.out.println(result);
        // 将返回的数据转换成json对象
        JSONObject  json1=new JSONObject(result);
        //判断返回的数据字段是否正确
        String a =json1.getString("xiaokele");
        Assert.assertEquals("success",a);

        }

}
