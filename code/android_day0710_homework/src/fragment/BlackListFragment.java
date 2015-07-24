package fragment;

import adapter.BlackAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.android_day0710_homework.R;
import contactsTool.ContactsTool;
import entity.Contacts;

import java.util.List;

/**
 * Created by VennUser on 2015/7/10.
 */
public class BlackListFragment extends Fragment {
	private static Context context;
	private ListView listView;
	private BlackAdapter adapter;
	private List<Contacts> contactsList;
	private MenuInflater menuInflater;

	public BlackListFragment() {
	}

	public static void getContext(Context con) {
		context = con;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		menuInflater = activity.getMenuInflater();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_blacklist, container, false);
		listView = (ListView) view.findViewById(R.id.list_black);

		contactsList = ContactsTool.selectBlack();
		adapter = new BlackAdapter(context, contactsList);
		listView.setAdapter(adapter);
		registerForContextMenu(listView);

		return view;
	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		menuInflater.inflate(R.menu.menu_black_context, menu);
	}

	public boolean onContextItemSelected(MenuItem item) {

		ContextMenu.ContextMenuInfo contextMenuInfo = item.getMenuInfo();
		Contacts contacts = null;
		AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = null;
		if (contextMenuInfo != null && contextMenuInfo instanceof AdapterView.AdapterContextMenuInfo) {
			adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
			contacts = contactsList.get(adapterContextMenuInfo.position);
		}

		switch (item.getItemId()) {
			case R.id.menu_black_delete:
				ContactsTool.deleteBlack(contacts);
				updateAdapter();
				break;
		}
		return true;
	}

	private void updateAdapter() {
		BlackAdapter.updateList(ContactsTool.selectBlack());
		adapter.notifyDataSetChanged();
	}
}