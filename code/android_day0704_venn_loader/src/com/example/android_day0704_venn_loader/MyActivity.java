package com.example.android_day0704_venn_loader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

public class MyActivity extends FragmentActivity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Bundle bundle = new Bundle();
		bundle.putString("path", "this is path");
		LoaderManager manager = getSupportLoaderManager();
		manager.initLoader(444, bundle, new LoaderManager.LoaderCallbacks<String>() {
			public Loader<String> onCreateLoader(int i, Bundle bundle) {
				String path = bundle == null ? null : bundle.getString("path");
				WeatherLoader loader = new WeatherLoader(MyActivity.this, path);
				return loader;
			}

			public void onLoadFinished(Loader<String> loader, String s) {
				Log.d("--------->", "onLoadFinished:" + s);
			}

			public void onLoaderReset(Loader<String> loader) {

			}
		});
	}
}
