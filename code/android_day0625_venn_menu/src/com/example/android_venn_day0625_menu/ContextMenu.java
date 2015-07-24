package com.example.android_venn_day0625_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_venn_day0625_menu
 * user:VennUser
 * date:2015/6/26
 */
public class ContextMenu extends Activity
{
	private ListView listView;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.context);
		listView = (ListView) this.findViewById(R.id.listview_context);
		List<String> datas = new ArrayList<String>();
		for (int i = 1; i < 20; i++)
		{
			datas.add("hello" + i);
		}
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, datas));
		registerForContextMenu(listView);
	}

	public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_option, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		android.view.ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
		AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) (menuInfo !=
				null && menuInfo
				instanceof AdapterView.AdapterContextMenuInfo ? item.getMenuInfo() : null);
		int position = adapterContextMenuInfo.position;
		String tip = position == R.id.edit_menu ? "edit " + position : "delete " + position;
		Toast.makeText(this, tip, Toast.LENGTH_LONG).show();
		return true;
	}
}