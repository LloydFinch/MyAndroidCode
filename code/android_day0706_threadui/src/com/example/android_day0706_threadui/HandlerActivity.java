package com.example.android_day0706_threadui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * project:com.example.android_day0706_threadui
 * user:VennUser
 * date:2015/7/6
 */
public class HandlerActivity extends Activity implements Runnable {
	private Thread downloadThread;
	private ImageView imageView;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 222) {
				imageView.setImageBitmap((Bitmap) msg.obj);
			}
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler);
		imageView = (ImageView) this.findViewById(R.id.image_photo);
	}

	public void btnDownload_onclick(View view) {
		if (downloadThread == null || !downloadThread.isAlive()) {
			downloadThread = new Thread(this);
			downloadThread.start();
		} else {
			downloadThread.start();
		}
	}

	public void run() {
		Bitmap bitmap = null;
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL("http://img2.3lian.com/2014/c7/13/d/1.jpg");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(10000);
			connection.setDoInput(true);
			if (connection.getResponseCode() == 200) {
				InputStream inputStream = connection.getInputStream();
				bitmap = BitmapFactory.decodeStream(inputStream);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Message message = new Message();
		message.what = 222;
		message.obj = bitmap;
		handler.sendMessage(message);
	}
}