package com.example.android_day0625_menu;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.ContextMenu;
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
 * project:com.example.android_day0625_menu
 * user:VennUser
 * date:2015/6/25
 */
//用于上下文菜单的测试，和OptionMenu加载类似
//1 定义XML布局
//2 注册
//3 加载
//4 实现点击监听
public class ContextMenuActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contextmenu);

		ListView listView = (ListView) this.findViewById(R.id.listview);
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			data.add(i + "hello");
		}
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data));
		//TDOD给ListView设置上下文菜单，每一个条目长安弹出菜单

		//给制定的UI设置上下文菜单
		registerForContextMenu(listView);

		//当上下文菜单需要显示，就会自动调用Activity的onCreateContextMenu方法
	}

	//menu 创建的上下文菜单需要添加到menu部分
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		//加载菜单
		final MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_context, menu);
	}

	//上下文菜单的点击处理
	//当用户点击上下文菜单之后，自动回调此方法
	//true父Activity不在处理，默认返回false
	public boolean onContextItemSelected(MenuItem item) {
		//通过ID来判断菜单
		int id = item.getItemId();

		//上下文菜单特定的附加信息，长按ListView时此值就有了
		final ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();

		//--->ListView特殊的MenuInfo
		//AdapterContextMenuInfo包含了当前显示的第几个位置
		AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = null;
		int position = -1;
		if (menuInfo != null && menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
			adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
			//获取长按的item的位置
			position = adapterContextMenuInfo.position;
		}

		switch (id) {
			case R.id.edit:
				Toast.makeText(this, "编辑此项？" + position, Toast.LENGTH_LONG).show();
				break;
			case R.id.delete:
				Toast.makeText(this, "删除此项？" + position, Toast.LENGTH_LONG).show();
				break;
		}
		return true;
	}
}