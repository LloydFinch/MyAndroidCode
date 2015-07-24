package com.example.android_day0707_homework;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.example.android_day0707_homework.fragment.ContactsFragment;
import com.example.android_day0707_homework.fragment.GroupFragment;
import com.example.android_day0707_homework.fragment.MessageFragment;

public class MyActivity extends Activity implements ActionBar.TabListener {
	/**
	 * Called when the activity is first created.
	 */

	private GroupFragment groupFragment;
	private ContactsFragment contactsFragment;
	private MessageFragment messageFragment;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		groupFragment = new GroupFragment();
		contactsFragment = new ContactsFragment();
		messageFragment = new MessageFragment();
		setContentView(R.layout.main);

		ContactsFragment.getContext(this);
		MessageFragment.getContext(this);

		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			ActionBar.Tab tabGroup = actionBar.newTab();
			tabGroup.setIcon(R.drawable.ic_action_group).setText("群组").setTabListener(this);

			ActionBar.Tab tabContacts = actionBar.newTab();
			tabContacts.setIcon(R.drawable.ic_action_person).setText("联系人").setTabListener(this);

			ActionBar.Tab tabMessage = actionBar.newTab();
			tabMessage.setIcon(R.drawable.ic_action_email).setText("收件箱").setTabListener(this);

			actionBar.addTab(tabGroup);
			actionBar.addTab(tabContacts);
			actionBar.addTab(tabMessage);
		}

	}

	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		switch (tab.getPosition()) {
			case 0:
				ft.replace(R.id.frame_content, groupFragment);
				break;
			case 1:
				ft.replace(R.id.frame_content, contactsFragment);
				break;
			case 2:
				ft.replace(R.id.frame_content, messageFragment);
				break;
		}
	}

	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

	}

	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

	}
}
