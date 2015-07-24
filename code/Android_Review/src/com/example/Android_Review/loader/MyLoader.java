package com.example.Android_Review.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Set;

/**
 * Created by VennUser on 2015/7/23.
 */
public class MyLoader extends AsyncTaskLoader {
	public MyLoader(Context context) {
		super(context);
	}

	public Object loadInBackground() {
		return null;
	}

	public void setUrl(String url) {

	}

	protected void onStartLoading() {

		//必须有
		forceLoad();
	}

	protected void onStopLoading() {
		super.onStopLoading();
	}
}
