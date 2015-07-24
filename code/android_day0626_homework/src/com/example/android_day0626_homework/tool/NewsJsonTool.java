package com.example.android_day0626_homework.tool;

/**
 * project:com.example.android_day0626_homework.tool
 * user:VennUser
 * date:2015/6/26
 */
import java.util.ArrayList;
import java.util.List;

import com.example.android_day0626_homework.entity.News;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsJsonTool
{
	public static List<News> getNews(String jsonString) throws JSONException
	{
		List<News> news = new ArrayList<News>();
		JSONObject jsonObject = new JSONObject(jsonString);
		if ("ok".equals(jsonObject.getString("status")))
		{
			JSONObject paramz = jsonObject.getJSONObject("paramz");
			JSONArray feeds = paramz.getJSONArray("feeds");
			for (int i = 0; i < feeds.length(); i++)
			{
				JSONObject obj = feeds.getJSONObject(i);
				JSONObject data = obj.getJSONObject("data");
				String subject = data.getString("subject");
				String summary = data.getString("summary");
				String cover = data.getString("cover");
				String changed = data.getString("changed");

				news.add(new News(subject, summary, cover, changed));
			}
		}
		return news;
	}
}