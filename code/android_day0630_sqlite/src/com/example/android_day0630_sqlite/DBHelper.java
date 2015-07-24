package com.example.android_day0630_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * project:com.example.android_day0630_sqlite
 * user:VennUser
 * date:2015/6/30
 */
public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		//param：1 Context，2 name of database，3 数据库查询时对于记录的封装，通常为null，
		// 4 version，will save in the file of database，and change with the code leave change
		super(context, "appdata", null, 2);

	}

	//called when first start and the db isn't exist
	public void onCreate(SQLiteDatabase db) {
		Log.d("----->SQLite", "onCreate");

		//you can create tables in this method
		db.execSQL("create table users (id integer primary key autoincrement,name text not null,age integer)");
	}

	//Update Database, called when current version is changed
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("----->SQLite", "onCreate" + " " + oldVersion + " " + newVersion);
	}
}
