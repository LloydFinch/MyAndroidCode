package com.example.android_day0708_viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import com.example.android_day0708_viewpage.adapter.NewsFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0708_viewpage
 * user:VennUser
 * date:2015/7/8
 */
//实现ViewPager和RadioGroup的联动
public class NewsActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

	private RadioGroup radioGroup;
	private ViewPager viewPager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		//联动的操作：
		//1 初始化两个对象
		//2 设置RadioGroup的点击事件
		//3 设置ViewPager的切换事件
		radioGroup = (RadioGroup) this.findViewById(R.id.news_tab_bar);

		//设置默认选中第一个RadioButton
		radioGroup.check(R.id.tab_home);

		viewPager = (ViewPager) this.findViewById(R.id.pager_news);

		radioGroup.setOnCheckedChangeListener(this);
		List<Fragment> fragmentList = new ArrayList<Fragment>();
		for (int i = 0; i < 5; i++) {
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putString("position", "news" + i);
			fragment.setArguments(bundle);
			fragmentList.add(fragment);
		}
		NewsFragmentAdapter adapter = new NewsFragmentAdapter(getSupportFragmentManager(), fragmentList);

		//设置ViewPager滚动动态的切换RadioGroup的状态
		//ViewPager在第一次显示的时候没有调用回调接口的方法
		viewPager.setOnPageChangeListener(this);

		viewPager.setAdapter(adapter);
	}

	//设置RadioGroup的点击切换ViewPager事件
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int index = 0;
		switch (checkedId) {
			case R.id.tab_home://新闻
				index = 0;
				break;
			case R.id.tab_read://阅读
				index = 1;
				break;
			case R.id.tab_view://视听
				index = 2;
				break;
			case R.id.tab_find://发现
				index = 3;
				break;
			case R.id.tab_personal://个人
				index = 4;
				break;
		}
		//设置ViewPager显示第几页,两个参数表示是否平滑切换
		viewPager.setCurrentItem(index);
	}

	//当页面滚动会频繁调用此方法
	public void onPageScrolled(int position, float positionOffset, int i1) {

	}

	//当页面切换完成时显示的回调
	//参数表示显示的页面
	public void onPageSelected(int position) {
		//指示器
		int id = R.id.tab_home;
		switch (position) {
			case 0:
				id = R.id.tab_home;
				break;
			case 1:
				id = R.id.tab_read;
				break;
			case 2:
				id = R.id.tab_view;
				break;
			case 3:
				id = R.id.tab_find;
				break;
			case 4:
				id = R.id.tab_personal;
				break;
		}
		radioGroup.check(id);
	}

	//页面滚动的状态改变
	public void onPageScrollStateChanged(int position) {

	}
}