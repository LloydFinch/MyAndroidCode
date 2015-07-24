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

		//����mapView�ı���
		//�����Ƿ���ʾ�Ŵ���С��
		//mapView.showZoomControls(false);

		//�����Ƿ���ʾ������
		//mapView.showScaleControl(false);

		//���ÿ��Ի�ȡλ����Ϣ
		baiduMap.setMyLocationEnabled(true);

		//����LocationClient��׼����λ
		location = new LocationClient(getApplicationContext());

		//��λ����������,ͨ��LocationClientOption������
		LocationClientOption option = new LocationClientOption();

		//���ö�λģʽ
		LocationClientOption.LocationMode locationMode = LocationClientOption.LocationMode.Hight_Accuracy;
		option.setLocationMode(locationMode);

		//��GPS
		option.setOpenGps(true);

		//�����Ƿ���Ҫ��λ�ĳ��е�ַ��Ϣ
		option.setIsNeedAddress(true);

		//���ö�λ���صľ�γ����Ϣ,����ϵ�Ĺ淶
		//option.setCoorType("bd09ll");

		//���ö�λ���ܻ�ȡ��λ�ص��ʱ����,��λ:����
		//>1s ����Ч,����start()������
		//<1s ��λִֻ��һ�Σ�����Ҫʹ��start()������,ͨ��requestLocation()����ȡ
		option.setScanSpan(500);

		location.setLocOption(option);

		//ע�ᶨλ�ļ�����
		location.registerLocationListener(this);

		//������λ
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

	//����λ�����յ�ʱ,�ص��˷���
	//��λʧ��Ҳ��ص��˷���
	public void onReceiveLocation(BDLocation bdLocation) {
		//TODO ����λ��λ����Ϣ

		//��λ�Ĵ�����
		//1 ��鶨λ������,ȷ������ȷ������
		//2 ��ȡ��γ��
		//3 ��ȡ����,����,�ֵ�����Ϣ

		//��ȡ��λ����
		int locType = bdLocation.getLocType();

		double latitude = bdLocation.getLatitude();
		double longitude = bdLocation.getLongitude();
		float radius = bdLocation.getRadius();


		String country = bdLocation.getCountry();
		String province = bdLocation.getProvince();
		String city = bdLocation.getCity();
		String district = bdLocation.getDistrict();
		String street = bdLocation.getStreet();

		//���ɵ�����ͼ���ĵ�λ�õĶ���
		newLocation = new LatLng(latitude, longitude);

		//�����µĵ�ͼ״̬,���Zoom�����������ű���(3-20),20���
		MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(newLocation, 20);

		//���µ�ͼ״̬
		baiduMap.setMapStatus(status);

		//�ֶ���λ
		//lockLocation(newLocation);
		MarkerOptions markerOptions = new MarkerOptions();
		BitmapDescriptor icon = BitmapDescriptorFactory.fromAsset("Icon_end.png");
		markerOptions.icon(icon)
				.position(newLocation);
		marker = (Marker) baiduMap.addOverlay(markerOptions);

		Bundle bundle = new Bundle();
		bundle.putString("address", street == null ? "δ֪" : street);

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

		//��ʾ��λ����Ϣ
		//showLocation(bdLocation);
	}

	//���ö�λ��Ϣ��ʾ�ڵ�ͼ��
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

		//��λ����Marker�ڵ�ǰλ��,ʹ�ðٶ�SDK�Դ���assets��Դ
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

		String address = "δ֪";
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

	//ʵ�ּ�������
	public void btnPOI_onclick(View view) {

		//����������Ϣ,searchϵ�к�����ʹ��

		//������������
		boolean result = search.searchInCity(new PoiCitySearchOption()
				.city("����")
				.keyword("KTV")
				.pageCapacity(10)
				.pageNum(0));
		Log.d("==========>", "�������:" + result);
	}

	//���������Ὣ������������˺������ص��˺���
	public void onGetPoiResult(PoiResult poiResult) {

		//�����ô�ʵ�ַ�ҳ
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
