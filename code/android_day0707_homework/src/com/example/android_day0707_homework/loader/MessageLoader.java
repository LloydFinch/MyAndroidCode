package com.example.android_day0707_homework.loader;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;
import com.example.android_day0707_homework.entity.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * project:com.example.android_day0707_homework.loader
 * user:VennUser
 * date:2015/7/7
 */
public class MessageLoader extends AsyncTaskLoader<List<Message>> {

	private Context context;
	private Uri uri;

	public MessageLoader(Context context) {
		super(context);
		this.context = context;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}

	public List<Message> loadInBackground() {
		List<Message> messageList = new ArrayList<Message>();
		ContentResolver resolver = context.getContentResolver();
		String rawNumber = Telephony.Sms.ADDRESS;
		String rawContent = Telephony.Sms.BODY;
		String rawDate = Telephony.Sms.DATE;
		Cursor cursor = resolver.query(uri, new String[]{rawNumber, rawContent, rawDate}, null, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				Message message = new Message();
				int indexNumber = cursor.getColumnIndex(rawNumber);
				String number = cursor.getString(indexNumber);
				message.setNumber(number);

				message.setSender(getSender(number));

				int indexContent = cursor.getColumnIndex(rawContent);
				String content = cursor.getString(indexContent);
				message.setContent(content);

				int indexDate = cursor.getColumnIndex(rawDate);
				Date date = new Date(cursor.getLong(indexDate));
				message.setDate(date);

				messageList.add(message);
			}
			cursor.close();
		}
		return messageList;
	}

	protected void onStartLoading() {
		forceLoad();
	}

	protected void onStopLoading() {
		super.onStopLoading();
	}

	private String getSender(String number) {
		String sender = null;
		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
				ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?",
				new String[]{number},
				null
		);

		if (cursor != null) {
			while (cursor.moveToNext()) {
				int indexSender = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
				sender = cursor.getString(indexSender);
			}
		}
		return sender;
	}
}
