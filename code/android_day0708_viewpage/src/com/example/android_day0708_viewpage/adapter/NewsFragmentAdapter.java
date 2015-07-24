package com.example.android_day0708_viewpage.adapter;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.android_day0708_viewpage.MyFragment;

import java.util.List;

/**
 * project:com.example.android_day0708_viewpage.adapter
 * user:VennUser
 * date:2015/7/8
 */

//用于给ViewPager设置Fragment
public class NewsFragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList;

	public NewsFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}

	public void setImageList(List<Fragment> fragmentList) {
		this.fragmentList = fragmentList;
	}


	//返回实际的内容,在内部调用此方法后会返回相应界面
	public Fragment getItem(int i) {

		return fragmentList.get(i);
	}

	//获取页数
	public int getCount() {
		return fragmentList.size();
	}
}
