package com.example.ProviderUse;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MyActivity extends Activity {
	private EditText editTextName, edtiTextNumber;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		editTextName = (EditText) this.findViewById(R.id.txt_name);
		edtiTextNumber = (EditText) this.findViewById(R.id.txt_number);

		//call user-defined ContentProvider:CustomProvider/StudentProvider
		//step1 :assign Uri
		String path = "content://com.example.CustomProvider.students/students";
		Uri uri = Uri.parse(path);

		//step2 :get ContentResolver
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(uri, null, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				int indexName = cursor.getColumnIndex("name");
				if (indexName > -1) {
					String name = cursor.getString(indexName);
					Log.d("名字", name);
				}

				int indexNumber = cursor.getColumnIndex("number");
				if (indexNumber > -1) {
					String number = cursor.getString(indexNumber);
					Log.d("学号", number);
				}
			}
			cursor.close();
		}
	}

	//insert data into table by ContentProvider
	public void btnAdd_onclick(View view) {
		String path = "content://com.example.CustomProvider.students/students";
		Uri uri = Uri.parse(path);
		ContentResolver resolver = getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", editTextName.getText().toString());
		values.put("number", edtiTextNumber.getText().toString());
		Uri newUri = resolver.insert(uri, values);

		long id = ContentUris.parseId(newUri);
		Cursor cursor = resolver.query(uri, null, "_id = ?", new String[]{Long.toString(id)}, null);
		Log.d("=======>id", "" + id);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				int indexName = cursor.getColumnIndex("name");
				if (indexName > -1) {
					String name = cursor.getString(indexName);
					Log.d("=======>", name);
				}

				int indexNumber = cursor.getColumnIndex("number");
				if (indexNumber > -1) {
					String number = cursor.getString(indexNumber);
					Log.d("=======>", number);
				}
			}
		}
	}

	public void btnDelete_onclick(View view) {
		String path = "content://com.example.CustomProvider.students/students";
		Uri uri = Uri.parse(path);
		ContentResolver resolver = getContentResolver();
		resolver.delete(uri, null, null);
	}

	public void btnUpdate_onclick(View view) {
		String path = "content://com.example.CustomProvider.students/students";
		Uri uri = Uri.parse(path);
		ContentResolver resolver = getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "张三");
		//values.put("number", "789");
		resolver.update(uri, values, null, null);
	}
}
