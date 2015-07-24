package com.example.Android_Review.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.example.Android_Review.sqlite.MySQLiteOpenHelper;

import java.util.Date;

/**
 * Created by VennUser on 2015/7/23.
 */
public class ContentProviderService extends ContentProvider {

	private Context context;
	private SQLiteDatabase database;
	private static UriMatcher uriMatcher = new UriMatcher(0);

	private static int student = 666;
	private static int teacher = 777;

	static {
		uriMatcher.addURI("com.kong.provider", "student", student);
		uriMatcher.addURI("com.kong.provider", "teacher", teacher);
	}

	public boolean onCreate() {
		return true;
	}

	public void setContext(Context context) {
		this.context = context;
		database = new MySQLiteOpenHelper(context).getWritableDatabase();
	}

	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		int code = uriMatcher.match(uri);

		Cursor cursor = null;
		if(code == 666){
			cursor = database.query("student",null,null,null,null,null,null);
		}

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
}
