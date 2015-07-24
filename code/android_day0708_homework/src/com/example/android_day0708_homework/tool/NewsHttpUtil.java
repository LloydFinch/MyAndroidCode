package com.example.android_day0708_homework.tool;

/**
 * project:com.example.android_day0626_homework.tool
 * user:VennUser
 * date:2015/6/26
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewsHttpUtil
{
	public static byte[] getDatas(String path) throws MalformedURLException, IOException
	{
		HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(10000);
		connection.setDoInput(true);
		if (connection.getResponseCode() == 200)
		{
			return changed(connection.getInputStream());
		}
		return null;
	}

	private static byte[] changed(InputStream in) throws IOException
	{
		byte[] data = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int length = 0;
		while ((length = in.read(data)) != -1)
		{
			baos.write(data, 0, length);
		}
		return baos.toByteArray();
	}
}
