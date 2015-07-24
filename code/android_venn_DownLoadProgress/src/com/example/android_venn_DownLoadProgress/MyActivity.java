package com.example.android_venn_DownLoadProgress;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.GetChars;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MyActivity extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void btn_onclick(View view)
	{
		new DownLoadAsyncTask(this, (ImageView) this.findViewById(R.id.picture)).execute("http://news.fdc.com" +
				".cn/newsimageupload/307117/23.jpg");
	}

	public void btn_toast_onclick(View view)
	{
		Toast toast = new Toast(this);
		//下面这句是错的，不能在自定义土司里面指定文本，可以放在自定义布局里面的TextView中
		//toast.setText("This is Toast");
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(LayoutInflater.from(this).inflate(R.layout.toast_view, null));
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	}
}
