package com.venn.initateweixin.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.venn.initateweixin.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0706_homework.adapter
 * user:VennUser
 * date:2015/7/6
 */
public class MessageAdapter extends BaseAdapter {

	private Context context;
	private static List<String[]> messages;

	public MessageAdapter(Context context) {
		this.context = context;
		messages = new ArrayList<String[]>();
		messages.add(new String[]{"333", "开始聊天"});
	}

	public static void addMessage(String[] message) {
		messages.add(message);
	}

	public int getCount() {
		return messages.size();
	}

	public Object getItem(int position) {
		return messages.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public int getViewTypeCount() {
		return 2;
	}

	public int getItemViewType(int position) {
		int type = 1;
		switch (position % 2) {
			case 0:
				type = 0;
				break;
			case 1:
				type = 1;
				break;
		}
		return type;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder1 viewHolder1 = null;
		ViewHolder2 viewHolder2 = null;
		TextView textView = null;
		int type = getItemViewType(position);
		if (convertView == null) {
			switch (type) {
				case 1:
					convertView = LayoutInflater.from(context).inflate(R.layout.adapter_message, parent,
							false);
					viewHolder1 = new ViewHolder1();
					viewHolder1.textView = (TextView) convertView.findViewById(R.id.text_message);
					viewHolder1.imageView = (ImageView) convertView.findViewById(R.id.image_left);
					convertView.setTag(viewHolder1);
					textView = viewHolder1.textView;
					break;
				case 0:
					convertView = LayoutInflater.from(context).inflate(R.layout.adapter_receive, parent,
							false);
					viewHolder2 = new ViewHolder2();
					viewHolder2.textView = (TextView) convertView.findViewById(R.id.text_message);
					viewHolder2.imageView = (ImageView) convertView.findViewById(R.id.image_left);
					convertView.setTag(viewHolder2);
					textView = viewHolder2.textView;
					break;
			}
		} else {
			switch (type) {
				case 1:
					viewHolder1 = (ViewHolder1) convertView.getTag();
					textView = viewHolder1.textView;
					break;
				case 0:
					viewHolder2 = (ViewHolder2) convertView.getTag();
					textView = viewHolder2.textView;
					break;
			}
		}

		String flag = messages.get(position)[0];
		if ("222".equals(flag)) {
			textView.setBackground(context.getResources().getDrawable(R.drawable.talk));
		} else if ("333".equals(flag)) {
			textView.setBackground(context.getResources().getDrawable(R.drawable.talk3));
		}
		textView.setText(messages.get(position)[1]);
		return convertView;
	}

	class ViewHolder1 {
		TextView textView;
		ImageView imageView;
	}

	class ViewHolder2 {
		TextView textView;
		ImageView imageView;
	}
}
