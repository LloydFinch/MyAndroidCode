package com.example.Android_Review.activity;

import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.content.Loader;
import com.example.Android_Review.R;
import com.example.Android_Review.loader.MyLoader;

/**
 * Created by VennUser on 2015/7/23.
 */
public class LoaderActivity extends android.support.v4.app.FragmentActivity implements LoaderManager.LoaderCallbacks {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader);

		LoaderManager loaderManager = this.getSupportLoaderManager();

		Bundle bundle = new Bundle();
		bundle.putString("url", "http://www.baidu.com");
		loaderManager.initLoader(4, bundle, this);

		//loaderManager.restartLoader(5,bundle,this);
	}

	public Loader onCreateLoader(int i, Bundle bundle) {
		MyLoader loader = new MyLoader(this);
		loader.setUrl(bundle.getString("url"));
		//return后开始调用loader的loadInBackground()
		return loader;
	}

	public void onLoadFinished(Loader loader, Object o) {
		//loader的onStopLoading执行完返回此处,并且返回第二个参数
	}

	public void onLoaderReset(Loader loader) {

	}
}