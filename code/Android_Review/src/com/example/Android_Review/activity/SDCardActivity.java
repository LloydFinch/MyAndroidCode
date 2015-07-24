package com.example.Android_Review.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import com.example.Android_Review.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by VennUser on 2015/7/23.
 */
public class SDCardActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sdcard);
	}

	public void btnSDCard_onclick(View view) throws FileNotFoundException {

		File file = Environment.getExternalStorageDirectory();
		Log.d("---------->", file.toString());

		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			Log.d("---------->", state);
		}

		File file1 = Environment.getDataDirectory();
		Log.d("---------->", file1.toString());

		File file2 = this.getDir("file", MODE_PRIVATE);
		Log.d("---------->", file2.toString());

		File file3 = this.getFilesDir();
		Log.d("---------->", file3.toString());

		File file4 = this.getCacheDir();
		Log.d("---------->", file4.toString());

		//FileInputStream fi = this.openFileInput("file");

		String[] fileNames = file.list();
		Log.d("---------->", fileNames.toString());

		File[] files = file.listFiles();
		Log.d("---------->", files.toString());

		String parentPath = file.getParent();
		Log.d("---------->", parentPath.toString());

		File parentFile = file.getParentFile();
		Log.d("---------->", parentFile.toString());
	}
}