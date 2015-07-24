package com.example.android_day0708_viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * project:com.example.android_day0708_viewpage
 * user:VennUser
 * date:2015/7/8
 */
public class MyFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_my, container, false);
		TextView textView = (TextView) view.findViewById(R.id.text_news);
		textView.setText("新闻内容:" + getArguments().getString("position"));

		return view;
	}
}