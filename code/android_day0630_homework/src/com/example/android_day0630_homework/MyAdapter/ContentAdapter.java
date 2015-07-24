package com.example.android_day0630_homework.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.android_day0630_homework.Entity.Account;
import com.example.android_day0630_homework.R;

import java.util.List;

/**
 * project:com.example.android_day0630_homework.MyAdapter
 * user:VennUser
 * date:2015/6/30
 */
public class ContentAdapter extends BaseAdapter {

	private static List<Account> datas;
	private Context context;

	public ContentAdapter(Context context, List<Account> datas) {
		this.context = context;
		this.datas = datas;
	}

	public static void addDatas(List<Account> data) {
		datas.addAll(data);
	}

	public static void removeData(int position) {
		datas.remove(position);
	}

	public int getCount() {
		return datas.size();
	}

	public Object getItem(int position) {
		return datas.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adaptercontent, null) :
				convertView;
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.checkBox = (CheckBox) view.findViewById(R.id.checkbox_state);
			viewHolder.textView = (TextView) view.findViewById(R.id.text_content);
			view.setTag(viewHolder);
		}
		Account account = datas.get(position);
		viewHolder.textView.setText("ID:" + account.getId()
				+ "时间:" + account.getTime() + "\n"
				+ "数额:" + account.getMoney()
				+ "收支类型:" + account.getType() + "\n"
				+ "消费类型:" + account.getCategory());

		return view;
	}

	class ViewHolder {
		CheckBox checkBox;
		TextView textView;
	}
}
