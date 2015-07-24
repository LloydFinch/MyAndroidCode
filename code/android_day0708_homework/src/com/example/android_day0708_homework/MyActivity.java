package com.example.android_day0708_homework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import com.example.android_day0708_homework.fragment.*;

public class MyActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

	private NewsFragment newsFragment;
	private ReadFragment readFragment;
	private LookFragment lookFragment;
	private DiscoverFragment discoverFragment;
	private PersonalFragment personalFragment;

	private RadioGroup radioGroup;

	private FragmentManager manager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		NewsFragment.getContext(this);

		newsFragment = new NewsFragment();
		readFragment = new ReadFragment();
		lookFragment = new LookFragment();
		discoverFragment = new DiscoverFragment();
		personalFragment = new PersonalFragment();

		radioGroup = (RadioGroup) this.findViewById(R.id.news_tab);
		radioGroup.check(R.id.news_tab_news);
		radioGroup.setOnCheckedChangeListener(this);

		manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.frame_container, newsFragment);
		transaction.commit();
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction transaction = manager.beginTransaction();
		Fragment fragment = newsFragment == null ? new NewsFragment() : newsFragment;
		switch (checkedId) {
			case R.id.news_tab_news:
				fragment = newsFragment == null ? new NewsFragment() : newsFragment;
				break;
			case R.id.news_tab_read:
				fragment = readFragment == null ? new ReadFragment() : readFragment;
				break;
			case R.id.news_tab_look:
				fragment = lookFragment == null ? new LookFragment() : lookFragment;
				break;
			case R.id.news_tab_discover:
				fragment = discoverFragment == null ? new DiscoverFragment() : discoverFragment;
				break;
			case R.id.news_tab_personal:
				fragment = personalFragment == null ? new PersonalFragment() : personalFragment;
				break;
		}
		transaction.replace(R.id.frame_container, fragment);
		transaction.commit();
	}
}
