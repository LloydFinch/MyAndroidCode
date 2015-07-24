package com.example.android_day01715_homework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.android_day01715_homework.R;
import com.example.android_day01715_homework.entity.City;
import com.example.android_day01715_homework.entity.Province;
import com.example.android_day01715_homework.tool.PointXmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by VennUser on 2015/7/16.
 */
public class CityListActivity extends Activity {

	private TextView textView;

	private Spinner spinnerProvince;
	private Spinner spinnerCity;

	private List<Province> provinceList;
	private List<City> cityList;
	private Province province;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_citylist);

		textView = (TextView) this.findViewById(R.id.text_locationInfo);
		spinnerProvince = (Spinner) this.findViewById(R.id.spinner_province);
		spinnerCity = (Spinner) this.findViewById(R.id.spinner_city);

		try {
			provinceList = PointXmlPullParser.getProvinceList(getResources().openRawResource(R.raw.citys_weather));
		}
		catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		ArrayAdapter<Province> adapter = new ArrayAdapter<Province>(this, android.R.layout.simple_list_item_1,
				provinceList);
		spinnerProvince.setAdapter(adapter);

		//选择省显示相应的城市列表
		spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				province = provinceList.get(position);
				cityList = province.getCities();
				ArrayAdapter<City> adapter = new ArrayAdapter<City>(CityListActivity.this, android.R.layout
						.simple_list_item_1, cityList);
				spinnerCity.setAdapter(adapter);
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				City city = cityList.get(position);
				String cityName = city.getName();
				textView.setText(cityName);
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	public void btnOK_onclick(View view) {
		Intent intent = new Intent(this, MapActivity.class);
		intent.putExtra("city", textView.getText().toString().trim());
		startActivity(intent);
	}
}