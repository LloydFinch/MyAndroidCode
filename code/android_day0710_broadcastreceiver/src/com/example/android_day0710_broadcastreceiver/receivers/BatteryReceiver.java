package com.example.android_day0710_broadcastreceiver.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import com.example.android_day0710_broadcastreceiver.R;

/**
 * Created by VennUser on 2015/7/10.
 */

//电池电量的广播接收者，使用动态注册方式注册
public class BatteryReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		//处理电量的状态
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {

			//BatteryManager包含很多常量

			//电量
			int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

			//电池状态,可选(枚举值)
			int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);

			//电源情况,可选(枚举值)
			int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

			//设置通知来显示电池状态
			NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
			builder.setSmallIcon(R.drawable.ic_launcher);
			builder.setContentTitle("电池状态");
			builder.setContentText("电池级别:" + level + "电池状态:" + status + "电源情况:" + plugged);
			Notification notification = builder.build();
			NotificationManagerCompat manager = NotificationManagerCompat.from(context);
			manager.notify(111, notification);
		}
	}
}
