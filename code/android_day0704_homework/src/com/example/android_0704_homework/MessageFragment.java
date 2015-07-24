package com.example.android_0704_homework;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
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
public class MessageFragment extends Fragment {
	private ListView listViewMessage;
	private static Context context;

	public MessageFragment() {

	}

	public static void getContext(Context con) {
		context = con;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_message, container, false);
		listViewMessage = (ListView) view.findViewById(R.id.list_message);

		Cursor cursor = context.getContentResolver().query(
				Telephony.Sms.CONTENT_URI,
				null,
				null, null, null
		);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				context,// Context
				android.R.layout.simple_expandable_list_item_2,// Layout
				cursor,// Cursor
				new String[]{Telephony.Sms.ADDRESS, Telephony.Sms.BODY},// raw
				new int[]{android.R.id.text1, android.R.id.text2}, // the view to display content
				SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER //flag(enum)
		);
		listViewMessage.setAdapter(adapter);
		return view;
	}
}