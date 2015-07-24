package com.example.android_day0704_loader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;

/**
 * project:com.example.android_day0704_loader
 * user:VennUser
 * date:2015/7/4
 */
public class WeatherActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<String> {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		LoaderManager manager = getSupportLoaderManager();
		Bundle bundle = new Bundle();
		bundle.putString("url", "http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&day=0&city=&dfc=1&charset=utf-8");
		manager.initLoader(333, bundle, this);
	}

	public Loader<String> onCreateLoader(int i, Bundle bundle) {
		Log.d("-------->", "onCreateLoader");
		WeatherLoader loader = new WeatherLoader(this);
		if (bundle != null) {
			String url = bundle.getString("url");
			if (url != null) {
				loader.setUrl(url);
			}
		}
		return loader;
	}

	public void onLoadFinished(Loader<String> loader, String s) {
		Log.d("-------->", "onLoadFinished" + ":" + s);
	}

	public void onLoaderReset(Loader<String> loader) {
		Log.d("-------->", "onLoaderReset");
	}

	public void btnLoad_onclick(View view) {
		LoaderManager manager = getSupportLoaderManager();
		Bundle bundle = new Bundle();
		bundle.putString("url", "http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&day=0&city=&dfc=1&charset=utf-8");

		//重启Loader，销毁旧的，加载新的
		manager.restartLoader(333, bundle, this);
	}
}