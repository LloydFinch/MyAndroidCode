package com.example.android_day0626_homework.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import com.example.android_day0626_homework.adapter.MarkAdapter;
import com.example.android_day0626_homework.tool.MarkHttpUtil;
import com.example.android_day0626_homework.tool.MarkJsonTool;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * project:com.example.android_day0626_homework.asynctask
 * user:VennUser
 * date:2015/6/27
 */
public class MarkAsyncTask extends AsyncTask<String, Void, List<String[]>>
{
	private ListView listView;
	private Context context;

	public MarkAsyncTask(Context context, ListView listView)
	{
		this.context = context;
		this.listView = listView;
	}

	protected List<String[]> doInBackground(String... params)
	{
		List<String[]> list = null;
		try
		{
			list = MarkJsonTool.getInternetList(MarkHttpUtil.getDatas(params[0]));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	protected void onPostExecute(List<String[]> strings)
	{
		listView.setAdapter(new MarkAdapter(context, strings));
	}
}
