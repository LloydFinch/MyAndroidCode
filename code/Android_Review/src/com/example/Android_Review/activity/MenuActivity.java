package com.example.Android_Review.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import com.example.Android_Review.R;

/**
 * Created by VennUser on 2015/7/23.
 */
public class MenuActivity extends Activity implements PopupMenu.OnMenuItemClickListener {

	private MenuInflater menuInflater;
	private ImageView imageView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		menuInflater = getMenuInflater();
		imageView = (ImageView) this.findViewById(R.id.image_menu);

		registerForContextMenu(imageView);
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		menuInflater.inflate(R.menu.menu_item, menu);

		return true;
	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

		menuInflater.inflate(R.menu.menu_item, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		Log.d("---------->", "title: " + item.getTitle());

		return true;
	}

	public boolean onContextItemSelected(MenuItem item) {

		ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();

		if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			int position = info.position;
		}

		return true;
	}

	public void btnPopMenuOnclick(View view) {

		PopupMenu popupMenu = new PopupMenu(this, view);

		MenuInflater menuInflater = popupMenu.getMenuInflater();

		menuInflater.inflate(R.menu.menu_item, popupMenu.getMenu());

		popupMenu.setOnMenuItemClickListener(this);

		popupMenu.show();
	}

	public boolean onMenuItemClick(MenuItem item) {

		Log.d("----------->", "" + item.getTitle());
		return true;
	}
}