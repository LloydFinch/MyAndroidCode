package com.example.android_day0708_homework.fragment;

import android.content.Context;
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
public class NewsFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
	private ViewPager viewPager;
	private RadioGroup radioGroup;
	private static Context context;

	public NewsFragment() {
	}

	public static void getContext(Context con) {
		context = con;
		SubNewsFragment.getContext(context);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, container, false);
		viewPager = (ViewPager) view.findViewById(R.id.pager_news);
		List<Fragment> fragmentList = new ArrayList<Fragment>();

		for (int i = 0; i < 5; i++) {
			SubNewsFragment subNewsFragment = new SubNewsFragment();
			fragmentList.add(subNewsFragment);
		}
		viewPager.setAdapter(new SubAdapter(getChildFragmentManager(), fragmentList));
		viewPager.setOnPageChangeListener(this);

		radioGroup = (RadioGroup) view.findViewById(R.id.news_news_tab);
		radioGroup.check(R.id.news_news_tab_head);
		radioGroup.setOnCheckedChangeListener(this);

		return view;
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int index = 0;
		switch (checkedId) {
			case R.id.news_news_tab_head:
				index = 0;
				break;
			case R.id.news_news_tab_entertain:
				index = 1;
				break;
			case R.id.news_news_tab_sports:
				index = 2;
				break;
			case R.id.news_news_tab_science:
				index = 3;
				break;
			case R.id.news_news_tab_economics:
				index = 4;
				break;
		}
		viewPager.setCurrentItem(index);
	}

	public void onPageScrolled(int i, float v, int i1) {

	}

	public void onPageSelected(int position) {
		int id = R.id.news_news_tab_head;
		switch (position) {
			case 0:
				id = R.id.news_news_tab_head;
				break;
			case 1:
				id = R.id.news_news_tab_entertain;
				break;
			case 2:
				id = R.id.news_news_tab_sports;
				break;
			case 3:
				id = R.id.news_news_tab_science;
				break;
			case 4:
				id = R.id.news_news_tab_economics;
				break;
		}
		radioGroup.check(id);
	}

	public void onPageScrollStateChanged(int i) {

	}
}