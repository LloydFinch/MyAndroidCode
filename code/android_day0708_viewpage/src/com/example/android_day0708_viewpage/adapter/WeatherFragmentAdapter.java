package com.example.android_day0708_viewpage.adapter;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * project:com.example.android_day0708_viewpage.adapter
 * user:VennUser
 * date:2015/7/8
 */
public class WeatherFragmentAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> fragments;

	public WeatherFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	public Fragment getItem(int i) {

		Log.d("----->life", "item" + i);
		return fragments.get(i);
	}

	public int getCount() {
		return fragments.size();
	}
}
