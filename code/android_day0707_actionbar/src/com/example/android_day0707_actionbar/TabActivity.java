package com.example.android_day0707_actionbar;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.example.android_day0707_actionbar.fragment.CallFragment;
import com.example.android_day0707_actionbar.fragment.ContactsFragment;
import com.example.android_day0707_actionbar.fragment.MessageFragment;

/**
 * project:com.example.android_day0707_actionbar
 * user:VennUser
 * date:2015/7/7
 */

//show Activity by ActionBar
public class TabActivity extends FragmentActivity implements ActionBar.TabListener {

	private ContactsFragment contactsFragment;
	private MessageFragment messageFragment;
	private CallFragment callFragment;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		contactsFragment = new ContactsFragment();
		messageFragment = new MessageFragment();
		callFragment = new CallFragment();

		//get ActionBar
		ActionBar actionBar = getActionBar();
		//没有标题的Activity没有ActionBar
		if (actionBar != null) {

			//设置ActionBar的导航模式
			//两种：TAB和下拉列表模式
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			ActionBar.Tab tabContacts = actionBar.newTab();

			//set title
			tabContacts.setText("联系人");

			//set icon
			tabContacts.setIcon(R.drawable.ic_action_refresh);

			//must set Listener
			tabContacts.setTabListener(this);

			ActionBar.Tab tabMessage = actionBar.newTab();
			tabMessage.setText("短信").setIcon(R.drawable.ic_action_refresh);
			tabMessage.setTabListener(this);

			ActionBar.Tab tabCall = actionBar.newTab();
			tabCall.setText("电话").setIcon(R.drawable.ic_action_refresh);
			tabCall.setTabListener(this);

			actionBar.addTab(tabContacts);
			actionBar.addTab(tabMessage);
			actionBar.addTab(tabCall);
		}
	}


	//da那个Tab从其他Tab切换过来就成为被选中状态,默认选中第一个
	//param :tab:被选中的Tab, ft:事务,不支持添加回退栈,不允许提交,ActionBar内部自己提交
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		switch (tab.getPosition()) {
			case 0:
				ft.replace(R.id.frame_content, contactsFragment);
				break;
			case 1:
				ft.replace(R.id.frame_content, messageFragment);
				break;
			case 2:
				ft.replace(R.id.frame_content, callFragment);
				break;
		}
	}

	//跳出时，旧的Tab先执行此方法,新的Tab再执行上述方法
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
		Toast.makeText(this, "跳出:" + tab.getText() + ",位置:" + tab.getPosition(), Toast.LENGTH_LONG).show();
	}

	//在选中的Tab上再次点击Tab时执行此方法
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
		Toast.makeText(this, "重新选中:" + tab.getText() + ",位置:" + tab.getPosition(), Toast.LENGTH_LONG).show();
	}
}