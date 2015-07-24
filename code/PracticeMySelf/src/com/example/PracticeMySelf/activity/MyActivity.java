package com.example.PracticeMySelf.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;
import com.example.PracticeMySelf.R;

public class MyActivity extends FragmentActivity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		FragmentManager manager = getSupportFragmentManager();
//		FragmentTransaction transaction = manager.beginTransaction();
//		transaction.addToBackStack("login");


		NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle("Title");
		builder.setContentText("Content");
		Intent intent = new Intent(this, MyActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 007, intent, PendingIntent.FLAG_ONE_SHOT);
		builder.setContentIntent(pendingIntent);
		Notification notification = builder.build();
		managerCompat.notify("message", 1000, notification);


		SharedPreferences settings = getSharedPreferences("settings", 222);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("title", "Android");
		editor.commit();
		String title = settings.getString("title", "333");
		Log.d("-------->", title);
	}
}
