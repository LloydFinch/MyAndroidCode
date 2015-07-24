package com.example.Android_Review.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.example.Android_Review.R;
import com.example.Android_Review.receiver.BatteryReceiver;

/**
 * Created by VennUser on 2015/7/23.
 */
public class BroadCastReceiverActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broadcastreceiver);

		BatteryReceiver receiver = new BatteryReceiver();

		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

		getApplicationContext().registerReceiver(receiver, intentFilter);
	}
}