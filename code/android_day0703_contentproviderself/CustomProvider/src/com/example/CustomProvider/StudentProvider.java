package com.example.CustomProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import com.example.CustomProvider.database.SqlOpenHelperImpl;

import java.nio.DoubleBuffer;

/**
 * project:com.example.CustomProvider
 * user:VennUser
 * date:2015/7/3
 */

//user-defined ContentProvider realize CRUD
//two Uri :1 students 2 courses
public class StudentProvider extends ContentProvider {

	private static final String TAG = "StudentProvider";
	private SqlOpenHelperImpl sqlOpenHelper;

	//use UriMatcher to check Uri
	//create UriMatcher to match Uri,if matched,return assigned code
	//default : the code that failed matched returned
	private static UriMatcher uriMatcher = new UriMatcher(0);

	public static final int CODE_STUDENT = 666;

	public static final int CODE_COURSES = 777;

	static {
		//reflect Uri to int code(Uri + path = code)
		//param 1:Uri, 2:path(can't use '/' as head), 3:code
		uriMatcher.addURI("com.example.CustomProvider.students", "students", CODE_STUDENT);
		uriMatcher.addURI("com.example.CustomProvider.students", "courses", CODE_COURSES);
	}

	//if ContentProvider loaded success, return true, false otherwise
	public boolean onCreate() {
		//if you want use SqLiteDatabase,don't use getWritableDatabase() in this method
		//suggest you use it in CRUD method

		//use getContext() to get Context
		sqlOpenHelper = new SqlOpenHelperImpl(getContext());
		return true;
	}

	public String getType(Uri uri) {
		return null;
	}

	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase database = sqlOpenHelper.getWritableDatabase();
		int code = uriMatcher.match(uri);
		long id = -1;
		switch (code) {
			case CODE_STUDENT:
				id = database.insert("student_info", null, values);
				break;
			case CODE_COURSES:
				id = database.insert("courses_info", null, values);
				break;
		}
		Log.d(TAG, "insert-->" + id);

		//param 1 old Uri 2 id of the insert returned
		return id != -1 ? Uri.withAppendedPath(uri, String.valueOf(id)) : null;
	}

	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase database = sqlOpenHelper.getWritableDatabase();
		int code = uriMatcher.match(uri);
		int count = 0;
		switch (code) {
			case CODE_STUDENT:
				count = database.delete("student_info", null, null);
				break;
			case CODE_COURSES:
				count = database.delete("courses_info", null, null);
				break;
		}
		Log.d(TAG, "delete-->" + count);
		return count;
	}

	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		Log.d(TAG, "update-->" + uri);
		int code = uriMatcher.match(uri);
		SQLiteDatabase database = sqlOpenHelper.getWritableDatabase();
		int rawCount = 0;
		switch (code) {
			case CODE_STUDENT:
				rawCount = database.update("student_info", values, selection, selectionArgs);
				break;
			case CODE_COURSES:
				rawCount = database.update("courses_info", values, selection, selectionArgs);
				break;
		}
		Log.d("update", "----->" + rawCount);
		return rawCount;
	}

	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Log.d(TAG, "query-->" + uri);
		//get matched code
		int code = uriMatcher.match(uri);

		//get SQLiteDatabase
		SQLiteDatabase database = sqlOpenHelper.getWritableDatabase();
		Cursor cursor = null;
		switch (code) {
			case CODE_STUDENT:
				cursor = database.query("student_info", projection, selection, selectionArgs, null, null,
						sortOrder);
				break;
			case CODE_COURSES:
				cursor = database.query("courses_info", projection, selection, selectionArgs, null, null,
						sortOrder);
				break;
		}
		Log.d(TAG, "uriCode---->" + code);
		return cursor;
	}
}
