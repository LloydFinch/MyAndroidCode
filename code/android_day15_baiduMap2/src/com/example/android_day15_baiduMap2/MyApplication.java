package com.example.android_day15_baiduMap2;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by VennUser on 2015/7/15.
 */
public class MyApplication extends Application {

	public void onCreate() {
		super.onCreate();

		//初始化百度地图的SDK
		SDKInitializer.initialize(getApplicationContext());

	}
}
