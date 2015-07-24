package com.example.CustomProvider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * project:com.example.CustomProvider
 * user:VennUser
 * date:2015/7/3
 */
public class ContentProviderStudent extends ContentProvider {

	private SQLiteHelperImpl helper;
	private static UriMatcher matcher = new UriMatcher(0);

	public static final int STUDENT_CODE = 498;

	static {
		matcher.addURI("com.venn.provider", "student", STUDENT_CODE);
	}

	public boolean onCreate() {
		helper = new SQLiteHelperImpl(getContext());
		return true;
	}

	public String getType(Uri uri) {
		return null;
	}

	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}

	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = null;
		int code = matcher.match(uri);
		switch (code) {
			case STUDENT_CODE:
				cursor = database.query("student", projection, selection, selectionArgs, null, null, sortOrder);
				break;
		}
		return cursor;
	}
}
