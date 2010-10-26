package com.brousalis;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

public class BetaChecker {

	public static Boolean isUpToDate(Boolean isInBeta, String betaCheckUrl) {
		if (isInBeta) {
			return getHTTPData(betaCheckUrl).equals("up_to_date");
		}
		return true;
	}
	
	public static void registerUser(String registerUrl, String deviceID) {
		getHTTPData(registerUrl + deviceID);
	}
	
	public static Boolean checkUser(String registerUrl, String deviceID) {
		return getHTTPData(registerUrl + deviceID).equals("registered");
	}
	
	private static String getHTTPData(String url) {
		URLConnection connection;
		String str = "";
		try {
			connection = new URL(url).openConnection();
			connection.connect();
			InputStream is = connection.getInputStream();
			StringBuffer buffer = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = is.read(b)) != -1;) {
				buffer.append(new String(b, 0, n));
			}
			str = buffer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
