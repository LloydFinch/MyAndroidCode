package com.example.android_day0630_intentaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void btn_onclick(View view) {

		//隐式意图：可以找到系统中支持的
		Intent intent=new Intent(Intent.ACTION_VIEW);

		//使用setData()设置ACTION_VIEW需要的参数

		//加载网址形式的data
		//intent.setData(Uri.parse("http://www.baidu.com.cn"));

		//设置电话格式的data
		//intent.setData(Uri.parse("tel:10086"));

		//设置短信格式的data
		intent.setData(Uri.parse("sms:18236586285"));
		startActivity(intent);

	}
}
