package com.example.android_day0714_baidumap;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by VennUser on 2015/7/14.
 */

//Application是应用程序启动的入口,第一个加载
public class MyApplication extends Application {

	public void onCreate() {
		super.onCreate();

		//TODO 在此处初始化百度地图的SDK
		//这里初始化的操作会对所有组建生效
		SDKInitializer.initialize(getApplicationContext());
	}
}
