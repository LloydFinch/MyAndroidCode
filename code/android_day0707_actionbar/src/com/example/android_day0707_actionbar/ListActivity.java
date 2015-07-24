package com.example.android_day0707_actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0707_actionbar
 * user:VennUser
 * date:2015/7/7
 */

//使用ActionBar的List显示
public class ListActivity extends Activity implements ActionBar.OnNavigationListener {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			actionBar.setIcon(R.drawable.ic_action_refresh);

			//设置Adapter,而且设置条目点击的回调
			List<String> list = new ArrayList<String>();
			list.add("按天");
			list.add("按周");
			list.add("按月");
			ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list);
			actionBar.setListNavigationCallbacks(adapter, this);
		}
	}

	//选中list时执行此方法,相同的条目多次选中无效果,只有改变选中才触发,默认选中第一个
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Toast.makeText(this, "选择了" + itemPosition + "," + itemId, Toast.LENGTH_LONG).show();
		return true;
	}
}