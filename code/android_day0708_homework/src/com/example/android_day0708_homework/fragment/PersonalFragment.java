package com.example.android_day0708_homework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android_day0708_homework.R;

/**
 * project:com.example.android_day0708_homework.fragment
 * user:VennUser
 * date:2015/7/8
 */
public class PersonalFragment extends Fragment {
	public PersonalFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_personal, container, false);
	}
}