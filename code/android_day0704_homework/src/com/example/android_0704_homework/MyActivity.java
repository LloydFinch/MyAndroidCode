package com.example.android_0704_homework;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyActivity extends FragmentActivity {

	private ContactsFragment contactsFragment;
	private MessageFragment messageFragment;
	private FragmentManager manager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		manager = getSupportFragmentManager();
	}

	public void btnContacts_onclick(View view) {
		ContactsFragment.getContext(this);
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.layout_fragment, contactsFragment == null ? new ContactsFragment() : contactsFragment);
		transaction.commit();
	}

	public void btnMessage_onclick(View view) {
		MessageFragment.getContext(this);
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.layout_fragment, messageFragment == null ? new MessageFragment() : messageFragment);
		transaction.commit();
	}
}
