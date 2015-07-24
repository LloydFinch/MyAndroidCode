package com.example.android_day0630_homework.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * project:com.example.android_day0630_homework.SQLite
 * user:VennUser
 * date:2015/6/30
 */
public class DBHelper extends SQLiteOpenHelper {
	private static final String CREATE_TABLE = "CREATE TABLE account (id INTEGER PRIMARY KEY AUTOINCREMENT, time LONG, " +
			"money REAL DEFAULT (0), type INTEGER DEFAULT (0), category TEXT)";

	public DBHelper(Context context) {
		super(context, "account.db", null, 2);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
