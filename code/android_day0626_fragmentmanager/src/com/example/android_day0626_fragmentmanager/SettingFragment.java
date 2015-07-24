package com.example.android_day0626_fragmentmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * project:com.example.android_day0626_fragmentmanager
 * user:VennUser
 * date:2015/6/26
 */
public class SettingFragment extends android.support.v4.app.Fragment
{
	public SettingFragment()
	{

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_setting, container, false);
	}
}