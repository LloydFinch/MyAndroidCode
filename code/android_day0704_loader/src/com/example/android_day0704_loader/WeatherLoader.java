package com.example.android_day0704_loader;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * project:com.example.android_day0704_loader
 * user:VennUser
 * date:2015/7/4
 */
//user-defined AsyncTaskLoader
public class WeatherLoader extends AsyncTaskLoader<String> {

	private String url;

	public WeatherLoader(Context context) {
		super(context);
	}

	public WeatherLoader(Context context, String url) {
		super(context);
		this.url = url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	//like doInBackground() in AsyncTask
	public String loadInBackground() {
		Log.d("--------->", "loadInBackground");
		String data = null;
		HttpURLConnection connection = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		if (url != null) {
			try {
				connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.setConnectTimeout(10000);//最多连接10000ms
				connection.setReadTimeout(5000);//最多读取5000ms
				connection.connect();//连接，实际上并没有打开InputStream
				//真正联网，200-300之间是正确返回
				//302、307 重定向，400-500：客户端错误，500-600：服务器错误
				if (connection.getResponseCode() == 200) {
					InputStream inputStream = connection.getInputStream();
					byte[] buff = new byte[1024];
					int length = 0;
					while ((length = inputStream.read(buff)) != -1) {
						bos.write(buff, 0, length);
					}
					data = new String(bos.toByteArray(), "utf-8");
					inputStream.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	//must realise this method
	//like onPreExecute() in AsyncTask
	protected void onStartLoading() {

		Log.d("--------->", "onStartLoading");
		//强制刷新数据,会自动在线程中调用loadInBackground()
		forceLoad();//call loadInBackground() in the method
	}

	//like onPostExecute() in AsyncTask
	protected void onStopLoading() {
		super.onStopLoading();
	}
}
