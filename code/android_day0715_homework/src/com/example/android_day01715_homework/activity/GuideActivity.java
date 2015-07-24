package com.example.android_day01715_homework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import com.example.android_day01715_homework.R;
import com.example.android_day01715_homework.adapter.GuideAdapter;
import com.example.android_day01715_homework.fragment.GuideFragment;
import com.example.android_day01715_homework.fragment.GuideLastFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VennUser on 2015/7/15.
 */
public class GuideActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager
		.OnPageChangeListener {

	private ViewPager viewPager;
	private RadioGroup radioGroup;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		viewPager = (ViewPager) this.findViewById(R.id.pager_guide);
		radioGroup = (RadioGroup) this.findViewById(R.id.tab_bottom);

		List<Fragment> fragmentList = new ArrayList<>();

		GuideFragment guideFragment = new GuideFragment();
		Bundle bundle = new Bundle();
		bundle.putString("introduce", "第一步,打开地图,选择城市");
		guideFragment.setArguments(bundle);
		fragmentList.add(guideFragment);

		GuideFragment guideFragment2 = new GuideFragment();
		Bundle bundle2 = new Bundle();
		bundle2.putString("introduce", "第二步,在浮动框输入关键词");
		guideFragment2.setArguments(bundle2);
		fragmentList.add(guideFragment2);

		GuideLastFragment guideFragment3 = new GuideLastFragment();
		Bundle bundle3 = new Bundle();
		bundle3.putString("introduce", "第三步,点击搜索即可查看结果");
		guideFragment3.setArguments(bundle3);
		fragmentList.add(guideFragment3);

		GuideAdapter adapter = new GuideAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(adapter);

		viewPager.setOnPageChangeListener(this);
		radioGroup.setOnCheckedChangeListener(this);
		radioGroup.check(R.id.tab1);
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int index = 0;
		switch (checkedId) {
			case R.id.tab1:
				index = 0;
				break;
			case R.id.tab2:
				index = 1;
				break;
			case R.id.tab3:
				index = 2;
				break;
		}
		viewPager.setCurrentItem(index);
	}

	public void onPageScrolled(int i, float v, int i1) {

	}

	public void onPageSelected(int position) {

		int id = R.id.tab1;
		switch (position) {
			case 0:
				id = R.id.tab1;
				break;
			case 1:
				id = R.id.tab2;
				break;
			case 2:
				id = R.id.tab3;
				break;
		}
		radioGroup.check(id);
	}

	public void onPageScrollStateChanged(int i) {

	}

	public void btnStart_onclick(View view) {
		startActivity(new Intent(this, CityListActivity.class));
	}
}