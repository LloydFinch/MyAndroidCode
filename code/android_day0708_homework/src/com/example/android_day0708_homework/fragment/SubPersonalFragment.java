package com.example.android_day0708_homework.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android_day0708_homework.R;

/**
 * Created by VennUser on 2015/7/9.
 */
public class SubPersonalFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_subpersonal, container, false);
	}
}