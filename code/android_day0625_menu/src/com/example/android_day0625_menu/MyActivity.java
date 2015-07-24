package com.example.android_day0625_menu;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

//当前Activity需要显示菜单,需要通过Activity的方法来加载菜单
//菜单需XML文件来定义
//使用Activity来加载
public class MyActivity extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	//用来给Activity设置菜单,此方法需要重写,自动在内部加载
	//menu:加载的菜单必须放到这个menu内部
	//返回true则显示，否则不显示
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//1 加载菜单,Activity已经包含MenuInflater，可以直接获取
		MenuInflater menuInflater = getMenuInflater();

		//加载res/menu/自己的布局，将生成的菜单放到menu内部
		menuInflater.inflate(R.menu.menu_main, menu);

		//2 返回true
		return true;
	}

	//事件如果在xml中定义就必须实现
	public void search_onclick(MenuItem item)
	{
		//Toast.makeText(this, "search......onclick", Toast.LENGTH_LONG).show();
	}

	//当Activity的optionMenu被点击时会自动调用该方法,默认返回false
	//当返回true代表Activity父类不在处理菜单事件，可以理解为自己处理
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//菜单的处理步骤
		//1 获取菜单信息，通过id获取
		//获取XML文件中设置的id
		int id = item.getItemId();

		//2 判断ID，执行逻辑
		switch (id)
		{
			case R.id.action_search:
				Toast.makeText(this, "search.....option", Toast.LENGTH_LONG).show();
		}
		return true;
	}
}
