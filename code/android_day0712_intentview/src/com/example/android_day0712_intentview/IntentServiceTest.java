package com.example.android_day0712_intentview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/12.
 */
public class IntentServiceTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intentservice);

		Intent intent = new Intent(this, MyIntentService.class);
		intent.putExtra("info", "intent");
		for (int i = 0; i < 10; i++) {
			startService(intent);
		}

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					Log.d("---------->", "thread");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}