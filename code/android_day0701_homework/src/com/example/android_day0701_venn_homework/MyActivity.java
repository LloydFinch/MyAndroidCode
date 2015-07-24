package com.example.android_day0701_venn_homework;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.example.android_day0701_venn_homework.fragment.AddContactsFragment;
import com.example.android_day0701_venn_homework.fragment.ContactsFragment;
import com.example.android_day0701_venn_homework.fragment.MessageFragment;

public class MyActivity extends FragmentActivity {
	private FragmentManager manager;
	private ContactsFragment contactsFragment;
	private MessageFragment messageFragment;
	private AddContactsFragment addContactsFragment;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ContactsFragment.getContext(this);
		MessageFragment.getContext(this);
		AddContactsFragment.getContext(this);
		setContentView(R.layout.main);
		manager = getSupportFragmentManager();
	}

	public void btnContacts_onClick(View view) {
		contactsFragment = contactsFragment == null ? new ContactsFragment() : contactsFragment;
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.fraglayout, contactsFragment);
		transaction.commit();
	}

	public void btnMessage_onClick(View view) {
		messageFragment = messageFragment == null ? new MessageFragment() : messageFragment;
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.fraglayout, messageFragment);
		transaction.commit();
	}

	public void btnAdd_onClick(View view) {
		addContactsFragment = addContactsFragment == null ? new AddContactsFragment() : addContactsFragment;
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.fraglayout, addContactsFragment);
		transaction.commit();
	}


	public void btnFinish_onclick(View view) {
		AddContactsFragment.addContacts();
		btnContacts_onClick(view);
		addContactsFragment.editClear();
	}

	public void btnClear_onclick(View view) {
		AddContactsFragment.editClear();
	}
}
