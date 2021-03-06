package com.xavey.android.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.xavey.android.ApplicationValues;
import com.xavey.android.ApplicationValues.LOGIN_TYPE;
import com.xavey.android.model.RequestMethod;
import com.xavey.android.model.RestClient;

public class ConnectionDetector {

	private Context _context;
	XaveyProperties xaveyProperties;
	XaveyUtils xUtils;
	
	
	public ConnectionDetector(Context context) {
		this._context = context;
		xaveyProperties = new XaveyProperties();
		xUtils = new XaveyUtils(context);
	}

	public boolean isConnectingToInternet() {

		ConnectivityManager connectivity = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null && ApplicationValues.CURRENT_LOGIN_MODE!=LOGIN_TYPE.DEMO_LOGIN) {
//			NetworkInfo[] info = connectivity.getAllNetworkInfo();
//			if (info != null)
//				for (int i = 0; i < info.length; i++)
//					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
//						return true;
//					}

			String xaveyURL = xaveyProperties.getCurrentlyPointingURL();
			int timeout = 7000;

			if(isMyURLReachable(xaveyURL, timeout)){
				return true;
			}
		}
		return false;
	}

	public boolean isURLReachable(String url_) {
		ConnectivityManager cm = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) {
			try {
				URL url = new URL("http://" + url_); // Change to
														// "http://google.com"
														// for www test.
				HttpURLConnection urlc = (HttpURLConnection) url
						.openConnection();
				urlc.setConnectTimeout(10 * 1000); // 10 s.
				urlc.connect();
				if (urlc.getResponseCode() == 200) { // 200 = "OK" code (http
														// connection is fine).
					Log.wtf("Connection", "Success !");
					return true;
				} else {
					return false;
				}
			} catch (MalformedURLException e1) {
				return false;
			} catch (IOException e) {
				return false;
			}
		}
		return false;
	}

	public boolean isConnected() { // by chu myat moe
		Boolean online = false;
		try {

			ConnectivityManager cm = (ConnectivityManager) _context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();

			if (netInfo != null && netInfo.isConnectedOrConnecting()
					&& netInfo.isAvailable()) {
				// Network is available but check if we can get access from the
				// network.
				// URL url = new URL("http://www.bstmobile.com/catalogue/");
				URL url = new URL("http://192.168.60.101/xavey-web-app-copy/api/get/forms/worker");
				/*
				 * URL url = new URL(
				 * "http://192.168.3.140/HealthDirectory/HealthDirectory.svc/");
				 */
				HttpURLConnection urlc = (HttpURLConnection) url
						.openConnection();
				urlc.setRequestProperty("xavey", "close");
				urlc.setConnectTimeout(4000); // Timeout 2 seconds.
				urlc.connect();

				if (urlc.getResponseCode() == 200) // Successful response.
				{
					// Log.d("Connected", "Finished Connect");
					online = true;
				} else {
					online = false;
				}
			}
		} catch (Exception e) {
			online = false;
		}
		return online;
	}

	// 9.7.2014
	public boolean isMyURLReachable(String url, int timeOut){
		boolean reachable = false;
        if(url.startsWith("api")){
            url = url+":3000/";
        }
		try {
		    URL url_ = new URL("http://"+url);
		    HttpURLConnection urlc = (HttpURLConnection) url_.openConnection();
		    urlc.setConnectTimeout(timeOut); // mTimeout is in seconds
		    urlc.connect();
            int responseCode = urlc.getResponseCode();
		    if (responseCode == 200) {
		        reachable = true;
		    }
		} catch (Exception e1) {
		    e1.printStackTrace();
		    return false;
		}
		return reachable;
	}

	//25.7.2014
	public boolean isConnectingWithMyServer(){
		ConnectionCheckTask checkTask = new ConnectionCheckTask();
		checkTask.execute("https://www.google.com");
		try {
			RestClient c = checkTask.get();
			if(c.getResponseCode()==200)
				return true;
			else
				return false;
		} catch (InterruptedException e) {
			return false;
		} catch (ExecutionException e) {
			return false;
		}
		
	}
	
	private class ConnectionCheckTask extends AsyncTask<String, Void, RestClient> {

		

		protected void onPreExecute() {
			
		}

		@Override
		protected RestClient doInBackground(String... params) {
			String url = params[0];
			int responseCode = 0;
			RestClient c = new RestClient(url);
			try {
				c.Execute(RequestMethod.GET);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
		}

		@Override
		protected void onPostExecute(RestClient c) {
			
		}
	}
	
	
	
}
