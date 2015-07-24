package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by VennUser on 2015/7/10.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList;

	public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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
