package com.example.android_0704_homework;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.GetChars;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * project:com.example.android_0704_homework
 * user:VennUser
 * date:2015/7/5
 */
public class ContactsFragment extends Fragment {

	private ListView listViewContacts;
	private static Context context;

	public ContactsFragment(){

	}

	public static void getContext(Context con) {
		context = con;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_contacts, container, false);
		listViewContacts = (ListView) view.findViewById(R.id.list_contacts);

		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null,
				null, null, null
		);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				context,// Context
				android.R.layout.simple_expandable_list_item_2,// Layout
				cursor,// Cursor
				new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds
						.Phone.NUMBER},
				// raw
				new int[]{android.R.id.text1, android.R.id.text2}, // the view to display content
				SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER //flag(enum)
		);
		listViewContacts.setAdapter(adapter);
		return view;
	}
}