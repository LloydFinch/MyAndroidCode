package com.example.android_day0626_homework.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.android_day0626_homework.entity.City;
import com.example.android_day0626_homework.entity.District;
import com.example.android_day0626_homework.entity.Province;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class WeatherXmlPullParser
{

	public static List<Province> getParserList(InputStream inputStream)
			throws XmlPullParserException, IOException
	{
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		org.xmlpull.v1.XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");

		List<Province> provinces = null;
		List<City> cities = null;
		List<District> districts = null;
		Province province = null;
		City city = null;
		District district = null;

		int event = parser.getEventType();
		while (event != org.xmlpull.v1.XmlPullParser.END_DOCUMENT)
		{
			switch (event)
			{
				case org.xmlpull.v1.XmlPullParser.START_DOCUMENT:
					provinces = new ArrayList<Province>();
					break;
				case org.xmlpull.v1.XmlPullParser.START_TAG:
					String tagName = parser.getName();

					if ("p".equals(tagName))
					{
						province = new Province();
						cities = new ArrayList<City>();
						int count = parser.getAttributeCount();
						for (int i = 0; i < count; i++)
						{
							String attrName = parser.getAttributeName(i);
							String attrValue = parser.getAttributeValue(i);
							if ("p_id".equals(attrName))
							{
								province.setId(attrValue);
							}
						}
					} else if ("pn".equals(tagName))
					{
						province.setName(parser.nextText());
					} else if ("c".equals(tagName))
					{
						city = new City();
						districts = new ArrayList<District>();
						int count = parser.getAttributeCount();
						for (int i = 0; i < count; i++)
						{
							String attrName = parser.getAttributeName(i);
							String attrValue = parser.getAttributeValue(i);
							if ("c_id".equals(attrName))
							{
								city.setId(attrValue);
							}
						}
					} else if ("cn".equals(tagName))
					{
						city.setName(parser.nextText());
					} else if ("d".equals(tagName))
					{
						district = new District();
						int count = parser.getAttributeCount();
						for (int i = 0; i < count; i++)
						{
							String attrName = parser.getAttributeName(i);
							String attrValue = parser.getAttributeValue(i);
							if ("d_id".equals(attrName))
							{
								district.setId(attrValue);
								district.setName(parser.nextText());
							}
						}
						districts.add(district);
					}
					break;

				case org.xmlpull.v1.XmlPullParser.END_TAG:
					String tagEndName = parser.getName();
					if ("c".equals(tagEndName))
					{
						city.setDistricts(districts);
						cities.add(city);
					} else if ("p".equals(tagEndName))
					{
						province.setCities(cities);
						provinces.add(province);
					}
					break;
			}
			event = parser.next();
		}
		return provinces;
	}
}
