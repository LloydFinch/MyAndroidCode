package com.example.android_day0626_fragmentmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.example.android_day0626_fragmentmanager.fragment.LoginFragment;
import com.example.android_day0626_fragmentmanager.fragment.RegisterFragment;
import com.example.android_day0626_fragmentmanager.fragment.RegisterFragmentStatus;

/**
 * project:com.example.android_day0626_fragmentmanager
 * user:VennUser
 * date:2015/6/26
 */
public class BackStackActivity extends FragmentActivity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_backstack);
	}

	public void login_onclick(View view)
	{
		LoginFragment loginFragment = new LoginFragment();
		FragmentManager supportFragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = supportFragmentManager.beginTransaction();

		//指定Tag
		transaction.replace(R.id.register_container, loginFragment, "login");

		//记录回退栈，指定名称为login
		transaction.addToBackStack("login");
		transaction.commit();
	}

	public void register_onclick(View view)
	{
		RegisterFragment registerFragment = new RegisterFragment();
		FragmentManager supportFragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = supportFragmentManager.beginTransaction();
		transaction.replace(R.id.register_container, registerFragment, "register");
		transaction.addToBackStack(null);
		transaction.commit();
	}

	public void status_onclick(View view)
	{
		RegisterFragmentStatus registerFragmentStatus = new RegisterFragmentStatus();
		FragmentManager supportFragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = supportFragmentManager.beginTransaction();
		transaction.replace(R.id.register_container, registerFragmentStatus, "status");
		transaction.addToBackStack(null);
		transaction.commit();
	}

	public void onBackPressed()
	{
		//TODO 处理点击后退，返回Fragment指定的位置，从Status直接跳到Login
		FragmentManager supportFragmentManager = getSupportFragmentManager();
		Fragment fragment = supportFragmentManager.findFragmentByTag("status");

		//判断当前的Fragment是否正在显示
		if (fragment != null && fragment.isVisible())
		{
			//supportFragmentManager.popBackStack("login", 0);
			if (!supportFragmentManager.popBackStackImmediate("login", FragmentManager.POP_BACK_STACK_INCLUSIVE))
				super.onBackPressed();
		} else
		{
			super.onBackPressed();
		}
		supportFragmentManager.popBackStack("",0);
	}
}