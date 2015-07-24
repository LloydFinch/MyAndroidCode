package com.example.android_day0707_homework.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.android_day0707_homework.R;
import com.example.android_day0707_homework.adapter.ContactsAdapter;
import com.example.android_day0707_homework.entity.Contacts;
import com.example.android_day0707_homework.loader.ContactsLoader;

import java.util.List;

/**
 * project:com.example.android_day0707_homework.fragment
 * user:VennUser
 * date:2015/7/7
 */
public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Contacts>> {

	private static Context context;
	private ListView listView;
	private ContactsAdapter adapter;

	public ContactsFragment() {
	}

	public static void getContext(Context con) {
		context = con;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contacts, container, false);
		listView = (ListView) view.findViewById(R.id.list_contacts);
		adapter = new ContactsAdapter(context);
		listView.setAdapter(adapter);

		LoaderManager manager = getLoaderManager();
		Bundle bundle = new Bundle();
		bundle.putParcelable("uri", ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
		manager.initLoader(123, bundle, this);

		return view;
	}

	public Loader<List<Contacts>> onCreateLoader(int id, Bundle bundle) {
		ContactsLoader loader = new ContactsLoader(context);
		if (bundle != null) {
			loader.setUri((Uri) bundle.getParcelable("uri"));
		}
		return loader;
	}

	public void onLoadFinished(Loader<List<Contacts>> loader, List<Contacts> data) {
		ContactsAdapter.updateList(data);
		adapter.notifyDataSetChanged();
	}

	public void onLoaderReset(Loader<List<Contacts>> loader) {

	}
}