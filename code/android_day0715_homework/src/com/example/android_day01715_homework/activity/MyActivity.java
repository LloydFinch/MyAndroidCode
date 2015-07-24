package com.example.android_day01715_homework.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.example.android_day01715_homework.R;

public class MyActivity extends Activity implements Runnable {

	private static final String LOGIN_TIME = "loginTime";

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (getLoginTime() == 0) {
				Intent intent = new Intent(MyActivity.this, GuideActivity.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(MyActivity.this, CityListActivity.class);
				startActivity(intent);
			}
			recordLoginTime();

		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		Message message = new Message();
		message.what = 222;

		handler.sendMessage(message);
	}

	//判断是否是第一次登录
	private int getLoginTime() {
		SharedPreferences sharedPreferences = getSharedPreferences("loginTimes", Context.MODE_PRIVATE);
		int loginTime = sharedPreferences.getInt(LOGIN_TIME, 0);

		return loginTime;
	}

	private void recordLoginTime() {
		SharedPreferences sharedPreferences = getSharedPreferences("loginTimes", Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = sharedPreferences.edit();
		edit.putInt(LOGIN_TIME, 1);
		edit.commit();
	}
}
