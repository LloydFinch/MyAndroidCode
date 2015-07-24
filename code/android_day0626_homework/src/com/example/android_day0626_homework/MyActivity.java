package com.example.android_day0626_homework;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.*;
import android.widget.RadioGroup;
import com.example.android_day0626_homework.fragment.MarkFragment;
import com.example.android_day0626_homework.fragment.NewsFragment;
import com.example.android_day0626_homework.fragment.WeatherFragment;

public class MyActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener
{
	/**
	 * Called when the activity is first created.
	 */
	private RadioGroup radioGroup;
	private FragmentManager manager;
	private NewsFragment newsFragment;
	private WeatherFragment weatherFragment;
	private MarkFragment markFragment;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		NewsFragment.getContext(this);
		WeatherFragment.getContext(this);
		MarkFragment.getContext(this);

		radioGroup = (RadioGroup) this.findViewById(R.id.tab_bar);
		radioGroup.setOnCheckedChangeListener(this);
		manager = getSupportFragmentManager();

		new CallNews()
		{
			public void jumpToNews()
			{
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.add(R.id.content, newsFragment = new NewsFragment());
				transaction.commit();
			}
		}.jumpToNews();
	}

	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		final FragmentTransaction transaction = manager.beginTransaction();
		switch (checkedId)
		{
			case R.id.tab_news_bar:
				new CallNews()
				{
					public void jumpToNews()
					{
						newsFragment = newsFragment == null ? new NewsFragment() : newsFragment;
						transaction.replace(R.id.content, newsFragment);
					}
				}.jumpToNews();
				break;
			case R.id.tab_weather_bar:
				new CallWeather()
				{
					public void jumpToWeather()
					{
						weatherFragment = weatherFragment == null ? new WeatherFragment() : weatherFragment;
						transaction.replace(R.id.content, weatherFragment);
					}
				}.jumpToWeather();
				break;
			case R.id.tab_mark_bar:
				new CallMark()
				{
					public void jumpToMark()
					{
						markFragment = markFragment == null ? new MarkFragment() : markFragment;
						transaction.replace(R.id.content, markFragment);
					}
				}.jumpToMark();
				break;
		}
		transaction.commit();
	}

	interface CallNews
	{
		void jumpToNews();
	}

	interface CallWeather
	{
		void jumpToWeather();
	}

	interface CallMark
	{
		void jumpToMark();
	}
}