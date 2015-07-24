package com.example.android_day0626_homework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.android_day0626_homework.R;
import com.example.android_day0626_homework.asynctask.MarkAsyncTask;

/**
 * project:com.example.android_day0626_homework.fragment
 * user:VennUser
 * date:2015/6/26
 */
public class MarkFragment extends Fragment
{
	private static Context context;

	public MarkFragment()
	{

	}

	public static void getContext(Context con)
	{
		context = con;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_mark, container, false);
		ListView listView = (ListView) view.findViewById(R.id.listview_mark);

		String path = "http://169.254.226.112:8080/Web/servlet/JsonServlet";
		new MarkAsyncTask(context, listView).execute(path);
		return view;
	}
}