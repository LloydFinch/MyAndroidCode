package com.example.android_day0707_actionbar.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android_day0707_actionbar.R;

/**
 * project:com.example.android_day0707_actionbar.fragment
 * user:VennUser
 * date:2015/7/7
 */
public class ContactsFragment extends Fragment {
	public ContactsFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_contacts, container, false);
	}
}