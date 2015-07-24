package com.example.android_day0706_homework.adapter;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.android_day0706_homework.R;

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

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_message, parent, false) :
				convertView;

		TextView textView = (TextView) view.getTag();
		if (textView == null) {
			textView = (TextView) view.findViewById(R.id.text_message);
			view.setTag(textView);
		}
		String flag = messages.get(position)[0];
		if ("222".equals(flag)) {
			textView.setBackground(context.getResources().getDrawable(R.drawable.dialog_1));
		} else if ("333".equals(flag)) {
			textView.setBackground(context.getResources().getDrawable(R.drawable.dialog_2));
		}
		textView.setText(messages.get(position)[1]);
		return view;
	}
}
