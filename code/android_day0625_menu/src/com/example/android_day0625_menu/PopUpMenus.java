package com.example.android_day0625_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

/**
 * project:com.example.android_day0625_menu
 * user:VennUser
 * date:2015/6/25
 */
public class PopUpMenus extends Activity implements PopupMenu.OnMenuItemClickListener
{
	//使用单例模式：防止当点击按钮多次同时显示多个菜单
	private PopupMenu popUpMenu;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popview);
	}

	//显示弹出菜单,使用单例模式只显示一个
	public void btn_onclick(View view)
	{
		if (popUpMenu == null)
		{
			//创建PopUpMenu并且显示在view的位置
			popUpMenu = new PopupMenu(this, view);

			//添加菜单内容

			//获取加载器
			final MenuInflater menuInflater = popUpMenu.getMenuInflater();

			//需要popUpMenu.getMenu()
			menuInflater.inflate(R.menu.menu_context, popUpMenu.getMenu());

			//设置点击事件
			popUpMenu.setOnMenuItemClickListener(this);
		}
		//显示
		popUpMenu.show();
	}

	//PopUpMenu的点击事件会回调
	//返回类型同前两个菜单
	public boolean onMenuItemClick(MenuItem item)
	{
		int id = item.getItemId();
		switch (id)
		{
			case R.id.edit:
				Toast.makeText(this, "编辑此项？", Toast.LENGTH_LONG).show();
				break;
			case R.id.delete:
				Toast.makeText(this, "删除此项？", Toast.LENGTH_LONG).show();
				break;
		}
		return true;
	}
}