package com.example.Android_Review.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.example.Android_Review.R;

/**
 * Created by VennUser on 2015/7/23.
 */
public class ContentProviderActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contentproviderservice);

		ContentResolver resolver = getContentResolver();

		Uri uri = Uri.parse("content://com.kong.provider/student");
		Cursor cursor = resolver.query(uri, null, null, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				int index = cursor.getColumnIndex("name");
				if (index > -1) {
					String name = cursor.getColumnName(index);
				}
			}
		}
	}
}