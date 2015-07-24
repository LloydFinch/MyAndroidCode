package com.example.Android_Review.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.example.Android_Review.R;
import com.example.Android_Review.adapter.ViewPagerAdapter;
import com.example.Android_Review.fragment.PagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VennUser on 2015/7/23.
 */
public class ViewPagerActivity extends FragmentActivity {
	private ViewPager viewPager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		viewPager = (ViewPager) this.findViewById(R.id.pager_view);

		List<Fragment> fragmentList = new ArrayList<Fragment>();
		for (int i = 0; i < 5; i++) {
			PagerFragment fragment = new PagerFragment();
			fragmentList.add(fragment);
		}

		ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(viewPagerAdapter);
	}
}