package com.example.android_day0625_homework;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0625_homework
 * user:VennUser
 * date:2015/6/25
 */
public class MyAdapter extends BaseAdapter
{

	private Context context;
	private static List<String> datas;

	public MyAdapter(Context context)
	{
		this.context = context;
		datas = new ArrayList<String>();
	}

	public static void addData(List<String> data)
	{
		datas.addAll(data);
	}

	public static void removeData(int position)
	{
		datas.remove(position);
	}

	public static void removeDatas(List<Integer> positions)
	{
		for (int i : positions)
		{
			datas.remove(i);
		}
		Log.d("---------------->size", String.valueOf(datas.size()));
	}

	public int getCount()
	{
		return datas.size();
	}

	public Object getItem(int position)
	{
		return datas.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_my, parent, false)
				: convertView;
		TextView textView = (TextView) view.getTag();
		if (textView == null)
		{
			textView = (TextView) view.findViewById(R.id.txt_view);
			view.setTag(textView);
		} else if (textView.getDrawingCacheBackgroundColor() == Color.GREEN)
		{
			textView = (TextView) view.findViewById(R.id.txt_view);
		}
		textView.setText(datas.get(position));

		return view;
	}
}
