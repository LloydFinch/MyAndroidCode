package com.example.android_day0626_fragmentmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MyActivity extends FragmentActivity
{
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//使用FragmentManager来进行Fragment的管理

		// 1 获取FragmentManager,
		FragmentManager fragmentManager = getSupportFragmentManager();

		//2 准备Fragment,通过代码创建Fragment
		HomeFragment homeFragment = new HomeFragment();

		//3 开启事务，准备操作Fragment(增删替换)
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		//3.1 Fragment的操作
		fragmentTransaction.add(R.id.fragment_frame, homeFragment);

		//4 事务提交，Fragment操作生效
		fragmentTransaction.commit();
	}


	//替换当前的Fragment显示为新的Fragment
	public void btnR_onclick(View view)
	{

		//FragmentManager以及准备需要替换新的Fragment
		FragmentManager manager = getSupportFragmentManager();
		SettingFragment fragment = new SettingFragment();

		//开启事务
		FragmentTransaction transaction = manager.beginTransaction();

		//替换指定容器中的Fragment
		transaction.replace(R.id.fragment_frame, fragment);

		//事务提交
		transaction.commit();


	}
}