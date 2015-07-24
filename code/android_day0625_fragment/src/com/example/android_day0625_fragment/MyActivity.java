package com.example.android_day0625_fragment;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

//创建Fragment
public class MyActivity extends FragmentActivity
{
	private static final String TAG = "ACTIVITY";
	/**
	 * Called when the activity is first created.
	 */
	private TextView textView;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d(TAG, "onCreate");
		//显示日期和星期
		textView = (TextView) this.findViewById(R.id.txt_date);
//		SimpleDateFormat format = new SimpleDateFormat("E yyyy-MM-dd HH:mm:ss");
//		Date date = new Date();
//		String dateString = format.format(date);
//		textView.setText(dateString);
	}


	public void btndrop_click(View view)
	{
		Intent intent = new Intent(this, DetailActivity.class);
		startActivity(intent);
	}

	protected void onRestart()
	{
		Log.d(TAG, "---->onRestart");
		super.onRestart();
	}

	public void onStart()
	{
		Log.d(TAG, "---->onStart");
		super.onStart();
	}

	public void onResume()
	{
		Log.d(TAG, "---->onResume");
		super.onResume();
	}

	public void onPause()
	{
		Log.d(TAG, "---->onPause");
		super.onPause();
	}

	public void onStop()
	{
		Log.d(TAG, "---->onStop");
		super.onStop();
	}

	public void onDestroy()
	{
		Log.d(TAG, "---->onDestroy");
		super.onDestroy();
	}
}
