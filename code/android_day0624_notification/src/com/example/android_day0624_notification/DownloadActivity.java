package com.example.android_day0624_notification;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

/**
 * project:com.example.android_day0624_notification
 * user:VennUser
 * date:2015/6/24
 */
public class DownloadActivity extends Activity
{
	private int progress = 10;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		//TODO创建下载异步任务
		//TODO
		new DownLoadAsyncTask(this).execute(new String[20]);
	}

	//带进度的通知
	public void btn_click(View view)
	{
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle("点击下载");
		builder.setContentText("下载内容");

		//设置进度,第三个参数决定是否显示具体的比例
		builder.setProgress(100, progress = progress < 100 ? progress += 100 : 100, false);

		Notification notification = builder.build();
		NotificationManagerCompat manager = NotificationManagerCompat.from(this);
		manager.notify((int) System.currentTimeMillis(), notification);
	}
}