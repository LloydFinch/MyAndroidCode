package com.example.android_day0701_venn_homework.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_day0701_venn_homework.R;
import com.example.android_day0701_venn_homework.entity.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * project:com.example.android_day0701_venn_homework.Adapter
 * user:VennUser
 * date:2015/7/2
 */
public class MessageAdapter extends BaseAdapter {
	private List<Message> messageList;
	private Context context;

	public MessageAdapter(Context context, List<Message> messageList) {
		this.context = context;
		this.messageList = messageList;
	}

	public int getCount() {
		return messageList.size();
	}

	public Object getItem(int position) {
		return messageList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_message, null) : convertView;

		ViewHolder viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.image_message);
			viewHolder.textView0 = (TextView) view.findViewById(R.id.text_sender);
			viewHolder.textView1 = (TextView) view.findViewById(R.id.text_message);
			viewHolder.textView2 = (TextView) view.findViewById(R.id.text_time);
			view.setTag(viewHolder);
		}

		Message message = messageList.get(position);
		String person = message.getPerson();
		String number = message.getNumber();
		viewHolder.textView0.setText(person == null ? number : person);
		String content = message.getContent();
		viewHolder.textView1.setText(content.length() > 10 ? content.substring(0, 10) + "......" : content);
		viewHolder.textView2.setText(new SimpleDateFormat().format(new Date(message.getTime())));
		return view;
	}

	class ViewHolder {
		ImageView imageView;
		TextView textView0, textView1, textView2;
	}
}
