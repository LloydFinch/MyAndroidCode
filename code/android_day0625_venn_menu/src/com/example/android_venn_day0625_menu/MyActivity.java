package com.example.android_venn_day0625_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.security.KeyStore;

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

	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_option, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		String tip = item.getItemId() == R.id.edit_menu ? "are you sure to edit?" : "are you sure to delete?";
		Toast.makeText(this, tip, Toast.LENGTH_LONG).show();
		return true;
	}
}
