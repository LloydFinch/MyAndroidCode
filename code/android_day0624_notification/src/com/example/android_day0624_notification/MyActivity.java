package com.example.android_day0624_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

/**
 * project:com.example.android_day0624_notification
 * user:VennUser
 * date:2015/6/24
 */
public class MyActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	//简单的通知
	public void btn_onclick(View view)
	{
		//创建用来创建通知的Builder,后缀Compat表示兼容
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		//通知必须设位置小图标属性，否则会崩溃
		builder.setSmallIcon(R.drawable.ic_launcher);
		//设置通知标题
		builder.setContentTitle("First Notification");
		//设置通知内容
		builder.setContentText("Content of the notification");
		//生成通知
		Notification notification = builder.build();
		//使用NotificationManager显示通知
		NotificationManagerCompat manager = NotificationManagerCompat.from(this);
		//通知的方法两种，1 采用id进行通知，id唯一标识一个通知。相同则覆盖，
		// 所以要显示多个通知，应使id不同  2 采用tag标签进行的通知，先看id再看tag
		manager.notify((int) System.currentTimeMillis(), notification);
		manager.notify(001, notification);
	}

	//复杂的通知
	public void btn2_onclick(View view)
	{
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle("Complex Notification");
		builder.setContentText("Content of Complex Notification");

		//设置通知数量
		builder.setNumber(3);
		//设置声音
		//通常在NotificationCompat执行
		builder.setDefaults(NotificationCompat.DEFAULT_ALL);

		Notification notification = builder.build();
		NotificationManagerCompat manager = NotificationManagerCompat.from(this);
		manager.notify((int) System.currentTimeMillis(), notification);
	}

	//不能删除的通知
	//应用场景:下载提示，音乐播放
	public void btn3_onclick(View view)
	{
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle("下载中");
		builder.setContentText("正在下载");
		builder.setDefaults(NotificationCompat.DEFAULT_ALL);

		//设置运行中状态，使得当前通知不能被清除
		builder.setOngoing(true);

		Notification notification = builder.build();
		NotificationManagerCompat manager = NotificationManagerCompat.from(this);
		manager.notify((int) System.currentTimeMillis(), notification);
	}

	//带点击查看详情的通知
	public void btn4_onclick(View view)
	{
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle("带有点击的通知");
		builder.setContentText("通知的内容");

		//设置启动的Intent的内容,PendingIntent用来包裹普通的Intent
		Intent intent = new Intent(this, MyActivity.class);

		//内部包裹了一个Intent，所有通过getActivity()获取的PendingIntent能够自动执行startActivity()
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 007, intent, PendingIntent.FLAG_ONE_SHOT);

		//设置通知点击的时候执行的操作，通过PendingIntent来定义
		builder.setContentIntent(pendingIntent);

		//设置通知点击之后自动消失
		builder.setAutoCancel(true);

		Notification notification = builder.build();
		NotificationManagerCompat manager = NotificationManagerCompat.from(this);
		manager.notify((int) System.currentTimeMillis(), notification);
	}
}
