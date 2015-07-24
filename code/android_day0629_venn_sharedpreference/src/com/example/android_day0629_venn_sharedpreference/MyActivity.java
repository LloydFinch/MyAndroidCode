package com.example.android_day0629_venn_sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//1 存储
		//通过上下文获取SharedPreferences,第一个参数为存储的文件名
		SharedPreferences settings = getSharedPreferences("settings", Context.MODE_PRIVATE);
		//通过SharedPreferences的Editor获取编辑器
		SharedPreferences.Editor editor = settings.edit();

		//通过编辑器存放配置信息，格式为key-value
		editor.putString("properies", "value");

		//提交数据
		editor.commit();


		//2 获取
		//通过上下文获取SharedPreferences,注意与要上面的第一个参数一样
		SharedPreferences settings2 = getSharedPreferences("settings", Context.MODE_PRIVATE);

		//获取SharedPreferences内部存储的配置信息,第一个参数为key,第二个参数为找不到时的默认值
		String value = settings2.getString("properties", "null");
	}
}
