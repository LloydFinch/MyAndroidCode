package com.example.ProviderUser;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String path = "content://com.venn.provider/student";
		Uri uri = Uri.parse(path);
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(uri, null, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				int indexName = cursor.getColumnIndex("name");
				if (indexName > -1) {
					String name = cursor.getString(indexName);
					Log.d("--------->", name);
				}
				
				int indexStuNo = cursor.getColumnIndex("stu_no");
				if (indexStuNo > -1) {
					String stuNo = cursor.getString(indexStuNo);
					Log.d("--------->", stuNo);
				}

				int indexCourseId = cursor.getColumnIndex("course_id");
				if (indexCourseId > -1) {
					String courseId = cursor.getString(indexCourseId);
					Log.d("--------->", courseId);
				}
			}
			cursor.close();
		}
	}
}
