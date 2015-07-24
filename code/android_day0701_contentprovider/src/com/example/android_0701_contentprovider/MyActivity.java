package com.example.android_0701_contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity implements AdapterView.OnItemClickListener {
	/**
	 * Called when the activity is first created.
	 */
	private ListView list_contacts;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		list_contacts = (ListView) this.findViewById(R.id.list_contacts);

		//step of get contacts(use ContentProvider)
		//step1: use getContentResolver()(Context offer) get ContentResolver
		ContentResolver resolver = getContentResolver();

		//step2: use ContentProvider get Uri
		Uri uri = ContactsContract.Contacts.CONTENT_URI;//ContentProvider offer Uri

		//step3: use ContentResolver to CRUD
		//query()
		//param1: type of Uri(ContentProvider offer)[content://<authority>/<table>/]
		//                  authority:the register content in AndroidManifest.xml[packageName.provider]
		//                  contacts: content://com.android.contacts/contacts
		//                  sms:content://sms/inbox
		Cursor cursor = resolver.query(
				uri, //Uri
				null, //row name
				null, //where
				null, // where condition
				null);//order by

		//step4: use Cursor get data
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this,
				android.R.layout.simple_list_item_1,
				cursor,
				new String[]{"display_name"},
				new int[]{android.R.id.text1},
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
		);
		list_contacts.setAdapter(adapter);

		//click display phone number
		list_contacts.setOnItemClickListener(this);
	}

	//param:id,set by SimpleCursorAdapter , is the id in database
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//TODO display phone number
		//use Context get ContentResolver
		ContentResolver resolver = getContentResolver();

		//get phone number Uri to query
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		Cursor cursor = resolver.query(
				uri,
				null,//row name
				"contact_id = ?",//where
				new String[]{Long.toString(id)},//where condition(id)
				null);//order by

		//use cursor to traverse(遍历) data
		if (cursor != null) {
			//TODO handle cursor
			StringBuilder sb = new StringBuilder();
			while (cursor.moveToNext()) {

				//get index
				// data all storage in the row of data1
				int indexPhone = cursor.getColumnIndex("data1");//data1 express phone number row
				if (indexPhone > -1) {

					//get data by index
					String phoneNumber = cursor.getString(indexPhone);
					sb.append(phoneNumber).append('\n');
				}
			}
			String number = sb.toString();
			cursor.close();
			Toast.makeText(this, number, Toast.LENGTH_LONG).show();
		}
	}
}
