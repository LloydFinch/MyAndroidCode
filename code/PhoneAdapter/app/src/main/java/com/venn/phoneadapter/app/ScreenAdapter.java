package com.venn.phoneadapter.app;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//支持横竖屏切换的Activity
public class ScreenAdapter extends ActionBarActivity implements AdapterView.OnItemClickListener, ContentFragment
		.OnFragmentInteractionListener {

	private ListView listView;
	private FrameLayout frameLayout;
	private ContentFragment fragment;
	private List<String> list;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_adapter);

		listView = (ListView) this.findViewById(R.id.list_news);
		frameLayout = (FrameLayout) this.findViewById(R.id.fragment_content);

		//判断横屏还是竖屏
		if (frameLayout != null) {
			fragment = new ContentFragment();

			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.add(R.id.fragment_content, fragment);
			transaction.commit();
		}

		list = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			list.add("Android" + i);
		}

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String title = list.get(position);
		if (fragment != null) {
			fragment.setText(title, title);
		} else {
			Intent intent = new Intent(this, DetailActivity.class);
			intent.putExtra("title", title);
			intent.putExtra("content", title);
			startActivity(intent);
		}
	}

	public void onFragmentInteraction(Uri uri) {

	}
}