package com.example.android_day0626_fragmentmanager.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.android_day0626_fragmentmanager.R;

/**
 * project:com.example.android_day0626_fragmentmanager.fragment
 * user:VennUser
 * date:2015/6/26
 */
public class NewsFragment extends Fragment
{
	private static String TAG = "------>";
	;

	public NewsFragment()
	{
		TAG += getActivity().toString();
	}

	//1
	public void onAttach(Activity activity)
	{
		Log.d(TAG, "------>onAttach");
		super.onAttach(activity);
	}

	//2
	public void onCreate(Bundle savedInstanceState)
	{
		Log.d(TAG, "------>onCreate");
		super.onCreate(savedInstanceState);
	}

	//3
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Log.d(TAG, "------>onCreateView");
		return inflater.inflate(R.layout.news_fragment, container, false);
	}

	//4

	public void onActivityCreated(Bundle savedInstanceState)
	{
		Log.d(TAG, "------>onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}


	//5
	public void onStart()
	{
		Log.d(TAG, "------>onStart");
		super.onStart();
	}

	//6
	public void onResume()
	{
		Log.d(TAG, "------>onResume");
		super.onResume();
	}

	//7
	public void onPause()
	{
		Log.d(TAG, "------>onPause");
		super.onPause();
	}

	//8
	public void onStop()
	{
		Log.d(TAG, "------>onStop");
		super.onStop();
	}

	//9
	public void onDestroyView()
	{
		Log.d(TAG, "------>onDestroyView");
		super.onDestroyView();
	}

	//10
	public void onDestroy()
	{
		Log.d(TAG, "------>onDestroy");
		super.onDestroy();
	}

	//11
	public void onDetach()
	{
		Log.d(TAG, "------>onDetach");
		super.onDetach();
	}
}