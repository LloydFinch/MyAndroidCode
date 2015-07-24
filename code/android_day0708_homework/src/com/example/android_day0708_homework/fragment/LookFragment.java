package com.example.android_day0708_homework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.example.android_day0708_homework.R;
import com.example.android_day0708_homework.adapter.SubAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0708_homework.fragment
 * user:VennUser
 * date:2015/7/8
 */
public class LookFragment extends Fragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
	private ViewPager viewPager;
	private RadioGroup radioGroup;

	public LookFragment() {
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_look, container, false);

		List<Fragment> fragmentList = new ArrayList<Fragment>();
		for (int i = 0; i < 2; i++) {
			SubLookFragment subLookFragment = new SubLookFragment();
			Bundle bundle = new Bundle();
			bundle.putString("info", "视听页面:" + i);
			subLookFragment.setArguments(bundle);
			fragmentList.add(subLookFragment);
		}
		viewPager = (ViewPager) view.findViewById(R.id.pager_look);
		viewPager.setAdapter(new SubAdapter(getChildFragmentManager(), fragmentList));
		viewPager.setOnPageChangeListener(this);

		radioGroup = (RadioGroup) view.findViewById(R.id.news_look_tab);
		radioGroup.check(R.id.news_look_tab_video);
		radioGroup.setOnCheckedChangeListener(this);

		return view;
	}

	public void onPageScrolled(int i, float v, int i1) {

	}

	public void onPageSelected(int position) {
		int id = R.id.news_look_tab_video;
		switch (position) {
			case 0:
				id = R.id.news_look_tab_video;
				break;
			case 1:
				id = R.id.news_look_tab_radio;
				break;
		}
		radioGroup.check(id);
	}

	public void onPageScrollStateChanged(int i) {

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int index = 0;
		switch (checkedId) {
			case R.id.news_look_tab_video:
				index = 0;
				break;
			case R.id.news_look_tab_radio:
				index = 1;
				break;
		}
		viewPager.setCurrentItem(index);
	}
}