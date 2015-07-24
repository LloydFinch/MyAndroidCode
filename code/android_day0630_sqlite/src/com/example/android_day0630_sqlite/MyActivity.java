package com.example.android_day0630_sqlite;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	private SQLiteDatabase writableDatabase;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	//Open/Create DateBase
	public void co_onclick(View view) {

		//step1: create child class of SQLiteOpenHelper
		//step2: assign name and version of the SQLiteOpenHelper in structure
		//step3: realize two method:onCreate(SQLiteDatabase db);onUpgrade(SQLiteDatabase db, int oldVersion, int
		// newVersion)
		//step4: use method you want:getReadableDatabase();
		//                           getWritableDatabase();by this method you can get database

		DBHelper dbHelper = new DBHelper(this);
		if (writableDatabase == null) {
			writableDatabase = dbHelper.getWritableDatabase();
		}
	}

	//Close DateBase
	public void close_onclick(View view) {
		if (writableDatabase != null) {
			writableDatabase.close();
		}
	}
}
