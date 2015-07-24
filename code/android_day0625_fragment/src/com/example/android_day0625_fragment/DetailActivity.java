package com.example.android_day0625_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * project:com.example.android_day0625_fragment
 * user:VennUser
 * date:2015/6/25
 */
public class DetailActivity extends FragmentActivity
{
	private TextView textView;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		textView = (TextView) this.findViewById(R.id.txt_date);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd E");
//		Date date = new Date();
//		String dateString = format.format(date);
//		textView.setText(dateString);
	}
}