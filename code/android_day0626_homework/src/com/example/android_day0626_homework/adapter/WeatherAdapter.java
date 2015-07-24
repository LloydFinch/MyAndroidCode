package com.example.android_day0626_homework.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_day0626_homework.R;
import com.example.android_day0626_homework.entity.Weather;


/**
 * project:com.example.android_day0626_homework.adapter
 * user:VennUser
 * date:2015/6/26
 */
public class WeatherAdapter extends BaseAdapter
{
	private List<Weather> weathers;
	private Context context;
	private Map<String, Bitmap> weathe_images;

	public WeatherAdapter(List<Weather> weathers, Context context, Map<String, Bitmap> weathe_images)
	{
		this.weathers = weathers;
		this.context = context;
		this.weathe_images = weathe_images;
	}

	public int getCount()
	{
		return weathers.size();
	}

	public Object getItem(int position)
	{
		return weathers.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView == null ? LayoutInflater.from(context).inflate(R.layout.adapter_weather, null) : convertView;
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null)
		{
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.image_weather);
			viewHolder.textView_date = (TextView) view.findViewById(R.id.info_date);
			viewHolder.textView_weather = (TextView) view.findViewById(R.id.info_weather);
			view.setTag(viewHolder);
		}
		Weather weather = weathers.get(position);
		viewHolder.imageView.setImageBitmap(weathe_images.get(weather.getImageId()));
		viewHolder.textView_date.setText(weather.getDate() + " " + weather.getWeek());
		viewHolder.textView_weather.setText(weather.getWeather() + "  " + weather.getTemp()
				+ "  " + weather.getFl());
		return view;
	}

	class ViewHolder
	{
		ImageView imageView;
		TextView textView_weather, textView_date;
	}
}


