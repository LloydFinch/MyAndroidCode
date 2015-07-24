package com.example.android_day0629_venn_file;

import android.app.Activity;
import android.os.Bundle;

import java.io.*;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//获取到的目录：data/data/package name/app_files,第一个参数的作用：app_params
		File file = getDir("files", MODE_PRIVATE);

		//获取到的目录：data/data/package name/files
		File file1 = getFilesDir();

		//获取到的目录：data/data/package name/cache
		File file2 = getCacheDir();

		//列出file目录下的所有文件及文件夹，返回字符串数组
		String[] filename = file.list();

		//列出file目录下的所有文件及文件夹，返回文件和文件夹数组
		File[] files = file.listFiles();

		//在file1目录下创建文件夹,/目录为:data/data/package name/files/file1/newFile
		File newFile = new File(file1, "newFile");


		//文件的写入
		try {
			//利用上下文的openFileOutput(String filename,int limits)创建一个文件,如果存在就不创建
			FileOutputStream fileOutputStream = openFileOutput("note", MODE_PRIVATE);
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));
			bufferedWriter.write("this is what you want to write,please replace this line by anything you want to " +
					"input");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			//TODO 关闭流的操作
		}


		//文件的读取
		try {
			//利用上下文的openFileInput(String filename)打开一个文件
			FileInputStream fileInputStream = openFileInput("note");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String line = null;
			StringBuilder stringBuilder = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			stringBuilder.toString();//读取到的字符串
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			//TODO 关闭流的操作
		}
	}
}
