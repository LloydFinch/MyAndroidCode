package com.example.android_day0708_homework.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android_day0708_homework.R;
import com.example.android_day0708_homework.adapter.NewsAdapter;
import com.example.android_day0708_homework.asynctask.NewsAsyncTask;
import com.example.android_day0708_homework.constant.ConstantInterface;

/**
 * Created by VennUser on 2015/7/9.
 */
public class SubNewsFragment extends Fragment {


	private static Context context;
	private int pageIndex = 1;
	private NewsAdapter adapter;

	public SubNewsFragment() {
	}

	public static void getContext(Context con) {
		context = con;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_subnews, container, false);
		ListView listView = (ListView) view.findViewById(R.id.listview_news);
		adapter = new NewsAdapter(context);
		new NewsAsyncTask(adapter)
				.execute(ConstantInterface.PATH + (pageIndex++) + ConstantInterface.PATH_LAST);
		listView.setAdapter(adapter);

		listView.setOnScrollListener(new AbsListView.OnScrollListener() {
			boolean isBottom = false;

			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (isBottom && scrollState == SCROLL_STATE_IDLE) {
					Toast.makeText(context, "Loading..........",
							Toast.LENGTH_SHORT).show();
					new NewsAsyncTask(adapter).execute(ConstantInterface.PATH + (pageIndex++)
							+ ConstantInterface.PATH_LAST);
				}
			}

			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				isBottom = ((firstVisibleItem + visibleItemCount) == totalItemCount);
			}
		});
		return view;
	}
}