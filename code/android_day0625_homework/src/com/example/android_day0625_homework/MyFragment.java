package com.example.android_day0625_homework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.*;
import android.widget.*;
import org.apache.http.impl.io.ContentLengthInputStream;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0625_homework
 * user:VennUser
 * date:2015/6/25
 */
public class MyFragment extends android.support.v4.app.Fragment
{

	private static Context context;
	private MenuInflater menuInflater;
	private ListView listView;
	private MyAdapter adapter;
	private List<Integer> selects = new ArrayList<Integer>();
	private TextView currentTextView;

	public static void getContext(Context con)
	{
		context = con;
	}

	public MyFragment()
	{

	}

	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		menuInflater = activity.getMenuInflater();
	}

	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View view = inflater.inflate(R.layout.fragment_listview, container, false);
		listView = (ListView) view.findViewById(R.id.listview);
		List<String> lists = new ArrayList<String>();
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				int relativePosition = position - listView.getFirstVisiblePosition();
				selects.add(relativePosition);
				View currentView = listView.getChildAt(relativePosition);
				currentTextView = (TextView) currentView.findViewById(R.id.txt_view);
				currentTextView.setBackgroundColor(Color.GREEN);
			}
		});
		for (int i = 0; i < 30; i++)
		{
			lists.add("hello" + i);
		}
		adapter = new MyAdapter(context);
		MyAdapter.addData(lists);
		listView.setAdapter(adapter);
		registerForContextMenu(listView);
		return view;
	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
	{
		menuInflater.inflate(R.menu.menu_context, menu);
	}

	public boolean onContextItemSelected(MenuItem item)
	{

		ContextMenu.ContextMenuInfo contextMenuInfo = item.getMenuInfo();
		AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = null;

		int position = -1;
		if (contextMenuInfo != null && contextMenuInfo instanceof AdapterView.AdapterContextMenuInfo)
		{
			adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
			position = adapterContextMenuInfo.position;
		}

		switch (item.getItemId())
		{
			case R.id.edit:
				break;
			case R.id.delete:
				MyAdapter.removeData(position);
				adapter.notifyDataSetChanged();
				break;
		}
		return true;
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.menu_context, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.delete)
		{
			MyAdapter.removeDatas(selects);
			currentTextView.setBackgroundColor(Color.BLUE);
			adapter.notifyDataSetChanged();
		}
		return true;
	}
}
