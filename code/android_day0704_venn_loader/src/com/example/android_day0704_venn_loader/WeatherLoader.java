package com.example.android_day0704_venn_loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * project:com.example.android_day0704_venn_loader
 * user:VennUser
 * date:2015/7/4
 */
public class WeatherLoader extends AsyncTaskLoader<String> {

	private String path;

	public WeatherLoader(Context context, String path) {
		super(context);
		this.path = path;
	}

	public String loadInBackground() {
		String data = path;
		return data;
	}

	protected void onStartLoading() {
		forceLoad();//call loadInBackground() in this method
	}
}
