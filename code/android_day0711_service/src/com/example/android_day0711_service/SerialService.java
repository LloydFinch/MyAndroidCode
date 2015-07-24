package com.example.android_day0711_service;

import android.app.IntentService;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/11.
 */

//串行下载的服务，继承IntentService,内部含有子线程
public class SerialService extends IntentService {

	//param 用于调试
	public SerialService(String name) {
		super(name);
	}

	//用于处理startService()传递的intent，每次onStartCommand()被调用时，传递的intent会被
	//放到一个队列中，调用该方法处理，所以可以通过启动startService()来启动IntentService
	protected void onHandleIntent(Intent intent) {

		Log.d("---------->", "onHandleIntent");
		String control = intent.getStringExtra("control");
		if (control.equals("play")) {
			Log.d("---------->", "play");
		}
		//TODO 处理实际的数据，是在子线程中调用的
	}
}
