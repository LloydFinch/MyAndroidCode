package com.example.android_day0706_threadui;

import android.os.AsyncTask;
import android.util.Log;

/**
 * project:com.example.android_day0706_threadui
 * user:VennUser
 * date:2015/7/6
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {
	protected String doInBackground(String... params) {

		//get Thread ID
		long id = Thread.currentThread().getId();
		for (int i = 0; i < 10; i++) {
			Log.d("------>", "MyAsyncTask" + id + "--" + i);
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
