package com.example.android_day0712_intentview;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/12.
 */
public class MyIntentService extends IntentService{

	public MyIntentService() {
		super("");
	}

	protected void onHandleIntent(Intent intent) {
		String name= intent.getStringExtra("info");
		Log.d("----------->",name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
