package com.example.android_day0629_SharedPrefenence;

import android.app.Activity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;

/**
 * project:com.example.android_day0629_SharedPrefenence
 * user:VennUser
 * date:2015/6/29
 */
public class FileMAnagerActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filemanager);

		//1 在files文件夹下创建目录，并且创建一个文件
		File filesDir = getFilesDir();
		filesDir=getCacheDir();
		File folder = new File(filesDir, "notes");
		if (!folder.exists())
		{
			folder.mkdirs();
		}

		File file = new File(folder, "note1.txt");
		if(!file.exists()){
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		//
	}
}