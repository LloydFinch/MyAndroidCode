package com.example.android_day0626_homework.asynctask;

import java.io.IOException;
import java.net.MalformedURLException;

import com.example.android_day0626_homework.adapter.NewsAdapter;
import com.example.android_day0626_homework.tool.NewsHttpUtil;
import com.example.android_day0626_homework.tool.NewsJsonTool;
import org.json.JSONException;

public class NewsAsyncTask extends android.os.AsyncTask<String, Void, String>
{
    private NewsAdapter adapter;

    public NewsAsyncTask(NewsAdapter adapter)
    {
        super();
        this.adapter = adapter;
    }

    protected String doInBackground(String... params)
    {
        try
        {
            byte[] data = NewsHttpUtil.getDatas(params[0]);
            return new String(data, 0, data.length);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        if (result != null && result.length() > 0)
        {
            try
            {
                adapter.addDatas(NewsJsonTool.getNews(result));
                adapter.notifyDataSetChanged();
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}
