package com.example.android_day15_baiduMap2;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by VennUser on 2015/7/15.
 */
public class MyApplication extends Application {

	public void onCreate() {
		super.onCreate();

		//��ʼ���ٶȵ�ͼ��SDK
		SDKInitializer.initialize(getApplicationContext());

	}
}
