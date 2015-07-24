package com.example.android_day0626_homework.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import com.example.android_day0626_homework.R;
import com.example.android_day0626_homework.asynctask.WeatherAsnyTask;
import com.example.android_day0626_homework.entity.City;
import com.example.android_day0626_homework.entity.District;
import com.example.android_day0626_homework.entity.Province;
import com.example.android_day0626_homework.tool.WeatherXmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * project:com.example.android_day0626_homework.fragment
 * user:VennUser
 * date:2015/6/26
 */
public class WeatherFragment extends Fragment
{
	private static Context context;
	private Spinner provinceSpinner, citySpinner, distinctSpinner;
	private ListView listView;
	private List<Province> provinces;
	private Map<String, Bitmap> images_weather;
	private Province province;
	private City city;
	private ArrayAdapter<Province> proAdapter;
	private ArrayAdapter<City> cityAdapter;
	private ArrayAdapter<District> disAdapter;

	public WeatherFragment()
	{

	}

	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		images_weather = new HashMap<String, Bitmap>();
		for (int i = 0; i <= 31; i++)
		{
			images_weather.put(String.valueOf(i), getBitmap(R.drawable.d00 + i));
		}
	}

	private Bitmap getBitmap(int id)
	{
		return BitmapFactory.decodeResource(getResources(), id);
	}

	public static void getContext(Context con)
	{
		context = con;
	}

	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_weather, container, false);
		listView = (ListView) view.findViewById(R.id.listview_weather);
		provinceSpinner = (Spinner) view.findViewById(R.id.province);
		citySpinner = (Spinner) view.findViewById(R.id.city);
		distinctSpinner = (Spinner) view.findViewById(R.id.distinct);

		InputStream inputStream = getResources().openRawResource(R.raw.citys_weather);
		try
		{
			provinces = WeatherXmlPullParser.getParserList(inputStream);
		}
		catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		proAdapter = new ArrayAdapter<Province>(context,
				android.R.layout.simple_expandable_list_item_1, provinces);
		provinceSpinner.setAdapter(proAdapter);
		provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				province = provinces.get(position);
				cityAdapter = new ArrayAdapter<City>(context,
						android.R.layout.simple_expandable_list_item_1, province
						.getCities());
				citySpinner.setAdapter(cityAdapter);
			}

			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
		citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				city = province.getCities().get(position);
				disAdapter = new ArrayAdapter<District>(context,
						android.R.layout.simple_expandable_list_item_1, city
						.getDistricts());
				distinctSpinner.setAdapter(disAdapter);
			}

			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		distinctSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)

			{
				District district = city.getDistricts().get(position);
				String path = "http://weather.123.duba.net/static/weather_info/" + district.getId()
						+ ".html";
				new WeatherAsnyTask(context, listView, images_weather).execute(path);
			}

			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
		return view;
	}
}