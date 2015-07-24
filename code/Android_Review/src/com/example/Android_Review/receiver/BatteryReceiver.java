package com.example.Android_Review.receiver;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by VennUser on 2015/7/23.
 */
public class BatteryReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		Log.d("--------->", "Action: " + action);
		Toast.makeText(context, "change", Toast.LENGTH_SHORT).show();
		if (action.equals(Intent.ACTION_BATTERY_OKAY)) {

			int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
			Log.d("---------->", "电池等级: " + level);
			Toast.makeText(context, "ACTION_BATTERY_OKAY", Toast.LENGTH_SHORT).show();
		}
		if (action.equals(Intent.ACTION_BATTERY_LOW)) {

			int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
			Log.d("---------->", "电池等级: " + level);
			Toast.makeText(context, "ACTION_BATTERY_LOW", Toast.LENGTH_SHORT).show();
		}
		if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {

			int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
			Log.d("---------->", "电池等级: " + level);
			Toast.makeText(context, "ACTION_BATTERY_CHANGED", Toast.LENGTH_SHORT).show();
		}
	}
}
