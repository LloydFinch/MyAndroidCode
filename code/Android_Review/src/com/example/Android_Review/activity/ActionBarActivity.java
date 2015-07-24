package com.example.Android_Review.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ShareActionProvider;
import com.example.Android_Review.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VennUser on 2015/7/23.
 */
public class ActionBarActivity extends Activity implements ActionBar.OnNavigationListener, ActionBar.TabListener {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actionbar);

		ActionBar actionBar = this.getActionBar();

		actionBar.setTitle("Actionbar");
		actionBar.setIcon(R.drawable.ic_launcher);
		actionBar.setDisplayHomeAsUpEnabled(true);

//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//
//		List<String> list = new ArrayList<String>();
//		list.add("按天");
//		list.add("按周");
//		list.add("按月");
//		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list);
//
//		actionBar.setListNavigationCallbacks(adapter, this);

//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//
//		ActionBar.Tab tab1 = actionBar.newTab();
//		tab1.setIcon(R.drawable.ic_launcher);
//		tab1.setTabListener(this);
//
//		ActionBar.Tab tab2 = actionBar.newTab();
//		tab2.setIcon(R.drawable.ic_launcher);
//		tab2.setTabListener(this);
//
//		ActionBar.Tab tab3 = actionBar.newTab();
//		tab3.setIcon(R.drawable.ic_launcher);
//		tab3.setTabListener(this);
//
//		actionBar.addTab(tab1);
//		actionBar.addTab(tab2);
//		actionBar.addTab(tab3);

	}

	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_share, menu);

		MenuItem item = menu.findItem(R.id.action_share);
		ShareActionProvider actionProvider = (ShareActionProvider) item.getActionProvider();
		Intent intent = new Intent(Intent.ACTION_SEND);

		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "你是250!");
		actionProvider.setShareIntent(intent);

		return true;
	}

	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		return false;
	}

	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

	}
}