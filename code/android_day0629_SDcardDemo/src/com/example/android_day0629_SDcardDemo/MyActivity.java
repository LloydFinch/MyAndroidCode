package com.example.android_day0629_SDcardDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MyActivity extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// 代码操作存储卡

		//1 获取存储卡的目录，file对象
		File sdcard = Environment.getExternalStorageDirectory();

		//2 进行存储卡状态的检测：检查存储卡是否存在
		//当返回状态为,MEDIA_MOUNTED代表存储卡可用，其余都不可以写文件
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED))
		{
			//存储卡有效
			File folder = new File(sdcard, "mydata");
			if (!folder.exists())
			{
				folder.mkdirs();
			}
			File f = new File(folder, "note1.txt");
			if (!f.exists())
			{
				try
				{
					f.createNewFile();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			//获取私有数据目录
			File fileD = this.getExternalFilesDir(null);
			Log.d("------------->", "" + fileD);

			File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_DCIM);
			Log.d("------------->", "" + externalFilesDir);

			getExternalCacheDir();

			//  /data/data
			Environment.getDataDirectory();

			Environment.getDownloadCacheDirectory();

			//获取共有目录
			Environment.getExternalStoragePublicDirectory("");
		} else
		{

		}
	}
}
