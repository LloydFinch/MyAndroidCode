package com.example.android_day01715_homework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by VennUser on 2015/7/15.
 */
public class GuideAdapter extends FragmentPagerAdapter {
	List<Fragment> fragmentList;

	public GuideAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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
