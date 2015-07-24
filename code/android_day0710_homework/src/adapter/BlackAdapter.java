package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_day0710_homework.R;
import entity.Contacts;

import java.util.List;

/**
 * Created by VennUser on 2015/7/10.
 */
public class BlackAdapter extends BaseAdapter {
	private static List<Contacts> blackList;
	private Context context;

	public BlackAdapter(Context context, List<Contacts> contactslsit) {
		this.context = context;
		this.blackList = contactslsit;
	}

	public static void updateList(List<Contacts> contactses) {
		blackList = contactses;
	}

	public int getCount() {
		return blackList.size();
	}

	public Object getItem(int position) {
		return blackList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_black, null) : convertView;

		ViewHolder viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.image_black_photo);
			viewHolder.textView = (TextView) view.findViewById(R.id.text_black_name);
			view.setTag(viewHolder);
		}

		Contacts contacts = blackList.get(position);
		viewHolder.textView.setText(contacts.getName() + "   " + contacts.getPhoneNumber());
		return view;
	}

	class ViewHolder {
		ImageView imageView;
		TextView textView;
	}
}
