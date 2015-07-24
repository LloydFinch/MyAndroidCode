package com.venn.myapplication4.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.venn.myapplication4.app.ChatMessage;
import com.venn.myapplication4.app.R;

import java.util.List;

/**
 * Created by VennUser on 2015/7/18.
 */
public class MyAdapter extends BaseAdapter {

	List<ChatMessage> messageList;
	private Context context;

	public MyAdapter(List<ChatMessage> messageList, Context context) {
		this.messageList = messageList;
		this.context = context;
	}

	public int getCount() {

		Log.d("=========>", "getCount");
		return messageList.size();
	}

	//获取指定位置的数据对象
	public Object getItem(int position) {
		Log.d("=========>", "getItem");
		return messageList.get(position);
	}

	//对于非数据库或者数据没有唯一标识的直接position即可
	//对于数据库就需要但会id
	public long getItemId(int position) {
		Log.d("=========>", "getItemId");
		return position;
	}

	//在getView之前,先获取当前item显示的类型
	//返回值的数值[0~~getCount()-1]
	public int getItemViewType(int position) {

		Log.d("=========>", "getItemViewType");
		ChatMessage message = messageList.get(position);
		int type = 1;
		if (message.isSelf()) {
			type = 0;
		} else {
			type = 1;
		}
		return type;
	}

	//获取listView能够接收的布局种类数量
	//返回的数据要大于等于1
	public int getViewTypeCount() {
		Log.d("=========>", "getViewTypeCount");
		return 2;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		Log.d("=========>", "getView");
		View view = null;

		int type = getItemViewType(position);

		if (convertView != null) {

			view = convertView;
		} else {
			LayoutInflater inflater = LayoutInflater.from(context);
			switch (type) {
				case 0:
					view = inflater.inflate(R.layout.chat_left, parent, false);
					break;
				case 1:
					view = inflater.inflate(R.layout.chat_right, parent, false);
					break;
			}
			if (view != null) {

				//TODO 进行ViewHolder的处理
				ViewHolder viewHolder = new ViewHolder();
				switch (type) {
					case 1:
						viewHolder = (ViewHolder) view.getTag();
						break;

					case 2:
						viewHolder = (ViewHolder) view.getTag();
						break;
				}
				if (viewHolder == null) {
					viewHolder = new ViewHolder();
					view.setTag(viewHolder);
				}

				viewHolder.textView = (TextView) view.findViewById(R.id.text_message);
				ChatMessage message = messageList.get(position);

				viewHolder.textView.setText(message.getContent());
			}
		}

		return view;
	}

	static class ViewHolder {
		TextView textView;
	}
}
