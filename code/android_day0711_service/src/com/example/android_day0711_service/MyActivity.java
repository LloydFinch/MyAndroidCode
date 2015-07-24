package com.example.android_day0711_service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//通过上下文 启动服务
		Intent intent = new Intent(this, MusicService.class);
//		for (int i = 0; i < 5; i++) {
		intent.putExtra("value", 0);
//			startService(intent);
//		}
		startService(intent);

	}

	public void btn_play(View view) {
		//给当前服务传递参数
		Intent intent = new Intent(this, MusicService.class);
		intent.putExtra("control", "play");
		startService(intent);
	}

	public void btn_pause(View view) {
		Intent intent = new Intent(this, MusicService.class);
		intent.putExtra("control", "pause");
		startService(intent);

	}

	public void btn_stop(View view) {
		Intent intent = new Intent(this, MusicService.class);
		intent.putExtra("control", "stop");
		startService(intent);
	}


	public void btn_stop_ori(View view) {

		//将所有的此服务停止
		Intent intent = new Intent(this, MusicService.class);

		//会自动回调onDestroy()方法，终止此所有此服务(不论是否粘性)
		stopService(intent);

		Log.d("-------->","stopService");
	}
}
