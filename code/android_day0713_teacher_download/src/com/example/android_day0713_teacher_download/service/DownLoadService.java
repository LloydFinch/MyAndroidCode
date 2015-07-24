package com.example.android_day0713_teacher_download.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by VennUser on 2015/7/13.
 */
public class DownLoadService extends Service implements Runnable {

	private Thread thread;
	private boolean isRunning;
	private Queue<DownLoadTask> downLoadTasks;
	private DownLoadTask currentTask;

	public void onCreate() {
		super.onCreate();

		downLoadTasks = new LinkedBlockingQueue<>();

		thread = new Thread(this);

		thread.start();
	}

	public void onDestroy() {

		isRunning = false;
		thread = null;

		super.onDestroy();
	}

	public IBinder onBind(Intent intent) {
		return new DownLoadBinder();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {

		String url = intent.getStringExtra("downloadUrl");
		if (url != null) {

			//TODO 开始下载

			DownLoadTask task = new DownLoadTask();
			task.setUrl(url);

			downLoadTasks.add(task);
		}

		return super.onStartCommand(intent, flags, startId);
	}

	public void run() {

		isRunning = true;

		File fileDir = getFilesDir();
		File file = new File(fileDir, "download");

		FileOutputStream fo = null;

		while (isRunning) {
			currentTask = downLoadTasks.poll();
			long total = 0;
			long progress = 0;
			if (currentTask != null) {
				String path = currentTask.getUrl();
				InputStream in = null;
				try {

					fo = new FileOutputStream(file);

					HttpGet get = new HttpGet(path);
					HttpClient client = new DefaultHttpClient();
					HttpResponse response = client.execute(get);
					if (response.getStatusLine().getStatusCode() == 200) {
						total = response.getEntity().getContentLength();
						in = response.getEntity().getContent();
						int len = 0;
						byte[] buffer = new byte[1024];
						while ((len = in.read(buffer)) != -1) {
							progress += len;
							fo.write(buffer, 0, len);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fo.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	//绑定服务

	public class DownLoadBinder extends Binder {

		public void reqDownload(String url) {

			if (url != null) {
				DownLoadTask task = new DownLoadTask();
				task.setUrl(url);

				downLoadTasks.add(task);
			}
		}

		public DownLoadTask getCurrent() {
			return currentTask;
		}
	}
}
