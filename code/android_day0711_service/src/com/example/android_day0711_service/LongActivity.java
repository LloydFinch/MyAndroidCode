package com.example.android_day0711_service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by VennUser on 2015/7/11.
 */

public class LongActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_long);
	}

	public void btnLong_onclick(View view) {
		Intent intent = new Intent(this, LongService.class);
		startService(intent);
	}

	public void btnSerial_onclick(View view) {
		Intent intent = new Intent(this, SerialService.class);
		intent.putExtra("control", "play");
		startService(intent);
	}

	public void btnBaidu_onclick(View view) {
		Intent intent1 = new Intent();
		intent1.setAction(Intent.ACTION_VIEW);
		intent1.setData(Uri.parse("http://baidu.com"));
		startActivity(intent1);
	}
}