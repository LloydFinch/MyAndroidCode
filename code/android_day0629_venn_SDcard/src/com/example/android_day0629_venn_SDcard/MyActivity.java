package com.example.android_day0629_venn_SDcard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//获取Sdcard的目录的file对象，将Sdcard想象为一个名为file的文件夹
		File externalStorageDirectory = Environment.getExternalStorageDirectory();

		//获取SDcard的状态，只有状态为MEDIA_MOUNTED才可用，其他任何状态都不可用
		String externalStorageState = Environment.getExternalStorageState();
		if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
			File file = new File(externalStorageState, "filename");
		}

		//获取到的目录/data/data
		Environment.getDataDirectory();

		//通过上下文获取私有数据,参数为要获得的类型，为枚举型，如果为null则获得所有类型
		this.getExternalFilesDir("type");
		this.getExternalFilesDir(Environment.DIRECTORY_DCIM);
		this.getExternalFilesDir(null);
	}
}
