package com.example.CustomProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * project:com.example.CustomProvider
 * user:VennUser
 * date:2015/7/3
 */
public class SQLiteHelperImpl extends SQLiteOpenHelper {

	public SQLiteHelperImpl(Context context) {
		super(context, "student.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table student(_id integer primary key autoincrement, name text, stu_no text, course_id " +
				"text)");
		ContentValues values = new ContentValues();
		values.put("name", "zack");
		values.put("stu_no", "1101");
		values.put("course_id", "101");
		db.insert("student", null, values);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
