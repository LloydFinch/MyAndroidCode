package com.example.android_day0629_SharedPrefenence;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;

/**
 * project:com.example.android_day0629_SharedPrefenence
 * user:VennUser
 * date:2015/6/29
 */
public class InternalFile extends Activity
{
	private EditText noteText;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internalfile);
		noteText = (EditText) this.findViewById(R.id.txt_note);
		//内部文件操作:记事本案例，启动时加载记事本内容

		//1 指定加载的文件名称
		//通过context打开文件
		FileInputStream fi = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try
		{
			//作用：打开Android内部存储区中，应用程序手机内部的文件
			fi = openFileInput("notes.txt");
			isr = new InputStreamReader(fi, "utf-8");
			br = new BufferedReader(isr);
			String line = null;
			StringBuilder sb = new StringBuilder();
			while (true)
			{
				line = br.readLine();
				if (line == null)
				{
					break;
				}
				sb.append(line).append('\n');
			}
			String content = sb.toString();

			//获取内部文件路径，可以自定义文件位置
			//通过上下文获取 方式 :/data/data/包名/app_files,如果写成其他内容，则创建新的目录
			File file = getDir("files", Context.MODE_PRIVATE);
			Log.d("file", "fileDir = " + file);

			//获取应用程序内部存储的缓存目录 /data/data/包名/cache
			getCacheDir();

			///data/data/包名/files/newFile/
			File targetFile = new File(file, "newFile");

			//创建多级目录
			if (!targetFile.exists())
			{
				targetFile.mkdirs();
			}

			//在newFile目录下创建文件
			File f = new File(targetFile, "a.txt");
			if (!f.exists())
			{
				f.createNewFile();
			}

			//获取目录下的所有文件及文件夹
			targetFile.list();
//			f.renameTo();
			FileInputStream fileInputStream = new FileInputStream(f);

			//TODO EditText显示内容
			noteText.setText(content);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException err)
		{
			err.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (isr != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (fi != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	//保存输入框的内容，存储到内部文件中
	public void Save_onclick(View view)
	{
		//1 打开输出流
		FileOutputStream fo = null;
		OutputStreamWriter osw = null;
		try
		{
			//打开文件并且打开输出流,不存在则创建
			fo = openFileOutput("notes.txt", Context.MODE_PRIVATE);
			osw = new OutputStreamWriter(fo, "utf-8");
			osw.write(noteText.getText().toString());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (osw != null)
			{
				try
				{
					osw.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (fo != null)
			{
				try
				{
					fo.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}