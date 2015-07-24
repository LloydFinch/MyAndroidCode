package com.example.android_day0626_homework.tool;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * project:com.example.android_day0626_homework.tool
 * user:VennUser
 * date:2015/6/27
 */
public class MarkHttpUtil
{
	public static String getDatas(String path) throws MalformedURLException, IOException
	{
		//		HttpGet get = new HttpGet(path);
//		HttpClient client=new DefaultHttpClient();
//
//		HttpResponse response=client.execute(get);
//		if(response.getStatusLine().getStatusCode()==200){
//			return changed(response.getEntity().getContent());
//		}

		HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(100000);
		connection.setDoInput(true);
		if (connection.getResponseCode() == 200)
		{
			return changed(connection.getInputStream());
		}
		return null;
	}

	private static String changed(InputStream in) throws IOException
	{
		byte[] data = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int length = 0;
		while ((length = in.read(data)) != -1)
		{
			baos.write(data, 0, length);
		}
		return new String(baos.toByteArray(), "utf-8");
	}
}
