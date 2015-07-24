package com.example.Android_Review.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VennUser on 2015/7/23.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public MySQLiteOpenHelper(Context context) {
		super(context, "student.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table student(_id integer primary key autoincrement, name text, age integer)");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
