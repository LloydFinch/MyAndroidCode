package com.example.android_day0711_homework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.example.android_day0711_homework.tool.DownLoad;
import com.example.android_day0711_homework.tool.PutFile;

import java.io.File;
import java.io.InputStream;

/**
 * Created by VennUser on 2015/7/11.
 */
public class DownLoadService extends Service {

	private Thread thread;

	public void onCreate() {
		super.onCreate();
	}

	public IBinder onBind(Intent intent) {
		return new DownLoadBinder();
	}

	public class DownLoadBinder extends Binder implements Runnable {

		private String path;

		public void download() {
			thread.start();
		}

		public void init(String path) {
			thread = new Thread(this);
			this.path = path;
		}

		public void run() {
			try {
				byte[] data = DownLoad.getInputStream(path);
				PutFile.CreateFile(data, getFilesDir().getParent());
			} catch (Exception e) {
				Log.d("------------>", "下载出错");
			}
		}
	}

	public void onDestroy() {
		super.onDestroy();
	}
}
