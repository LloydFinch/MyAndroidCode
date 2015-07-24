package com.example.android_day0710_broadcastreceiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import com.example.android_day0710_broadcastreceiver.receivers.BatteryReceiver;


public class MyActivity extends Activity {
	private BatteryReceiver receiver;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//使用动态注册的方式进行电池电量的注册
		receiver = new BatteryReceiver();

		//使用安卓内置的电池电量变化的Action
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

		Context applicationContext = getApplicationContext();
		//param 1 BroadCastReceiver 2 IntentFilter
		//动态注册的广播接收者和注册他的Context的生命周期一样
		applicationContext.registerReceiver(receiver, filter);
	}

	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	public void bntSend_onclick(View view) {

		//创建广播发送的ACTION
		Intent intent = new Intent("com.example.action.STUDY_CHANGE");

		//利用上下文发送无序广播
		sendBroadcast(intent);

	}

	public void btnSendOrder_onclick(View view) {

		Intent intent = new Intent("com.qq.action.STUDY_CHANGE");
		intent.putExtra("info", "BroadCast Receiver");

		//利用上下文发送有序广播,需要intent和权限,接收者必须声明该权限，否则无法接收
		//指定null表示不需要权限
		sendOrderedBroadcast(intent, null);
	}

	public void btnSendStick_onclick(View view) {
		Intent intent = new Intent("com.web.action.STUDY_CHANGE");
		sendStickyBroadcast(intent);
	}
}
