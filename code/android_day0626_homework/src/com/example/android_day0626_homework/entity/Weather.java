package com.example.android_day0626_homework.entity;

import android.graphics.Bitmap;

public class Weather
{
	private String week;
	private String date;
	private String weather;
	private String imageId;
	private String fl;
	private String temp;

	public Weather()
	{
		super();
	}

	public Weather(String date, String fl, String imageId, String temp, String weather, String week)
	{
		this.date = date;
		this.fl = fl;
		this.imageId = imageId;
		this.temp = temp;
		this.weather = weather;
		this.week = week;
	}

	public String getWeek()
	{
		return week;
	}

	public void setWeek(String week)
	{
		this.week = week;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getWeather()
	{
		return weather;
	}

	public void setWeather(String weather)
	{
		this.weather = weather;
	}

	public String getImageId()
	{
		return imageId;
	}

	public void setImageId(String imageId)
	{
		this.imageId = imageId;
	}

	public String getFl()
	{
		return fl;
	}

	public void setFl(String fl)
	{
		this.fl = fl;
	}

	public String getTemp()
	{
		return temp;
	}

	public void setTemp(String temp)
	{
		this.temp = temp;
	}
}
