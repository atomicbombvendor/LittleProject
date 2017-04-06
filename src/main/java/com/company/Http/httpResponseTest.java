package com.company.Http;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Created by eli9 on 4/5/2017.
 */
public class httpResponseTest {
    public void gethttpResponse() throws IOException {
        String userName = "xieyuepinran51437@163.com";
        String password = "chunchunj";
        String logurl = "https://passport.csdn.net/account/login?ref=toolbar";
        String url = "http://blog.csdn.net/z69183787/article/details/47171059";
        CredentialsProvider provider = new BasicCredentialsProvider();//这种认证方式不适用于github
        provider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName,password));
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        HttpGet httpGet = new HttpGet(logurl);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println("httpResponse: "+httpResponse);
        System.out.println( "Headers: " + httpResponse.getHeaders("Set-Cookie"));
        System.out.println( httpResponse.getFirstHeader("Set-Cookie"));
        System.out.println( httpResponse.getFirstHeader("Content-Type"));
        System.out.println( httpResponse.getFirstHeader("Expires"));
        System.out.println( httpResponse.getFirstHeader("Path"));
    }

    public static void main(String[] args) {
        httpResponseTest test = new httpResponseTest();
        try {
            test.gethttpResponse();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
