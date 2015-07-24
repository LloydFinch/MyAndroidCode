package com.example.Android_Review.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/23.
 */
public class NetReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			Log.d("--------------->", "net receiver");
		}
	}
}
