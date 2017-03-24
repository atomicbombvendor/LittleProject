package PriceXOI;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class XOIAccess {
	private String loginUrl;
	private String userName;
	private String password;

	private String authCookie;

	private Date authCookieExpired = null;

	public XOIAccess(String loginUrl, String username, String password) {
		this.loginUrl = loginUrl;
		this.userName = username;
		this.password = password;
	}

	private void doLogin() throws Exception {
		if (!isExpired())
			return;

		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(this.userName, this.password));

		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider)
				.build();
		HttpGet httpGet = new HttpGet(this.loginUrl);
		HttpResponse response = httpClient.execute(httpGet);
		Header cookieHeader = response.getFirstHeader("Set-Cookie");
		if (cookieHeader != null) {
			this.authCookie = cookieHeader.getValue();

			int start = this.authCookie.indexOf("expires=");
			if (start >= 0) {
				String expires = this.authCookie.substring(start);
				start = expires.indexOf("=");
				int end = expires.indexOf(";");
				if (start >= 0 && end > 0 && end > start + 1) {
					expires = expires.substring(start + 1, end).trim();
					DateFormat df = new SimpleDateFormat("E, dd-MMM-yyyy HH:mm:ss z");
					this.authCookieExpired = df.parse(expires);
				}
			}
			if (this.authCookieExpired == null) {
				throw new Exception("invalid auth cookie:" + this.authCookie);
			}
		} else {
			throw new Exception("Login exception. LoginUrl:" + this.loginUrl + ",username:" + this.userName);
		}
	}

	public String getXmlData(String url) throws Exception {
		doLogin();
		
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Cookie", this.authCookie);
		try {
			HttpResponse response = httpclient.execute(httpget);
			String encoding = "utf-8";
			Header encodingHeader = response.getFirstHeader("Content-Encoding");
			if(encodingHeader!=null){
				encoding = encodingHeader.getValue();
			}

			//Data From XOI
			String xml = EntityUtils.toString(response.getEntity(),encoding);
			
			Header errorCodeHeader = response.getFirstHeader("X-XOI-ErrorCode");
			if(errorCodeHeader!=null){
				String errorCode = errorCodeHeader.getValue();
				if(errorCode.trim()!="40401"){
					throw new Exception("Fail to get data. url:" + url +",error: \n" + xml);
				}else{
					return "";
				}
			}
		
			return xml;
		} finally {
			httpget.releaseConnection();
		}
	}

	private Boolean isExpired() throws Exception {
		if (this.authCookieExpired == null)
			return true;
		Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("GTM"));
		utcCalendar.set(Calendar.MINUTE, 5);
		Date utcNow = utcCalendar.getTime();
		if (this.authCookieExpired.after(utcNow))
			return false;

		return true;
	}

	public static void main(String[] args) throws Exception {
		
	}
}
