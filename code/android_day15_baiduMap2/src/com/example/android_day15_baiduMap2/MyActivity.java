package com.example.android_day15_baiduMap2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.*;

import java.util.List;

public class MyActivity extends Activity implements BDLocationListener, BaiduMap.OnMarkerClickListener,
		OnGetPoiSearchResultListener {

	private BaiduMap baiduMap;
	private MapView mapView;
	private LocationClient location;
	private Marker marker;
	private LatLng newLocation;
	private PoiSearch search;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.main);

		search = PoiSearch.newInstance();
		search.setOnGetPoiSearchResultListener(this);

		mapView = (MapView) this.findViewById(R.id.map_baidu);
		baiduMap = mapView.getMap();

		baiduMap.setOnMarkerClickListener(this);

		//设置mapView的表现
		//设置是否显示放大缩小条
		//mapView.showZoomControls(false);

		//设置是否显示比例尺
		//mapView.showScaleControl(false);

		//设置可以获取位置信息
		baiduMap.setMyLocationEnabled(true);

		//创建LocationClient，准备定位
		location = new LocationClient(getApplicationContext());

		//定位参数的设置,通过LocationClientOption来设置
		LocationClientOption option = new LocationClientOption();

		//设置定位模式
		LocationClientOption.LocationMode locationMode = LocationClientOption.LocationMode.Hight_Accuracy;
		option.setLocationMode(locationMode);

		//打开GPS
		option.setOpenGps(true);

		//设置是否需要定位的城市地址信息
		option.setIsNeedAddress(true);

		//设置定位返回的经纬度信息,坐标系的规范
		//option.setCoorType("bd09ll");

		//设置定位功能获取定位地点的时间间隔,单位:毫秒
		//>1s 则有效,采用start()来启动
		//<1s 定位只执行一次，不需要使用start()来启动,通过requestLocation()来获取
		option.setScanSpan(500);

		location.setLocOption(option);

		//注册定位的监听器
		location.registerLocationListener(this);

		//启动定位
		location.start();
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

		location.unRegisterLocationListener(this);
		location.stop();

		baiduMap.setMyLocationEnabled(false);

		mapView.onDestroy();

		search.destroy();

		super.onDestroy();

	}

	//当定位数据收到时,回调此方法
	//定位失败也会回调此方法
	public void onReceiveLocation(BDLocation bdLocation) {
		//TODO 处理定位的位置信息

		//定位的处理步骤
		//1 检查定位的类型,确保是正确的类型
		//2 获取经纬度
		//3 获取城市,区县,街道等信息

		//获取定位类型
		int locType = bdLocation.getLocType();

		double latitude = bdLocation.getLatitude();
		double longitude = bdLocation.getLongitude();
		float radius = bdLocation.getRadius();


		String country = bdLocation.getCountry();
		String province = bdLocation.getProvince();
		String city = bdLocation.getCity();
		String district = bdLocation.getDistrict();
		String street = bdLocation.getStreet();

		//生成调整地图中心点位置的对象
		newLocation = new LatLng(latitude, longitude);

		//创建新的地图状态,后加Zoom可以设置缩放比例(3-20),20最大
		MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(newLocation, 20);

		//更新地图状态
		baiduMap.setMapStatus(status);

		//手动定位
		//lockLocation(newLocation);
		MarkerOptions markerOptions = new MarkerOptions();
		BitmapDescriptor icon = BitmapDescriptorFactory.fromAsset("Icon_end.png");
		markerOptions.icon(icon)
				.position(newLocation);
		marker = (Marker) baiduMap.addOverlay(markerOptions);

		Bundle bundle = new Bundle();
		bundle.putString("address", street == null ? "未知" : street);

		marker.setExtraInfo(bundle);


		switch (locType) {
			case 61:
			case 65:
			case 161:
				Log.d("========>", "SUCCESS:" + country + " " + province + " " + city + " " + district + " " + street);
				break;
			default:

				Log.d("========>", "ERROR,ERROR_CODE:" + locType);
				break;
		}

		//显示定位的信息
		//showLocation(bdLocation);
	}

	//设置定位信息显示在地图上
	private void showLocation(BDLocation bdLocation) {
		MyLocationData locationData = new MyLocationData.Builder()
				.accuracy(bdLocation.getRadius())
				.latitude(bdLocation.getLatitude())
				.longitude(bdLocation.getLongitude())
				.direction(1)
				.build();

		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.location);

		MyLocationConfiguration config = new MyLocationConfiguration(null, true, bitmap);

		baiduMap.setMyLocationData(locationData);
		baiduMap.setMyLocationConfigeration(config);
	}

	public void btnLocation_onclick(View view) {
		//lockLocation(newLocation);
		location.requestLocation();
	}

	private void lockLocation(LatLng newLocation) {

		//定位设置Marker在当前位置,使用百度SDK自带的assets资源
		MarkerOptions markerOptions = new MarkerOptions();
		BitmapDescriptor icon = BitmapDescriptorFactory.fromAsset("Icon_end.png");
		markerOptions.icon(icon)
				.position(newLocation);
		marker = (Marker) baiduMap.addOverlay(markerOptions);
	}

	public void btnWindow_onclick(View view) {
		Button button = new Button(getApplicationContext());
		button.setBackgroundResource(R.drawable.location);
	}

	public boolean onMarkerClick(Marker marker) {

		Bundle bundle = marker.getExtraInfo();

		String address = "未知";
		if (bundle != null) {
			address = (String) bundle.get("address");
		}

		TextView textView = new TextView(this);
		textView.setText(address);
		textView.setBackgroundColor(Color.RED);
		textView.setTextColor(Color.BLUE);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP);


		Button button = new Button(getApplicationContext());
		button.setBackgroundResource(R.drawable.location);

		InfoWindow infoWindow = new InfoWindow(textView, marker.getPosition(), -50);

		baiduMap.showInfoWindow(infoWindow);

		return false;
	}

	//实现检索功能
	public void btnPOI_onclick(View view) {

		//搜索城市信息,search系列函数的使用

		//设置搜索参数
		boolean result = search.searchInCity(new PoiCitySearchOption()
				.city("北京")
				.keyword("KTV")
				.pageCapacity(10)
				.pageNum(0));
		Log.d("==========>", "搜索结果:" + result);
	}

	//搜索结束会将搜索结果传给此函数并回调此函数
	public void onGetPoiResult(PoiResult poiResult) {

		//可以用此实现分页
		if (poiResult != null) {
			List<PoiInfo> allPoi = poiResult.getAllPoi();
			if (allPoi != null) {
				for (PoiInfo poiInfo : allPoi) {
					LatLng location = poiInfo.location;
					String name = poiInfo.name;
					String address = poiInfo.address;
					PoiInfo.POITYPE type = poiInfo.type;

					BitmapDescriptor bitmap = BitmapDescriptorFactory.fromAsset("Icon_mark1.png");
					MarkerOptions options = new MarkerOptions()
							.icon(bitmap)
							.position(location);

					Bundle bundle = new Bundle();
					bundle.putString("name", name);
					bundle.putString("type", type.toString());
					bundle.putString("address", address);

					options.extraInfo(bundle);
					baiduMap.addOverlay(options);
				}
			}
		}
	}

	public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

	}
}
