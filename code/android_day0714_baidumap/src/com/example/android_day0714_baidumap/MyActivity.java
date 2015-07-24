package com.example.android_day0714_baidumap;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;

public class MyActivity extends Activity implements CompoundButton.OnCheckedChangeListener, BaiduMap
		.OnMarkerDragListener, BaiduMap.OnMapClickListener, BaiduMap.OnMarkerClickListener {
	private MapView mapView;
	private BaiduMap baiduMap;
	private Switch aSwitch, bSwitch;
	private Circle circle;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//设置全屏显示
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		SDKInitializer.initialize(getApplicationContext());


		setContentView(R.layout.main);

		mapView = (MapView) this.findViewById(R.id.map_view);
		aSwitch = (Switch) this.findViewById(R.id.switch_btn);
		bSwitch = (Switch) this.findViewById(R.id.switch_heat);
		aSwitch.setOnCheckedChangeListener(this);
		bSwitch.setOnCheckedChangeListener(this);

		//获得内部管理器
		baiduMap = mapView.getMap();

		//设置地图类型
		//baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		baiduMap.setOnMapClickListener(this);
		baiduMap.setOnMarkerClickListener(this);

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
		super.onDestroy();
		mapView.onDestroy();
	}


	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		int id = buttonView.getId();
		switch (id) {
			case R.id.switch_btn:

				//开启交通图
				baiduMap.setTrafficEnabled(true);

				break;

			case R.id.switch_heat:

				//开启热力图
				baiduMap.setBaiduHeatMapEnabled(true);

				break;
		}
	}

	//在地图上添加标记
	public void btn_addMarker(View view) {

		//创建Marker的纬经度坐标
		LatLng point = new LatLng(39.963175, 116.400244);

		//创建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.loca_ll);
		BitmapDescriptor bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher);


		//创建图片组

		ArrayList<BitmapDescriptor> bitmaps = new ArrayList<BitmapDescriptor>();
		bitmaps.add(bitmap);
		bitmaps.add(bitmap2);

		//创建OverlayOptions对象
		OverlayOptions options = new MarkerOptions()
				.position(point) //设置位置
				.icons(bitmaps)
				.period(10)
				.zIndex(9)//设置图标
				.draggable(true); //设置可拖拽，此时位置已经发生变化，
		// 主要用于目标位置的获取,来设置路径的规划

		//options.extraInfo();

		//将Marker添加到地图
		baiduMap.addOverlay(options);

		//设置拖拽事件，给所有可拖拽的Marker设置事件，不是针对一个
		baiduMap.setOnMarkerDragListener(this);
	}


	public void btn_addWindow(View view) {
		Button button = new Button(getApplicationContext());
		button.setBackgroundResource(R.drawable.maliao);
		LatLng point = new LatLng(39.963175, 100.400244);
		InfoWindow infoWindow = new InfoWindow(button, point, -50);
		baiduMap.showInfoWindow(infoWindow);
	}

	//开始拖拽
	public void onMarkerDragStart(Marker marker) {

	}

	//拖拽中
	public void onMarkerDrag(Marker marker) {

		circle.setCenter(marker.getPosition());
	}

	//拖拽结束
	public void onMarkerDragEnd(Marker marker) {

		//获得Marker的位置
		LatLng position = marker.getPosition();

		Log.d("------------->", "位置信息:" + position);
	}

	public void onMapClick(LatLng latLng) {
		openLocation();
	}

	//返回值：true表示已经内部处理了
	public boolean onMapPoiClick(MapPoi mapPoi) {

		//TODO 显示点击的详情
		return false;
	}

	//添加圆形覆盖物,不支持拖拽
	public void btn_showCircle(View view) {

		LatLng centre = new LatLng(39.963175, 116.400244);

		//设置边框为5个像素，颜色为红色
		Stroke stroke = new Stroke(5, Color.BLUE);

		//圆半径的单位:m(地图上的)
		CircleOptions options = new CircleOptions()
				.center(centre)
				.radius(500)
				.fillColor(Color.argb(0x99, 0xff, 0, 0))
				.stroke(stroke);

		//添加覆盖物
		circle = (Circle) baiduMap.addOverlay(options);
	}

	//添加多边形
	public void btn_showPolygon(View view) {
		ArrayList<LatLng> points = new ArrayList<LatLng>();

		LatLng point1 = new LatLng(39.963175, 100.400244);
		LatLng point2 = new LatLng(37.963175, 97.400244);
		LatLng point3 = new LatLng(43.963175, 104.400244);
		LatLng point4 = new LatLng(36.963175, 106.400244);
		LatLng point5 = new LatLng(39.963175, 100.400244);

		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		points.add(point5);

		PolygonOptions options = new PolygonOptions()
				.points(points)
				.stroke(new Stroke(5, Color.BLUE))
				.fillColor(Color.RED);

		baiduMap.addOverlay(options);
	}

	//添加线段
	public void btn_showSegment(View view) {

		ArrayList<LatLng> points = new ArrayList<LatLng>();

		LatLng point1 = new LatLng(39.963175, 100.400244);
		LatLng point2 = new LatLng(37.963175, 97.400244);
		LatLng point3 = new LatLng(43.963175, 104.400244);
		LatLng point4 = new LatLng(36.963175, 106.400244);

		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);

		PolylineOptions options = new PolylineOptions()
				.width(5)
				.color(Color.RED)
				.points(points);

		baiduMap.addOverlay(options);
	}

	public void btn_showText(View view) {
		TextOptions options = new TextOptions()
				.fontSize(25)
				.fontColor(Color.RED)
				.bgColor(Color.GREEN)
				.text("北京欢迎你")
				.position(new LatLng(36.963175, 106.400244))
				.rotate(-30);

		baiduMap.addOverlay(options);
	}

	public boolean onMarkerClick(Marker marker) {

		marker.remove();
		circle.remove();
		return false;
	}

	//设置定位功能
	private void openLocation() {
		baiduMap.setMyLocationEnabled(true);

		LatLng location = new LatLng(39.963175, 116.400244);
		LocationClient location2 = new LocationClient(getApplicationContext());

		MyLocationData myLocationData = new MyLocationData.Builder()
				.accuracy(20)
				.latitude(location.latitude)
				.longitude(location.longitude)
				.direction(50)
				.build();

		baiduMap.setMyLocationData(myLocationData);

		BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.location);

		MyLocationConfiguration configuration = new MyLocationConfiguration(null, true, descriptor);

		baiduMap.setMyLocationConfigeration(configuration);
	}
}
