package com.example.android_day0624_dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * project:com.example.android_day0624_dialog
 * user:VennUser
 * date:2015/6/24
 */
public class ToastActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toast);
		Toast toast = Toast.makeText(this, "Toast", Toast.LENGTH_LONG);
		//设置土司的位置,位置参数的第一个需要使用Gravity中的常量
		toast.setGravity(Gravity.RIGHT | Gravity.TOP, 0, 0);
		toast.show();
	}

	//自定义土司
	public void btnt_onclick(View view)
	{
		//1 创建土司
		Toast toast = new Toast(this);

		//2 加载布局(可以自定义)
		View v = LayoutInflater.from(this).inflate(R.layout.toastview, null);

		//3 设置布局,一定要设置位置和时长
		toast.setView(v);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);

		//4 显示
		toast.show();
	}
}