package com.example.android_0701_contentprovider;

import android.app.Activity;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * project:com.example.android_0701_contentprovider
 * user:VennUser
 * date:2015/7/1
 */
public class InsertTestActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avtivity_inserttest);

		Uri uri = Telephony.Sms.CONTENT_URI;
	}

	//add empty contact
	public void btnadd_onclick(View view) {

		//step 1 get ContentResolver by Context
		ContentResolver resolver = getContentResolver();

		//step 2 add data to raw_contacts table

		//1 get Uri
		Uri uri = ContactsContract.RawContacts.CONTENT_URI;

		//2 get ContentValues and put data by the type of key-value
		ContentValues values = new ContentValues();
		values.put(ContactsContract.Contacts.DISPLAY_NAME, "Tom");

		//create a empty contacts but can display
		Uri insertID = resolver.insert(uri, values);
		Toast.makeText(this, "---->" + insertID, Toast.LENGTH_LONG).show();

		//add data to assign contact name
		Uri uriName = ContactsContract.Data.CONTENT_URI;
		ContentValues valuesName = new ContentValues();

		// to data table, add data must assign row_contact_id
		// all Uri that insert return include the record id
		// get id by ContentUris
		long newID = ContentUris.parseId(insertID);
		valuesName.put(ContactsContract.Data.RAW_CONTACT_ID, newID);
		valuesName.put("data1", "Android" + System.currentTimeMillis());

		//3 set data type
		valuesName.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
		Uri insert = resolver.insert(uriName, valuesName);

		//add phone number
		Uri uriPhone = ContactsContract.Data.CONTENT_URI;
		ContentValues valuesPhone = new ContentValues();
		valuesPhone.put(ContactsContract.Data.RAW_CONTACT_ID, newID);
		valuesPhone.put("data1", "18612345678");

		//assign data type to phone number
		valuesPhone.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		resolver.insert(uriPhone, valuesPhone);
	}

	//delete contacts name start with "Android"
	public void btnDelete_onclick(View view) {

		//step 1 get ContentResolver
		ContentResolver resolver = getContentResolver();

		//step 2 delete data,only need to delete raw_contacts table
		Uri uri = ContactsContract.RawContacts.CONTENT_URI;

		//param1: uri
		//param2: where
		//param3: where condition
		int count = resolver.delete(
				uri,
				"display_name like ?",
				new String[]{"Android%"});
		Toast.makeText(this, "删除了" + count + "记录", Toast.LENGTH_LONG).show();
	}

	//update contacts
	public void btnUpdate_onclick(View view) {
		Uri uri = ContactsContract.RawContacts.CONTENT_URI;
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(
				uri,
				new String[]{ContactsContract.RawContacts._ID},//raw name
				"display_name like ?",
				new String[]{"Android%"},//% is 通配符
				null
		);

		//get id by cursor
		if (cursor != null) {
			long rid = -1;
			if (cursor.moveToNext()) {
				int index = cursor.getColumnIndex(ContactsContract.RawContacts._ID);
				rid = cursor.getLong(index);
			}
			cursor.close();

			if (rid != -1) {
				//update contact who's id = rid
				ContentValues values = new ContentValues();
				values.put("data1", "wocao");
				int count = resolver.update(
						ContactsContract.Data.CONTENT_URI,
						values,
						ContactsContract.Data.RAW_CONTACT_ID + "= ?",
						new String[]{Long.toString(rid)});
				Toast.makeText(this, "修改了" + count + "条记录", Toast.LENGTH_SHORT).show();
			}
		}
	}

	//get message
	public void btnGetsms_onclick(View view) {
		ContentResolver resolver = getContentResolver();
		Uri uri = Telephony.Sms.CONTENT_URI;
		Cursor cursor = resolver.query(
				uri, null, null, null, null
		);

		if (cursor != null) {
			while (cursor.moveToNext()) {
				int index = cursor.getColumnIndex(Telephony.Sms.BODY);
				if (index > -1) {
					String smsBody = cursor.getString(index);
					Log.d("--------->", "" + smsBody);
				}
			}
			cursor.close();
		}
	}
}