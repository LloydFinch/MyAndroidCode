package com.example.android_venn_day0624_notification;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

public class MyActivity extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	private int currentProgress;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ProgressDialog progressDialog = new ProgressDialog(this);

		//ProgressDialog.Builder builder = new ProgressDialog.Builder(this);
		progressDialog.setTitle("Loading...");
		progressDialog.setMessage("Please Wait......");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		progressDialog.setProgress(70);
		progressDialog.setCanceledOnTouchOutside(true);

		progressDialog.setCancelable(true);
		progressDialog.show();
	}

	public void btn_onclick(View view)
	{
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.mes2, Notification.PRIORITY_HIGH);
		builder.setContentTitle("Message");
		builder.setContentText("You have a mseeage need to read......");

		builder.setAutoCancel(true);
		builder.setOngoing(true);
		builder.setNumber(3);
		builder.setProgress(100,currentProgress<100?currentProgress+=10:100,false);

		Intent intent = new Intent(this, MyActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 007, intent, PendingIntent.FLAG_ONE_SHOT);
		builder.setContentIntent(pendingIntent);

		//Notification notification = builder.build();
		//notification.sound= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.dog);
		//notification.defaults=Notification.DEFAULT_SOUND;

		//builder.setDefaults(Notification.DEFAULT_ALL);

		//自定义通知声音
		builder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dog));
		NotificationManagerCompat.from(this).notify("Music", 888, builder.build());
	}

	public void onBackPressed()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Warnning");
		builder.setMessage("Are you sure to Exit ? ");
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
				finish();
			}
		});

		builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
			}
		});
		builder.show();
	}

	public void down_onclick(View view) {
		new ProgressAsync(this).execute(new String[20]);
	}
}
