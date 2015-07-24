package com.homework.day0623_adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.homework_day0623.R;
import com.homework.entity.Content;

import java.util.List;

/**
 * project:com.homework.day0623
 * user:VennUser
 * date:2015/6/23
 */
public class MyAdapter extends BaseAdapter
{
	private Context context;
	private List<Content> contents;
	private ViewHolder viewHolder;
	public MyAdapter(Context context, List<Content> imagesId)
	{
		this.context = context;
		this.contents = imagesId;
	}

	public int getCount()
	{
		return contents.size();
	}

	public Object getItem(int position)
	{
		return contents.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.view, null) : convertView;
		viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null)
		{
			viewHolder = new ViewHolder();
			viewHolder.centerImageView = (ImageView) view.findViewById(R.id.item_image_view);
			viewHolder.starCheckBox = (CheckBox) view.findViewById(R.id.item_image_star);
			viewHolder.leftTextView = (TextView) view.findViewById(R.id.item_text_view_left);
			view.setTag(viewHolder);
		}
		viewHolder.leftTextView.setText(contents.get(position).getMessage());
		viewHolder.centerImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.a));

		return view;
	}

	private static class ViewHolder
	{
		ImageView centerImageView;
		TextView leftTextView;
		CheckBox starCheckBox;
	}
}
