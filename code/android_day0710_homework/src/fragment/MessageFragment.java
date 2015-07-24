package fragment;

import adapter.MessageAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android_day0710_homework.R;
import contactsTool.ContactsTool;
import entity.Message;

import java.util.List;

/**
 * project:com.example.android_day0701_venn_homework.fragment
 * user:VennUser
 * date:2015/7/2
 */
public class MessageFragment extends Fragment implements AdapterView.OnItemClickListener {

	private static Context context;
	private MenuInflater menuInflater;
	private ListView listView;
	private MessageAdapter adapter;
	private List<Message> messageList;

	public void MessageFragment() {

	}

	public static void getContext(Context con) {
		context = con;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		menuInflater = activity.getMenuInflater();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, container, false);
		listView = (ListView) view.findViewById(R.id.list_message);
		messageList= ContactsTool.getMessage(context);
		adapter = new MessageAdapter(context, messageList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		return view;
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TextView textView2 = (TextView) view.findViewById(R.id.text_time);
		String time = textView2.getText().toString();
		MesDetailFragment.getContext(context);
		MesDetailFragment.getMessage(messageList.get(position).getContent(), time);

//		FragmentManager manager = getFragmentManager();
//		FragmentTransaction transaction = manager.beginTransaction();
//		transaction.replace(R.id.fraglayout, new MesDetailFragment());
//		transaction.commit();
	}
}