package com.example.android_day0710_broadcastreceiver.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/10.
 */
public class Stick2BroadCast extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Log.d("------->", "Stick2BroadCast");
	}
}
