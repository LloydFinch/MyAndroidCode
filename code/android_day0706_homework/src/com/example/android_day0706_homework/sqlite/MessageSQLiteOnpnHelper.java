package com.example.android_day0706_homework.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * project:com.example.android_day0706_homework.sqlite
 * user:VennUser
 * date:2015/7/6
 */
public class MessageSQLiteOnpnHelper extends SQLiteOpenHelper {

	public MessageSQLiteOnpnHelper(Context context) {
		super(context, "message.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table messages(_id integer primary key autoincrement, content text, time long, flag " +
				"integer)");
	}


	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
