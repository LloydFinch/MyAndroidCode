package com.example.android_day0707_homework.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.android_day0707_homework.R;
import com.example.android_day0707_homework.adapter.MessageAdapter;
import com.example.android_day0707_homework.entity.Message;
import com.example.android_day0707_homework.loader.MessageLoader;

import java.util.List;

/**
 * project:com.example.android_day0707_homework.fragment
 * user:VennUser
 * date:2015/7/7
 */
public class MessageFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Message>> {

	private static Context context;
	private ListView listView;
	private MessageAdapter adapter;

	public MessageFragment() {
	}

	public static void getContext(Context con) {
		context = con;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, container, false);
		listView = (ListView) view.findViewById(R.id.list_message);
		adapter = new MessageAdapter(context);
		listView.setAdapter(adapter);

		LoaderManager manager = getLoaderManager();
		Bundle bundle = new Bundle();
		bundle.putParcelable("uri", Telephony.Sms.CONTENT_URI);
		manager.initLoader(333, bundle, this);
		return view;
	}

	public Loader<List<Message>> onCreateLoader(int id, Bundle bundle) {
		MessageLoader loader = new MessageLoader(context);
		if (bundle != null) {
			loader.setUri((Uri) bundle.getParcelable("uri"));
		}
		return loader;
	}

	public void onLoadFinished(Loader<List<Message>> loader, List<Message> data) {
		MessageAdapter.updateMessage(data);
		adapter.notifyDataSetChanged();
	}

	public void onLoaderReset(Loader<List<Message>> loader) {

	}
}