package com.example.android_day0625_homework;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.Menu;
import android.view.MenuInflater;

public class MyActivity extends FragmentActivity
{
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		MyFragment.getContext(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
}
