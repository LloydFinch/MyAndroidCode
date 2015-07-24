package com.example.android_day0711_service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/11.
 */

//长时间服务
//1 是否使用startService()
//2 Activity退出后，服务是否退出
//3 是否要传参数
public class LongService extends Service implements Runnable {

	//长时间的操作在线程中执行，不要在服务中
	private Thread thread;

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {
		super.onCreate();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.d("-------->", "onStartCommand--start!");
		if (thread == null || !thread.isAlive()) {
			thread = new Thread(this);
		}
		thread.start();

		//TODO 如果需要传递给线程参数，如何实现？
		return super.onStartCommand(intent, flags, startId);
	}

	public void onDestroy() {
		super.onDestroy();
	}

	public void run() {
		for (int i = 1; i <= 300; i++) {
			try {
				Thread.sleep(1000);
				Log.d("-------->", "onStartCommand" + "--" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
