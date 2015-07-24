package com.example.android_venn_day0625_menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * project:com.example.android_venn_day0625_menu
 * user:VennUser
 * date:2015/6/26
 */
public class MyFragment extends android.support.v4.app.Fragment
{
	public MyFragment() {}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view=inflater.inflate(R.layout.main,container,false);
		return view;
	}
}
