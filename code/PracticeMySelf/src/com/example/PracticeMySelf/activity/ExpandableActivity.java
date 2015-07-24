package com.example.PracticeMySelf.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import com.example.PracticeMySelf.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VennUser on 2015/7/17.
 */
public class ExpandableActivity extends Activity {

	private ExpandableListView expandableListView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expandable);

		expandableListView = (ExpandableListView) this.findViewById(R.id.list_expandable);

		List<String> stringList = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			stringList.add("hello world - " + i);
		}

		expandableListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,
				stringList));
	}
}