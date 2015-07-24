package fragment;

import adapter.InterceptAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.android_day0710_homework.R;
import contactsTool.ContactsTool;
import entity.Message;

import java.util.List;

/**
 * Created by VennUser on 2015/7/10.
 */
public class InterceptFragment extends Fragment {
	private ListView listView;
	private static InterceptAdapter adapter;
	private static Context context;
	private List<Message> interceptList;

	public InterceptFragment() {

	}

	public static void getContext(Context con) {
		context = con;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_intercept, container, false);
		listView = (ListView) view.findViewById(R.id.list_intercept);
		interceptList = ContactsTool.selectIntercept();
		adapter = new InterceptAdapter(context, interceptList);
		Log.d("------->", interceptList.toString());
		listView.setAdapter(adapter);
		return view;
	}

	public static void update() {
		adapter.update(ContactsTool.selectIntercept());
		adapter.notifyDataSetChanged();
	}
}