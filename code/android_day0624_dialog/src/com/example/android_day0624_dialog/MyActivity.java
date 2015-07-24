package com.example.android_day0624_dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.text.AndroidCharacter;
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

		//创建显示进度对话框
		final ProgressDialog progressDialog = new ProgressDialog(this);

		//设置标题
		progressDialog.setTitle("Please Wait");

		//显示提示信息
		progressDialog.setMessage("Loading.....");

		//设置是否可以取消
		progressDialog.setCancelable(false);

		//设置点击外部区域是否取消
		progressDialog.setCanceledOnTouchOutside(false);

		//水平进度条需要使用自带的属性设置(枚举类型)
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setProgress(50);
		progressDialog.setMax(100);
		progressDialog.setIndeterminate(false);

		//设置确定按钮,第一个为按钮种类，枚举类型，第二个参数为按钮上的内容，第三个参数为监听的事件
		progressDialog.setButton(ProgressDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				Toast.makeText(MyActivity.this, "Now is Loading", Toast.LENGTH_LONG).show();
			}
		});

		//设置取消按钮
		progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener()
		{
			//参数的意义：哪一个对话框的那个按钮点击了
			public void onClick(DialogInterface dialog, int which)
			{
				//TODO 取消加载,对话框消失
				progressDialog.dismiss();
			}
		});

		//显示对话框
		progressDialog.show();
	}

	//当用户点击返回键的时候，执行相应的回调
	public void onBackPressed()
	{
		//Activity的此方法默认是finish()
		//显示AlertDialog，提示是否退出
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("是否退出?");

		//设置是否允许取消
		builder.setCancelable(true);

		//设置确认按钮
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				//先关闭对话框，Activity再退出
				dialog.dismiss();
				finish();
			}
		});

		//设置取消按钮
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
			}
		});

		builder.setNeutralButton("Nothing", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
			}
		});

		//创建对话框但不显示
		//builder.create();
		//显示对话框并显示，内部自动创建一个对话框对象，要关闭需要调用此对象的dismiss()方法
		builder.show();
	}
}
