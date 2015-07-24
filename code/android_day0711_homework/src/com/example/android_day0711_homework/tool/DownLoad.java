package com.example.android_day0711_homework.tool;

import android.widget.ProgressBar;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URI;

/**
 * Created by VennUser on 2015/7/11.
 */
public class DownLoad {
	public static long total;
	public static long progress;

	public static byte[] getInputStream(String path) {
		InputStream in = null;
		HttpGet get = new HttpGet(path);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(get);
			total = response.getEntity().getContentLength();
			if (response.getStatusLine().getStatusCode() == 200) {
				byte[] data = new byte[1024];
				int len = 0;
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				in = response.getEntity().getContent();
				while ((len = in.read(data)) != -1) {
					progress += len;
					Thread.sleep(300);
					byteArrayOutputStream.write(data, 0, len);
				}
				return byteArrayOutputStream.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long getTotal() {
		return total;
	}

	public static long getProgress() {
		return progress;
	}
}
