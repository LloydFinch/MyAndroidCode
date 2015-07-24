package com.example.android_day0629_homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.MulticastSocket;
import java.util.List;

/**
 * project:com.example.android_day0629_homework
 * user:VennUser
 * date:2015/6/29
 */
public class FileAdapter extends BaseAdapter {
	private Context context;
	private static List<File> paths;

	public FileAdapter(Context context, List<File> paths) {
		this.context = context;
		this.paths = paths;
	}

	public static void changedDatas(List<File> path) {
		paths = path;
	}

	public int getCount() {
		return paths.size();
	}

	public Object getItem(int position) {
		return paths.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_file, null) : convertView;
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.file_image);
			viewHolder.textView = (TextView) view.findViewById(R.id.file_path);
			view.setTag(viewHolder);
		}

		viewHolder.textView.setText(paths.get(position).getName());
		return view;
	}

	class ViewHolder {
		ImageView imageView;
		TextView textView;
	}
}
