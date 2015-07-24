package com.example.android_day0626_homework.asynctask;

import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import com.example.android_day0626_homework.adapter.WeatherAdapter;
import com.example.android_day0626_homework.entity.Weather;
import com.example.android_day0626_homework.tool.WeatherHttpUtil;
import com.example.android_day0626_homework.tool.WeatherJsonTool;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class WeatherAsnyTask extends AsyncTask<String, Void, String>
{
	private Context context;
	private ListView listView;
	private Map<String, Bitmap> weathe_images;


	public WeatherAsnyTask(Context context, ListView listView, Map<String, Bitmap> weathe_images
	)
	{
		this.context = context;
		this.listView = listView;
		this.weathe_images = weathe_images;
	}

	protected String doInBackground(String... params)
	{
		String jsonString = null;
		try
		{
			jsonString = WeatherHttpUtil.getJsonString(params[0], "utf-8");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return jsonString;
	}

	protected void onPostExecute(String result)
	{
		List<Weather> weathers = null;

		try
		{
			weathers = WeatherJsonTool.getWeather(result, context);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		listView.setAdapter(new WeatherAdapter(weathers, context, weathe_images));
		super.onPostExecute(result);
	}
}
