package com.example.Android_Review.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by VennUser on 2015/7/23.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragmentList;

	public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}

	public Fragment getItem(int i) {
		return fragmentList.get(i);
	}

	public void setFragmentList(List<Fragment> fragmentList) {
		this.fragmentList = fragmentList;
	}

	public int getCount() {
		return fragmentList.size();
	}
}
