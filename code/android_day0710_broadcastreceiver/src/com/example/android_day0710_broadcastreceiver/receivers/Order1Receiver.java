package com.example.android_day0710_broadcastreceiver.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/10.
 */
public class Order1Receiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = new Bundle();
		bundle.putString("info", "Order1Receiver更改的信息");
		setResultExtras(bundle);
		Log.d("------->", intent.getStringExtra("info"));

		//终止广播，不再向下发送,无序广播此举无效
		//abortBroadcast();
	}
}
