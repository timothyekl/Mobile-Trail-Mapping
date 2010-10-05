package com.brousalis;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class DataLoader {

	//private HttpURLConnection _connection;
	private HttpClient _httpClient;
	private HttpPost _httpPost;
	private HttpContext _httpContext;
	
	public DataLoader(String serverAddress) {
		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		HttpConnectionParams.setSoTimeout(myParams, 10000);
		_httpClient = new DefaultHttpClient(myParams);
		_httpContext = new BasicHttpContext();
		_httpPost = new HttpPost(serverAddress);
		_httpPost.setHeader("User-Agent", HttpProtocolParams.getUserAgent(myParams));
		_httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
	}
	
	/** 
	 * Perform a POST operation
	 * @param data The data to send to the server
	 * @return A String with result data
	 */
	public String postData(String data) {
		return "";
	}
	
	/**
	 * Perform a GET operation
	 * @param data The data to send to the server
	 * @return A String with result data
	 */
	public String getData(String data) {
		return "";
	}
	
	public Trail getTrail(String trailName) {
		Trail t = new Trail(trailName);
		
		return t;
	}
	
	/**
	 * Determines whether or not we have a valid Connection to post to.
	 * @return
	 */
	public boolean validConnection() {
		HttpResponse response = null;
		try {
			response = _httpClient.execute(_httpPost, _httpContext);
			if(response == null)
			{
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
