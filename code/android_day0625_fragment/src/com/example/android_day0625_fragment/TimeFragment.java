package com.example.android_day0625_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * project:com.example.android_day0625_fragment
 * user:VennUser
 * date:2015/6/25
 */

public class TimeFragment extends android.support.v4.app.Fragment
{
	private static final String TAG = "FRAGMENT--------->";

	//必须要写的无参的构造函数
	//只允许创建一个构造函数,且必须为此无参数的构造函数
	public TimeFragment()
	{

	}

	//Fragment的生命周期

	//1 最早的生命周期，当Fragment被添加到Activity中时进行调用
	public void onAttach(Activity activity)
	{
		Log.d(TAG, "OnAttach");
		super.onAttach(activity);
	}

	//2 构造方法
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
	}

	//3 第三个生命周期
	//当Fragment要显示时，会调用此方法，返回值就是用来显示的布局
	//参数：布局加载器，显示位置，现场状态
	//返回值：要显示的界面
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Log.d(TAG, "onCreateView");
		//通用的布局记载方式通用3个参数的,因为返回的view会自动添加到container
		View view = inflater.inflate(R.layout.fragment_time, container, false);
		TextView txtDate = (TextView) view.findViewById(R.id.txt_date);
		if (txtDate != null)
		{
			//TODO显示日期，时间，星期
			SimpleDateFormat format = new SimpleDateFormat("E yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String dateString = format.format(date);
			txtDate.setText(dateString);
		}
		return view;
	}


	//4 第四个生命周期,onCreateView被调用后调用
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		Log.d(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	//5
	public void onStart()
	{
		Log.d(TAG, "onStart");
		super.onStart();
	}

	//6
	public void onResume()
	{
		Log.d(TAG, "onResume");
		super.onResume();
	}

	//7
	public void onPause()
	{
		Log.d(TAG, "onPause");
		super.onPause();
	}

	//8
	public void onStop()
	{
		Log.d(TAG, "onStop");
		super.onStop();
	}

	//9 对应onCreateView，在Fragment销毁之前自动调用
	public void onDestroyView()
	{
		Log.d(TAG, "onDestroyView");
		super.onDestroyView();
	}

	//10
	public void onDestroy()
	{
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}

	//11对应onAttach，彻底从Activity移除
	public void onDetach()
	{
		Log.d(TAG, "onDetach");
		super.onDetach();
	}
}
