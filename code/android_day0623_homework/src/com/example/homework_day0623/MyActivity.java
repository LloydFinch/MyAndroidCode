package com.example.homework_day0623;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.homework.day0623_adapter.MyAdapter;
import com.homework.entity.Content;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity
{
	private GridView gridView;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gridView = (GridView) this.findViewById(R.id.item_grid_view);

		List<Content> contents=new ArrayList<Content>();
		for(int i=0;i<10;i++){
			contents.add(new Content(1,1,"左边"));
		}
		gridView.setAdapter(new MyAdapter(this,contents));
	}
}
