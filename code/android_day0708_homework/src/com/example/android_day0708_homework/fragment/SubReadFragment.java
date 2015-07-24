package com.example.android_day0708_homework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android_day0708_homework.R;

/**
 * Created by VennUser on 2015/7/9.
 */
public class SubReadFragment extends Fragment {
	public SubReadFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_subread, container, false);
		TextView textView = (TextView) view.findViewById(R.id.text_sub_read);
		textView.setText(getArguments().getString("info"));
		return view;
	}
}