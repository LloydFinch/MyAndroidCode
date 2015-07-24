package com.example.android_day0713_teacher_download;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.android_day0713_teacher_download.service.DownLoadService;
import com.example.android_day0713_teacher_download.service.DownLoadTask;

import java.security.PrivilegedAction;

public class MyActivity extends Activity implements ServiceConnection, Runnable {
	private EditText text;
	private DownLoadService.DownLoadBinder downLoadBinder;
	private ProgressBar progressBar;
	private Thread thread;
	private boolean isRunning;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		text = (EditText) this.findViewById(R.id.txt_url);
		progressBar = (ProgressBar) this.findViewById(R.id.progress);

		Intent intent = new Intent(this, DownLoadService.class);
		startService(intent);
	}

	protected void onResume() {
		super.onResume();
		Intent intent = new Intent(this, DownLoadService.class);
		bindService(intent, this, BIND_AUTO_CREATE);
	}

	//当singleTask(single系列)匹配，startActivity()就不会创建新的实例,而是调用setIntent();
	protected void onNewIntent(Intent intent) {

		super.onNewIntent(intent);

		setIntent(intent);

		Uri data = intent.getData();
		if (data != null) {

			//TODO 下载
			String path = data.toString();
			text.setText(path);
		}
	}

	public void btnDownLoad_onclick(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.baidu.com/"));
		startActivity(intent);
	}

	protected void onPause() {
		unbindService(this);
		super.onPause();
	}

	public void btnLoading_onclick(View view) {

		String url = text.getText().toString().trim();
		if (url != null && url.startsWith("http://")) {
			downLoadBinder.reqDownload(url);
		}
	}

	public void onServiceConnected(ComponentName name, IBinder service) {

		downLoadBinder = (DownLoadService.DownLoadBinder) service;
		isRunning = true;
		thread = new Thread(this);
	}

	public void onServiceDisconnected(ComponentName name) {

		isRunning = false;
		downLoadBinder = null;
	}

	public void run() {
		DownLoadTask task = new DownLoadTask();
		long total = task.getCurrent();
		long progress = task.getMax();
		progressBar.setMax((int) total);
		progressBar.setProgress((int) progress);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
