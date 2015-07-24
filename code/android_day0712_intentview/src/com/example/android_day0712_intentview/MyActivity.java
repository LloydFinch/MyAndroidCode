package com.example.android_day0712_intentview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://a.hiphotos.baidu.com/image/w%3D230/sign=211c120b3adbb6fd255be2253925aba6/b8014a90f603738dd9b1aad2b01bb051f919ec43.jpg"));
		startActivity(intent);
	}
}
