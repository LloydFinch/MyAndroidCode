package com.example.android_venn_day0625_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

/**
 * project:com.example.android_venn_day0625_menu
 * user:VennUser
 * date:2015/6/26
 */
public class PopupContext extends Activity implements PopupMenu.OnMenuItemClickListener
{
	private PopupMenu popupMenu;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
	}

	public void btn_onclick(View view)
	{
		if (popupMenu == null)
		{
			popupMenu = new PopupMenu(this, view);
			getMenuInflater().inflate(R.menu.menu_option, popupMenu.getMenu());
		}
		popupMenu.setOnMenuItemClickListener(this);
	}

	public boolean onMenuItemClick(MenuItem item)
	{
		return true;
	}
}