package com.venn.myapplication4.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import com.venn.myapplication4.app.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

	private ListView listView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) this.findViewById(R.id.list_content);

		List<ChatMessage> messageList = new ArrayList<ChatMessage>();
		for (int i = 0; i < 20; i++) {
			ChatMessage message = new ChatMessage();
			message.setContent("sender" + i);
			if (i % 2 == 0) {
				message.setSelf(true);
			} else {
				message.setSelf(false);
			}
			messageList.add(message);
		}

		MyAdapter adapter = new MyAdapter(messageList, this);
		listView.setAdapter(adapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
