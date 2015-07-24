package com.example.Android_Review.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.example.Android_Review.R;
import com.example.Android_Review.fragment.ActivityFragment;

/**
 * Created by VennUser on 2015/7/23.
 */
public class FragmentActivity extends android.support.v4.app.FragmentActivity {

	private ActivityFragment fragment;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		fragment = new ActivityFragment();

		FragmentManager manager = getSupportFragmentManager();
		//manager.popBackStack("add",0);

		FragmentTransaction transaction = manager.beginTransaction();

		transaction.add(R.id.layout_frame, fragment);
		transaction.addToBackStack("add");
		transaction.commit();
	}
}