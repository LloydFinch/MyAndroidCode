package com.example.android_day0708_viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.example.android_day0708_viewpage.adapter.NewsFragmentAdapter;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0708_viewpage
 * user:VennUser
 * date:2015/7/8
 */
public class EnterActivity extends FragmentActivity {
	private ViewPager viewPager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter);

		viewPager = (ViewPager) this.findViewById(R.id.pager_enter);

//		List<Fragment> fragments = new ArrayList<Fragment>();
//
//		//Fragment可以通过参数不同来传递不同的内容，大部分应用在场景相同但内容不同
//		for (int i = 0; i < 4; i++) {
//			MyFragment fragment = new MyFragment();
//
//			Bundle bundle = new Bundle();
//			bundle.putString("position", "位置是:" + i);
//
//			fragment.setArguments(bundle);
//			fragments.add(fragment);
//		}

		//关于FragmentManager的设置
//		NewsFragmentAdapter adapter = new NewsFragmentAdapter(getSupportFragmentManager(), fragments);
//		viewPager.setAdapter(adapter);

		List<Fragment> fragmentList = new ArrayList<Fragment>();
		for (int i = 0; i < 4; i++) {
			WeatherFragment fragment = new WeatherFragment();
			Bundle bundle = new Bundle();
			bundle.putString("position", "state" + i);
			fragment.setArguments(bundle);
			fragmentList.add(fragment);
		}

		NewsFragmentAdapter adapter = new NewsFragmentAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(adapter);
	}
}