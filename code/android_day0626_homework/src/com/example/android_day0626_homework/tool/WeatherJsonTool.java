package com.example.android_day0626_homework.tool;

import java.util.ArrayList;
import java.util.List;

import com.example.android_day0626_homework.R;
import com.example.android_day0626_homework.entity.Weather;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.BitmapFactory;

public class WeatherJsonTool
{
	public static List<Weather> getWeather(String jsonString, Context context)
			throws JSONException
	{
		List<Weather> weathers = new ArrayList<Weather>();
		Weather weather = new Weather();
		Weather weather1 = new Weather();
		Weather weather2 = new Weather();
		Weather weather3 = new Weather();
		Weather weather4 = new Weather();
		Weather weather5 = new Weather();
		Weather weather6 = new Weather();
		try
		{
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject weatherObj = jsonObject.getJSONObject("weatherinfo");
			weather.setWeek(weatherObj.getString("week"));
			weather.setDate(weatherObj.getString("date_y"));
			weather.setImageId(weatherObj.getString("img_single"));
			weather.setWeather(weatherObj.getString("img_title_single"));
			weather.setTemp(weatherObj.getString("temp1"));
			weather.setFl(weatherObj.getString("wd"));

			weather1.setWeek("明日");
			weather1.setDate(weatherObj.getString("date_y"));
			weather1.setImageId(weatherObj.getString("img1"));
			weather1.setWeather(weatherObj.getString("img_title1"));
			weather1.setTemp(weatherObj.getString("temp1"));
			weather1.setFl(weatherObj.getString("wind1"));

			weather2.setWeek("后日");
			weather2.setDate(weatherObj.getString("date_y"));
			weather2.setImageId(weatherObj.getString("img3"));
			weather2.setWeather(weatherObj.getString("img_title3"));
			weather2.setTemp(weatherObj.getString("temp2"));
			weather2.setFl(weatherObj.getString("wind2"));

			weather3.setWeek("星期四");
			weather3.setDate(weatherObj.getString("date_y"));
			weather3.setImageId(weatherObj.getString("img5"));
			weather3.setWeather(weatherObj.getString("img_title5"));
			weather3.setTemp(weatherObj.getString("temp3"));
			weather3.setFl(weatherObj.getString("wind3"));

			weather4.setWeek("星期五");
			weather4.setDate(weatherObj.getString("date_y"));
			weather4.setImageId(weatherObj.getString("img7"));
			weather4.setWeather(weatherObj.getString("img_title7"));
			weather4.setTemp(weatherObj.getString("temp4"));
			weather4.setFl(weatherObj.getString("wind4"));

			weather5.setWeek("星期六");
			weather5.setDate(weatherObj.getString("date_y"));
			weather5.setImageId(weatherObj.getString("img9"));
			weather5.setWeather(weatherObj.getString("img_title9"));
			weather5.setTemp(weatherObj.getString("temp5"));
			weather5.setFl(weatherObj.getString("wind5"));

			weather6.setWeek("星期日");
			weather6.setDate(weatherObj.getString("date_y"));
			weather6.setImageId(weatherObj.getString("img11"));
			weather6.setWeather(weatherObj.getString("img_title11"));
			weather6.setTemp(weatherObj.getString("temp6"));
			weather6.setFl(weatherObj.getString("wind6"));
		}
		catch (Exception e)
		{
		}
		weathers.add(weather);
		weathers.add(weather1);
		weathers.add(weather2);
		weathers.add(weather3);
		weathers.add(weather4);
		weathers.add(weather5);
		weathers.add(weather6);
		return weathers;
	}
}
