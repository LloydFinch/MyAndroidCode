package com.example.android_day0707_homework.loader;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import com.example.android_day0707_homework.entity.Contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * project:com.example.android_day0707_homework.loader
 * user:VennUser
 * date:2015/7/7
 */
public class ContactsLoader extends AsyncTaskLoader<List<Contacts>> {

	private Context context;
	private Uri uri;

	public ContactsLoader(Context context) {
		super(context);
		this.context = context;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}

	public List<Contacts> loadInBackground() {
		List<Contacts> contactsList = new ArrayList<Contacts>();
		String rawName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
		String rawNumber = ContactsContract.CommonDataKinds.Phone.NUMBER;
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(uri, new String[]{rawName, rawNumber}, null, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				Contacts contacts = new Contacts();
				int indexName = cursor.getColumnIndex(rawName);
				String name = cursor.getString(indexName);
				contacts.setName(name);

				int indexNumber = cursor.getColumnIndex(rawNumber);
				String number = cursor.getString(indexNumber);
				contacts.setNumber(number);

				contactsList.add(contacts);
			}
			cursor.close();
		}
		return contactsList;
	}

	protected void onStartLoading() {
		forceLoad();
	}

	protected void onStopLoading() {
		super.onStopLoading();
	}
}
