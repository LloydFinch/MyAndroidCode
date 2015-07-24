package com.example.android_day0708_viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * project:com.example.android_day0708_viewpage
 * user:VennUser
 * date:2015/7/8
 */
public class WeatherFragment extends Fragment {
	private String position;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_weather, container, false);
		TextView textView = (TextView) view.findViewById(R.id.text_state);
		position = getArguments().getString("position");
		textView.setText("状态:" + position);
		return view;
	}

	public void onCreate(Bundle savedInstanceState) {
		Log.d("----->life", "onCreate--" + getArguments().getString("position"));
		super.onCreate(savedInstanceState);
	}

	public void onResume() {
		Log.d("----->life", "onResume--" + position);
		super.onResume();
	}

	public void onDestroyView() {
		Log.d("----->life", "onDestroyView--" + position);
		super.onDestroyView();
	}

	public void onDestroy() {
		Log.d("----->life", "onDestroy--" + position);
		super.onDestroy();
	}

	public void onStart() {
		Log.d("----->life", "onStart--" + position);
		super.onStart();
	}

	public void onPause() {
		Log.d("----->life", "onPause--" + position);
		super.onPause();
	}

	public void onStop() {
		Log.d("----->life", "onStop--" + position);
		super.onStop();
	}
}