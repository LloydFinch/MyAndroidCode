package com.example.android_day0710_homework;

import adapter.FragmentAdapter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import contactsTool.ContactsTool;
import fragment.*;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
	private ViewPager viewPager;
	private RadioGroup radioGroup;
	private FragmentPagerAdapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ContactsTool.addBlack(this, null);

		ContactsFragment.getContext(this);
		MessageFragment.getContext(this);
		MesDetailFragment.getContext(this);
		BlackListFragment.getContext(this);
		InterceptFragment.getContext(this);

		viewPager = (ViewPager) this.findViewById(R.id.pager_content);

		List<Fragment> fragmentList = new ArrayList<Fragment>();
		fragmentList.add(new ContactsFragment());
		fragmentList.add(new MessageFragment());
		fragmentList.add(new BlackListFragment());
		fragmentList.add(new InterceptFragment());

		adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);

		radioGroup = (RadioGroup) this.findViewById(R.id.sms_tab_bar);
		radioGroup.check(R.id.tab_contacts);
		radioGroup.setOnCheckedChangeListener(this);
	}

	public void onPageScrolled(int i, float v, int i1) {

	}

	public void onPageSelected(int position) {
		int id = R.id.tab_contacts;
		switch (position) {
			case 0:
				id = R.id.tab_contacts;
				break;
			case 1:
				id = R.id.tab_sms;
				break;
			case 2:
				id = R.id.tab_black;
				break;
			case 3:
				id = R.id.tab_intercept;
				break;
		}
		radioGroup.check(id);
	}

	public void onPageScrollStateChanged(int i) {

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int index = 0;
		switch (checkedId) {
			case R.id.tab_contacts:
				index = 0;
				break;
			case R.id.tab_sms:
				index = 1;
				break;
			case R.id.tab_black:
				index = 2;
				break;
			case R.id.tab_intercept:
				index = 3;
				break;
		}
		viewPager.setCurrentItem(index);
	}
}
