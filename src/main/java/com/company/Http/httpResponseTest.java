package com.company.Http;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * Created by eli9 on 4/5/2017.
 */
public class httpResponseTest {

    public static void main(String[] args) {
        String url = "http://xoi/XOISuite/IdList.aspx?Package=DataBank&Option=RecvMsgs_Start&ProductGroup=GEMORNITOR";
        String cookie =
                "XoiLogin=A1AE34AA-7E4F-4542-BDF7-FEAF8A679347; expires=Fri, 14-Apr-2017 04:49:06 GMT; path=/";
        String cookie2 =
                "XoiLogin=A1AE34AA-7E4F-4542-BDF7-FEAF8A679347; expires=Fri, 14-Apr-2017 04:49:06 GMT; path=/;";
        System.out.println(getResponseByURL(url,cookie));
        System.out.println(getString(url, cookie2));
    }

    public void gethttpResponse() throws IOException {
        String userName = "xieyuepinran51437@163.com";
        String password = "chunchunj";
        String logurl = "https://passport.csdn.net/account/login?ref=toolbar";
        String url = "http://blog.csdn.net/z69183787/article/details/47171059";
        CredentialsProvider provider = new BasicCredentialsProvider();//这种认证方式不适用于github
        provider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName, password));
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        HttpGet httpGet = new HttpGet(logurl);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println("httpResponse: " + httpResponse);
        System.out.println("Headers: " + httpResponse.getHeaders("Set-Cookie"));
        System.out.println(httpResponse.getFirstHeader("Set-Cookie"));
        System.out.println(httpResponse.getFirstHeader("Content-Type"));
        System.out.println(httpResponse.getFirstHeader("Expires"));
        System.out.println(httpResponse.getFirstHeader("Path"));
    }

    public static String getHttpClientExecute() throws Exception {
        String url = "http://www.v2ex.com/";
        int status = 200;
        int reRunTimes = 10;
        int runTimes = 0;
        String result = null;
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet httpget = new HttpGet(url);
        while (runTimes < reRunTimes) {
            try {
                HttpResponse response = httpclient.execute(httpget);

                String encoding = "utf-8";
                Header encodingHeader = response.getFirstHeader("Content-Encoding");
                if (encodingHeader != null) {
                    encoding = encodingHeader.getValue();
                }
                // Get result that from url

                result = EntityUtils.toString(response.getEntity(), encoding);

                status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    break;
                }
                System.out.println("runTimes: "+runTimes);
                runTimes++;
            } catch (Exception e) {

            }
        }
        httpget.releaseConnection();
        if (status == 200) {
            return result;
        } else {
            throw new Exception("StatusCode: "+status+" Fail to get data. url:" + url);
        }
    }


    public static String getString(String url, String cookie){
        int retry = 2;
        String result = null;
        InputStream is = null;
        HttpURLConnection connection = null;
        int attempts = 0;
        if (url.contains("currency")) {
            ++attempts;
        }

        while (++attempts <= retry) {
            try {
                URL requestUrl = new URL(url);
                connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestProperty("Cookie",cookie);// 这种方式的SetCookie好像不行
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(20000);
                is = connection.getInputStream();
                StringWriter writer = new StringWriter();
                copy(is, writer);
                result = writer.toString();
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                if (attempts == retry) {
                    System.out.println("Tried " + attempts + " times to call: " + url+"\n "+ e);
                }
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        return result;
    }

    public static void copy(InputStream input, Writer output) throws IOException {
        Reader in = new InputStreamReader(input);
        char[] buffer = new char[4096];
        long count = 0L;
        int n;
        for(boolean flag = false; -1 != (n = in.read(buffer)); count += (long)n) {
            output.write(buffer, 0, n);
        }
    }

    public static String getResponseByURL(String url, String authCookie) {
        int status = 200;
        int reRunTimes = 3;
        int runTimes = 0;
        String result = null;
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Cookie", authCookie);

        while (runTimes < reRunTimes) {
            try {
                HttpResponse response = httpclient.execute(httpget);

                String encoding = "utf-8";
                Header encodingHeader = response.getFirstHeader("Content-Encoding");
                if (encodingHeader != null) {
                    encoding = encodingHeader.getValue();
                }
                // Get result that from url
                result = EntityUtils.toString(response.getEntity(), encoding);

                status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    break;
                }
                if(status == 404){
                    break;
                }
                runTimes++;
            } catch (Exception e) {
                System.out.println("Login pricexoi failed.");
            }
        }

        httpget.releaseConnection();
        if(status == 404){
            System.out.println(url+" is 404");
            return null;
        }

        if (status == 200) {
            return result;
        } else {
            //throw new DataAccessException(ErrorEnum.PRICEXOI_NOT200,status,url);
        }
        return null;
    }

    public static void storeCookie(URI uri, String cookie){
        CookieManager manager = new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);
        HttpCookie httpCookie = new HttpCookie("Cookie: ", cookie);
        httpCookie.setMaxAge(60000);
        manager.getCookieStore().add(uri,httpCookie);
    }
}
