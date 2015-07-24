package com.example.android_venn_day0624_notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;

/**
 * project:com.example.android_venn_day0624_notification
 * user:VennUser
 * date:2015/6/24
 */
public class ProgressAsync extends AsyncTask<String, Integer, String>
{
	private Context context;
	private  ProgressDialog progressDialog;
	public ProgressAsync(Context context)
	{
		this.context = context;
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Loading...");
		progressDialog.setMessage("File is Loading......");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
			}
		});
	}

	protected String doInBackground(String... params)
	{
		if (params != null)
		{
			int length = params.length;
			for (int i = 0; i < length; i++)
			{
				publishProgress(length, i + 1);
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	protected void onProgressUpdate(Integer... values)
	{
		progressDialog.setMax(values[0]);
		progressDialog.setProgress(values[1]);
		progressDialog.show();
	}
}
