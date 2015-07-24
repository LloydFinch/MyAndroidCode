package com.example.Android_Review.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.View;
import com.example.Android_Review.R;
import com.example.Android_Review.sqlite.MySQLiteOpenHelper;

/**
 * Created by VennUser on 2015/7/23.
 */
public class SQLiteActivity extends Activity {

	private SQLiteDatabase database;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);

		database = new MySQLiteOpenHelper(this).getWritableDatabase();
	}

	public void btnSQLOnclick(View view) {
		String table = "student";
		insert(table);
		query(table);
		update(table);
		delete(table);
	}

	private void query(String table) {
		Cursor cursor = database.query(table, null, null, null, null, null, null);

		while (cursor.moveToNext()) {
			int nameIndex = cursor.getColumnIndex("name");
			if (nameIndex > -1) {
				String name = cursor.getColumnName(nameIndex);
			}
		}
	}

	private long insert(String table) {

		ContentValues values = new ContentValues();
		values.put("name", "Tom");
		values.put("age", 20);

		long row = database.insert(table, null, values);

		return row;
	}

	private int delete(String table) {
		int count = database.delete(table, "name = ?", new String[]{"Tom"});
		return count;
	}

	private int update(String table) {

		ContentValues values = new ContentValues();
		values.put("name", "Zack");

		int count = database.update(table, values, "name = ?", new String[]{"Tom"});
		return count;
	}
}