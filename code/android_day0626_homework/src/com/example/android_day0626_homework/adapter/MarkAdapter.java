package com.example.android_day0626_homework.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.android_day0626_homework.R;

import java.util.List;
import java.util.Properties;

/**
 * project:com.example.android_day0626_homework.adapter
 * user:VennUser
 * date:2015/6/27
 */
public class MarkAdapter extends BaseAdapter
{
	private List<String[]> names;
	private Context context;

	public MarkAdapter(Context context, List<String[]> names)
	{
		this.context = context;
		this.names = names;
	}

	public int getCount()
	{
		return names.size();
	}

	public Object getItem(int position)
	{
		return position;
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_mark, null) : convertView;
		TextView textView = (TextView) view.getTag();
		if (textView == null)
		{
			textView = (TextView) view.findViewById(R.id.mark_textview);
			view.setTag(textView);
		}
		String name = names.get(position)[0];
		String address = names.get(position)[1];
		setSpan(textView, name, address);
		return view;
	}

	private void setSpan(TextView textView, String name, String address)
	{
		//创建一个 SpannableString对象
		SpannableString sp = new SpannableString(name);
		//设置超链接
		sp.setSpan(new URLSpan(address), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//SpannableString对象设置给TextView
		textView.setText(sp);
		//设置TextView可点击
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
