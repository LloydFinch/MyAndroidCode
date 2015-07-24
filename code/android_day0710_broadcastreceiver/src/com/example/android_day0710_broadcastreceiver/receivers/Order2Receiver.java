package com.example.android_day0710_broadcastreceiver.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/10.
 */
public class Order2Receiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = getResultExtras(true);
		String info = bundle.getString("info");
		Log.d("------->", "Order2Receiver" + info);
	}
}
