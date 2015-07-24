package com.example.PracticeMySelf.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.UriMatcher;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import com.example.PracticeMySelf.R;

/**
 * Created by VennUser on 2015/7/19.
 */
public class SQLiteActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);

		SimpleCursorAdapter adapter  = null;
		Cursor cursor = null;
		adapter.swapCursor(cursor);

	}
}