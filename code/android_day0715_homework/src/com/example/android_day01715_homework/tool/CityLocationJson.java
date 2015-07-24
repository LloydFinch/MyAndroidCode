package com.example.android_day01715_homework.tool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by VennUser on 2015/7/16.
 */
public class CityLocationJson {
	public static void jsonCityLocation(String jsonString) {
		try {
			JSONObject municipalities = new JSONObject(jsonString);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
