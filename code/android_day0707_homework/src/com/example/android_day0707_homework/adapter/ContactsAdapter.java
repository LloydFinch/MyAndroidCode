package com.example.android_day0707_homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_day0707_homework.R;
import com.example.android_day0707_homework.entity.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0701_venn_homework.Adapter
 * user:VennUser
 * date:2015/7/1
 */
public class ContactsAdapter extends BaseAdapter {


	private static List<Contacts> contactslist;
	private Context context;

	public ContactsAdapter(Context context) {
		this.context = context;
		contactslist = new ArrayList<Contacts>();
	}

	public static void updateList(List<Contacts> contactses) {
		contactslist = contactses;
	}

	public int getCount() {
		return contactslist.size();
	}

	public Object getItem(int position) {
		return contactslist.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_contacts, null) : convertView;

		ViewHolder viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.image_photo);
			viewHolder.textView = (TextView) view.findViewById(R.id.text_name);
			view.setTag(viewHolder);
		}

		Contacts contacts = contactslist.get(position);
		viewHolder.textView.setText(contacts.getName() + "   " + contacts.getNumber());
		return view;
	}

	class ViewHolder {
		ImageView imageView;
		TextView textView;
	}
}
