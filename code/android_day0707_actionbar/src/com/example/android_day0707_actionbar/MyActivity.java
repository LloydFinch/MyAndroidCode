package com.example.android_day0707_actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//get ActionBar
		ActionBar actionBar = getActionBar();

		//create Drawable
		ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);

		//set Drawable
		actionBar.setIcon(colorDrawable);

		//set return icon left of Logo
		actionBar.setDisplayHomeAsUpEnabled(true);

		//set option
		actionBar.setDisplayShowTitleEnabled(false);


	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_main, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_edit:
				Toast.makeText(this, "点击了编辑", Toast.LENGTH_LONG).show();
				break;
			case R.id.action_delete:
				Toast.makeText(this, "点击了删除", Toast.LENGTH_LONG).show();
				break;
			case android.R.id.home:
				Toast.makeText(this, "你点击了返回键", Toast.LENGTH_SHORT).show();
				finish();
		}
		return true;
	}
}
