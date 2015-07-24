package com.example.android_day0708_homework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * project:com.example.android_day0708_homework.adapter
 * user:VennUser
 * date:2015/7/8
 */
public class SubAdapter extends FragmentPagerAdapter {
	List<Fragment> fragmentList;

	public SubAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}

	public Fragment getItem(int i) {
		return fragmentList.get(i);
	}

	public int getCount() {
		return fragmentList.size();
	}
}
