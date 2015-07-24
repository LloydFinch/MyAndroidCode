package com.example.android_day0704_loader;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ListView;

//a demo of use Loader
//step1: get LoaderManager by Context
//step2: init Loader by LoaderManager, create new if the Loader doesn't exist
//step3: assign callback interface to load data
//LoaderCallback need to assign Generics(泛型) to assign the data type
public class MyActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {
	private ListView listview;
	private SimpleCursorAdapter adapter;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listview = (ListView) this.findViewById(R.id.list_content);
		adapter = new SimpleCursorAdapter(
				this,
				android.R.layout.simple_list_item_2,
				null, // Cursor,null is allowed，can set it by swapCursor(Cursor)
				new String[]{"body"},
				new int[]{android.R.id.text2},
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
		);
		listview.setAdapter(adapter);

		//create LoaderManager by Context
		LoaderManager manager = getSupportLoaderManager();

		//init Loader by LoaderManager
		//param1: id of you assigned, a tag of the Loader(sole[唯一])
		//param2: param to the Loader you want to create
		//param3: callback interface to load data, need to realise
		Loader loader = manager.initLoader(111, null, this);
		manager.initLoader(222, null, new LoaderManager.LoaderCallbacks<String>() {

			public Loader<String> onCreateLoader(int i, Bundle bundle) {
				Log.d("========>", "匿名内部类的方法");

				return null;
			}

			public void onLoadFinished(Loader<String> loader, String s) {

			}

			public void onLoaderReset(Loader<String> loader) {

			}
		});
	}

	//called when the Loader you init was doesn't exist
	//param1:same to initLoader
	//param2:same to initLoader
	//return the Loader you create
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {

		//CursorLoader: use to load data of ContentProvider
		CursorLoader cursorLoader = new CursorLoader(
				this,
				Telephony.Sms.CONTENT_URI,
				null, null, null, null
		);
		return cursorLoader;
	}

	//called automatically when the data was finished
	//load data stored in Loader
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		//the method will replace the old Cursor in a new Cursor in the Loader
		//and close the old Cursor(judge whether the Cursor is empty inner the method )
		adapter.changeCursor(cursor);

		//the method will replace the old Cursor in a new Cursor but don't close the old Cursor
		//adapter.swapCursor(cursor);
	}

	//called automatically to destroy Loader when activity/fragment was destroy
	//you should free the data before the method was called
	public void onLoaderReset(Loader<Cursor> loader) {

	}
}
