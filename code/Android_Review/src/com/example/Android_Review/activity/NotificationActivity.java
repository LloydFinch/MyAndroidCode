package com.example.Android_Review.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import com.example.Android_Review.R;

/**
 * Created by VennUser on 2015/7/23.
 */
public class NotificationActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
	}

	public void btnNotification(View view) {

		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

		builder.setContentTitle("Notification");
		builder.setContentText("This is a notification");
		builder.setSmallIcon(R.drawable.ic_launcher);

		//set Music
		//builder.setDefaults(9);
		Intent intent = new Intent(this, ToastActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 9, intent, PendingIntent.FLAG_ONE_SHOT);

		builder.setContentIntent(pendingIntent);

		Notification notification = builder.build();

		NotificationManagerCompat manager = NotificationManagerCompat.from(this);

		manager.notify(444, notification);

	}
}