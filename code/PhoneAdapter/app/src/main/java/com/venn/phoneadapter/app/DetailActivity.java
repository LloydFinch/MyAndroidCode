package com.venn.phoneadapter.app;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class DetailActivity extends ActionBarActivity implements ContentFragment.OnFragmentInteractionListener {

	private ContentFragment fragment;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		FragmentManager manager = getSupportFragmentManager();
		//FragmentTransaction transaction = manager.beginTransaction();

		fragment = (ContentFragment) manager.findFragmentById(R.id.frame_detail);

		//transaction.add(R.id.fragment_content, fragment);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	protected void onResume() {
		super.onResume();

		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		String content = intent.getStringExtra("content");

		fragment.setText(title, content);
	}

	@Override
	public void onFragmentInteraction(Uri uri) {

	}
}
