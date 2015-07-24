package com.example.android_day0630_SQLiteCountMoney.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * project:com.example.android_day0630_SQLiteCountMoney.database
 * user:VennUser
 * date:2015/6/30
 */
public class DBHelper extends SQLiteOpenHelper {

	private static final String CREATE_RECORDS = "CREATE TABLE records (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
			" record_type INTEGER, money REAL DEFAULT (0), " +
			"time LONG, category1 TEXT, category2 TEXT);";

	//constructor:4 params
	public DBHelper(Context context) {
		super(context, "money.db", null, 2);
	}

	//create table
	public void onCreate(SQLiteDatabase db) {
		//db:offer CRUD
		db.execSQL(CREATE_RECORDS);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion != oldVersion) {
			//oldVersion primary key is id ,don't 支持SimpleCursorAdapter
			String dropOld = "DROP TABLE records";//delete table
			db.execSQL(dropOld);
			db.execSQL(CREATE_RECORDS);
		}
	}
}
