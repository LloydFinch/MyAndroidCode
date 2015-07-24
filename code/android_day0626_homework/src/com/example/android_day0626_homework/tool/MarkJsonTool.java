package com.example.android_day0626_homework.tool;

import com.example.android_day0626_homework.entity.News;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * project:com.example.android_day0626_homework.tool
 * user:VennUser
 * date:2015/6/27
 */
public class MarkJsonTool
{
	public static List<String[]> getInternetList(String jsonString) throws JSONException
	{
		List<String[]> internets = new ArrayList<String[]>();
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONObject object = jsonObject.getJSONObject("object");
		JSONArray internetArray = object.getJSONArray("internet");
		for (int i = 0; i < internetArray.length(); i++)
		{
			JSONObject internet = internetArray.getJSONObject(i);
			internets.add(new String[]{internet.getString("name"), internet.getString("address")});
		}
		return internets;
	}
}
