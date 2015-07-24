package com.example.android_day0706_threadui;

import android.app.Activity;
import android.os.Bundle;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * project:com.example.android_day0706_threadui
 * user:VennUser
 * date:2015/7/6
 */
public class AsyncTestActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asynctest);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				10, // max Thread
				300, //alive time
				1,
				TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>());
		for (int i = 0; i < 200; i++) {
			//set Executor as a ThreadPool
			//new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			new MyAsyncTask().executeOnExecutor(threadPoolExecutor);
		}

	}
}