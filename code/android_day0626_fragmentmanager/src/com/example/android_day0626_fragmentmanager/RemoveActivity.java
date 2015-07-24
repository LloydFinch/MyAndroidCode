package com.example.android_day0626_fragmentmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;
import com.example.android_day0626_fragmentmanager.fragment.NewsFragment;

/**
 * project:com.example.android_day0626_fragmentmanager
 * user:VennUser
 * date:2015/6/26
 */
public class RemoveActivity extends FragmentActivity
{
	private NewsFragment fragment;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove);
	}

	//添加Fragment
	public void btnAddOnclick(View view)
	{
		if (fragment == null)
		{
			fragment = new NewsFragment();
		}
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.remove_frame, fragment, "home");
		transaction.commit();
	}

	//删除Fragment
	public void btnRemoveOnclick(View view)
	{
		if (fragment != null)
		{
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.remove(fragment);
			transaction.commit();
		} else
			Toast.makeText(this, "Fragment is Empty !", Toast.LENGTH_LONG).show();
	}

	public void btnRemoveOnclickByTag(View view)
	{
		FragmentManager manager = getSupportFragmentManager();

		//通过查找Tag来找到Fragment从而进行删除
		Fragment fragment = manager.findFragmentByTag("home");
		if (fragment != null)
		{
			FragmentTransaction transaction = manager.beginTransaction();

			transaction.remove(fragment);
			transaction.commit();
		} else
			Toast.makeText(this, "Fragment is Empty !", Toast.LENGTH_LONG).show();
	}
}