package com.example.android_day0624_notification;

import android.app.Notification;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * project:com.example.android_day0624_notification
 * user:VennUser
 * date:2015/6/24
 */
public class DownLoadAsyncTask extends AsyncTask<String, Integer, String>
{
	private Context context;

	public DownLoadAsyncTask(Context context)
	{
		this.context = context;
	}

	//在子线程中执行耗时的操作，操作完成后返回值就是泛型的第三个参数
	protected String doInBackground(String... params)
	{
		if (params != null)
		{
			int len = params.length;
			for (int i = 0; i < len; i++)
			{
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				//异步任务可以在当前方法内部调用更新进度的方法从而更新进度
				this.publishProgress(i + 1,len);
			}
		}
		return null;
	}

	//当异步任务进度发生变化(调用publishProgress(int ))就会自动回调此方法，可以更新UI界面
	protected void onProgressUpdate(Integer... values)
	{
		//TODO 发送带有进度的通知
		//需要上下文才可以发送通知
		if (context != null)
		{
			NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
			builder.setSmallIcon(R.drawable.ic_launcher);
			builder.setContentTitle("DownLoading....");
			builder.setContentText("DownLoading..........");

			int currentProgress=values[0];
			int max=values[1];

			//设置进度
			builder.setProgress(max,currentProgress,false);

//			Notification notification=builder.build();
//			NotificationManagerCompat manager = NotificationManagerCompat.from(context);
//			manager.notify(999,notification);

			NotificationManagerCompat.from(context).notify(888,builder.build());
		}
	}

	protected void onPostExecute(String s)
	{
		super.onPostExecute(s);
	}
}
