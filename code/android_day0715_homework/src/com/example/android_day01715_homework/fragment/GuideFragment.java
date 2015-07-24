package com.example.android_day01715_homework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android_day01715_homework.R;

/**
 * Created by VennUser on 2015/7/15.
 */
public class GuideFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_firstguide, container, false);
		TextView textView = (TextView) view.findViewById(R.id.text_guide);
		textView.setText(getArguments().getString("introduce"));
		return view;
	}
}