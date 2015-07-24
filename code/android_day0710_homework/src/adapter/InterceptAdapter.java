package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_day0710_homework.R;
import entity.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by VennUser on 2015/7/10.
 */
public class InterceptAdapter extends BaseAdapter {
	private static List<Message> interceptList;
	private Context context;

	public InterceptAdapter(Context context, List<Message> messageList) {
		this.context = context;
		this.interceptList = messageList;
	}

	public static void update(List<Message> intercept) {
		interceptList = intercept;
	}

	public int getCount() {
		return interceptList.size();
	}

	public Object getItem(int position) {
		return interceptList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_intercept, null) : convertView;

		ViewHolder viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.intercept_image_message);
			viewHolder.textView0 = (TextView) view.findViewById(R.id.intercept_text_sender);
			viewHolder.textView1 = (TextView) view.findViewById(R.id.intercept_text_message);
			viewHolder.textView2 = (TextView) view.findViewById(R.id.intercept_text_time);
			view.setTag(viewHolder);
		}

		Message message = interceptList.get(position);
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
