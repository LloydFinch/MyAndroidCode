package com.example.android_day01715_homework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.*;
import com.example.android_day01715_homework.R;
import com.example.android_day01715_homework.entity.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VennUser on 2015/7/16.
 */
public class MapActivity extends Activity implements BDLocationListener, OnGetPoiSearchResultListener {

	private TextView textView;
	private MapView mapView;
	private BaiduMap baiduMap;
	private InfoWindow infoWindow;
	private PoiSearch search;
	private LatLng newLocation;
	private String cityName;
	private List<Marker> markerList;

	private LocationClient locationClient;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		markerList = new ArrayList<>();

		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_map);

		textView = (TextView) this.findViewById(R.id.text_city);
		Intent intent = getIntent();
		String city = intent.getStringExtra("city");
		textView.setText(city == null ? "正在定位..." : city);

		mapView = (MapView) this.findViewById(R.id.map_view);
		baiduMap = mapView.getMap();

		baiduMap.setMyLocationEnabled(true);

		search = PoiSearch.newInstance();
		search.setOnGetPoiSearchResultListener(this);

		//定位信息的初始化设置
		locationClient = new LocationClient(getApplicationContext());

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
		option.setIsNeedAddress(true);
		option.setScanSpan(5000);


		locationClient.setLocOption(option);
		locationClient.registerLocationListener(this);

		//开始定位
		locationClient.start();
	}

	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	protected void onDestroy() {
		mapView.onDestroy();

		locationClient.unRegisterLocationListener(this);
		locationClient.stop();

		search.destroy();

		super.onDestroy();
	}

	//点击搜索
	public void btnSearch_onclick(View view) {

		baiduMap.showInfoWindow(onCreateInfoWindow());
	}


	String flag = "city";

	//创建悬浮框
	private InfoWindow onCreateInfoWindow() {

		if (infoWindow == null) {

			View view = View.inflate(getApplicationContext(), R.layout.window_info, null);

			EditText editText = (EditText) view.findViewById(R.id.txt_information);

			RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.tab_condition);
			radioGroup.check(R.id.tab_city);
			radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					switch (checkedId) {
						case R.id.tab_city:
							flag = "city";
							break;
						case R.id.tab_bound:
							flag = "bound";
							break;
					}
				}
			});

			Button buttonSearch = (Button) view.findViewById(R.id.btn_search);
			buttonSearch.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					//清除上次搜索标记
					onClearMarker();

					String keyWords = editText.getText().toString().trim();
					Log.d("======>", "keyWords: " + keyWords);
					baiduMap.hideInfoWindow();
					if (flag.equals("city")) {
						onSearchByCity(cityName, keyWords);
					} else if (flag.equals("bound")) {
						onSearchByBound(keyWords);
					}
				}
			});

			infoWindow = new InfoWindow(view, newLocation, -50);
		}
		return infoWindow;
	}

	//创建Marker
	private void onCreateMarker(LatLng point) {

		//创建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.loca_ll);
		BitmapDescriptor bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.state_1);

		ArrayList<BitmapDescriptor> bitmaps = new ArrayList<BitmapDescriptor>();
		bitmaps.add(bitmap);
		bitmaps.add(bitmap2);

		//创建OverlayOptions对象
		OverlayOptions options = new MarkerOptions()
				.position(point) //设置位置
				.icons(bitmaps)
				.period(10) //动画帧
				.zIndex(9)//设置图标
				.draggable(true); //设置可拖拽，此时位置已经发生变化，

		//将Marker添加到地图
		baiduMap.addOverlay(options);
	}

	//对定位的处理
	public void onReceiveLocation(BDLocation bdLocation) {

		newLocation = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());

		cityName = bdLocation.getCity();
		textView.setText(cityName == null ? "正在定位中..." : cityName);
		onCreateMarker(newLocation);
	}

	//按城市搜索
	private void onSearchByCity(String cityName, String keyWords) {

		PoiCitySearchOption citySearchOption = new PoiCitySearchOption();
		citySearchOption.city(cityName);
		citySearchOption.keyword(keyWords);
		citySearchOption.pageNum(0);//显示的页数
		citySearchOption.pageCapacity(10);//每页显示数量
		boolean searchResult = search.searchInCity(citySearchOption);
	}

	//按周边搜索
	private void onSearchByBound(String keyWords) {
		PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption();
		nearbySearchOption.keyword(keyWords);
		nearbySearchOption.radius(10000);//搜索半径(m)
		nearbySearchOption.location(newLocation);
		nearbySearchOption.pageNum(0);
		nearbySearchOption.pageCapacity(10);
		boolean searchResult = search.searchNearby(nearbySearchOption);
	}

	//对搜索结果的处理
	public void onGetPoiResult(PoiResult poiResult) {

		List<PoiInfo> allPoi = poiResult.getAllPoi();
		if (allPoi != null) {
			for (PoiInfo poiInfo : allPoi) {
				LatLng location = poiInfo.location;
				MarkerOptions markerOptions = new MarkerOptions();
				markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.location));
				markerOptions.position(location);
				Marker marker = (Marker) baiduMap.addOverlay(markerOptions);
				markerList.add(marker);
			}
		}
	}

	public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

		Toast.makeText(this, "不支持详细搜索", Toast.LENGTH_LONG).show();
	}

	//清除地图上的搜索标记
	private void onClearMarker() {
		for (Marker marker : markerList) {
			marker.remove();
		}
	}
}