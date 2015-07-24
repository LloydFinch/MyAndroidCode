package com.example.CustomProvider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * project:com.example.CustomProvider.database
 * user:VennUser
 * date:2015/7/3
 */
public class SqlOpenHelperImpl extends SQLiteOpenHelper {

	public SqlOpenHelperImpl(Context context) {
		super(context, "students.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		//create table student_info
		db.execSQL("create table student_info(_id integer primary key autoincrement, number text, name text)");

		//insert into student_info data
		ContentValues valuesStudent = new ContentValues();
		valuesStudent.put("number", "107");
		valuesStudent.put("name", "Tom");
		db.insert("student_info", null, valuesStudent);

		//create table courses_info
		db.execSQL("create table courses_info(_id integer primary key autoincrement, title text, week text)");

		//insert into courses_info data
		ContentValues valuesCourse = new ContentValues();
		valuesCourse.put("title", "java");
		valuesCourse.put("week", "monday");
		db.insert("courses_info", null, valuesCourse);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
