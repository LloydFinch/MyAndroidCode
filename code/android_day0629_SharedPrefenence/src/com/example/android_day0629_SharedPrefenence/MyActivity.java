package com.example.android_day0629_SharedPrefenence;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MyActivity extends Activity implements CompoundButton.OnCheckedChangeListener
{
	/**
	 * Called when the activity is first created.
	 */

	private CheckBox chbRemember;
	private EditText usernameEditText;
	private EditText pswEditText;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		//获取配置信息

		//1 打开共享设置(需要通过上下文) SharedPreferences
		Context context = this;

		//在应用程序中，创建一个配置文件，配置的名称叫做settings.xml,会自动存储在手机中相应的应用程序下
		//第二个参数为私有模式，代表当前文件不能够被其他应用程序访问
		SharedPreferences sp = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

		//2 获取配置信息,
		// 获取布尔值类型的配置，配置的名称：第一个参数
		boolean b = sp.getBoolean("rememberpass", false);

		//3 显示配置的信息(准备布局)
		chbRemember = (CheckBox) this.findViewById(R.id.chb_remember);
		chbRemember.setChecked(b);

		//4 设置CheckedBox的监听事件,更新设置
		chbRemember.setOnCheckedChangeListener(this);

		usernameEditText = (EditText) this.findViewById(R.id.txt_username);
		pswEditText = (EditText) this.findViewById(R.id.txt_password);

		//5 加载保存的用户名
		usernameEditText.setText(sp.getString("username", ""));
		pswEditText.setText(sp.getString("password", ""));
	}

	//存储配置信息
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		//更新SharedPreferences

		//1 打开SharedPreferences
		SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);

		//2 开始存储配置,必须通过edit()返回的编辑器进行存储
		SharedPreferences.Editor editor = sp.edit();

		//3 通过编辑器进行配置信息的存储
		editor.putBoolean("rememberpass", isChecked);

		//
		if (isChecked)
		{
			String username = usernameEditText.getText().toString();
			editor.putString("username", username);

			String password = pswEditText.getText().toString();
			editor.putString("password", password);
		} else
		{
			editor.remove("username");
			editor.remove("password");
		}
		//4编辑器提交数据
		editor.commit();

	}
}
