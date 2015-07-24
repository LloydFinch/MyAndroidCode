package com.example.android_day15_baiduMap2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.*;

import java.util.List;

/**
 * Created by VennUser on 2015/7/15.
 */
public class RoteActivity extends Activity implements OnGetRoutePlanResultListener {

	private MapView mapView;
	private BaiduMap baiduMap;
	private RoutePlanSearch search;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);

		mapView = (MapView) this.findViewById(R.id.map_route);
		Button button = new Button(this);
		button.setText("搜索");

		button.setBackgroundResource(R.drawable.ic_launcher);
		mapView.addView(button);
		baiduMap = mapView.getMap();

		search = RoutePlanSearch.newInstance();
		search.setOnGetRoutePlanResultListener(this);

		//公交路线规划
		TransitRoutePlanOption option = new TransitRoutePlanOption();

		//城市
		option.city("北京");

		//起点
		PlanNode startPoint = PlanNode.withCityNameAndPlaceName("北京", "宝盛路");

		//终点
		PlanNode endPoint = PlanNode.withCityNameAndPlaceName("北京", "天安门");

		option.from(startPoint);
		option.to(endPoint);

		//搜索
		search.transitSearch(option);
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
		search.destroy();
		super.onDestroy();
	}

	//获取步行路线规划
	public void onGetWalkingRouteResult(WalkingRouteResult result) {

	}

	//获取公交路线规划
	public void onGetTransitRouteResult(TransitRouteResult result) {

		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			//起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			//result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			TransitRouteOverlay overlay = new MyTransitRouteOverlay(baiduMap);
			baiduMap.setOnMarkerClickListener(overlay);
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}
		Log.d("=======>", "onGetTransitRouteResult");
	}

	//获取自驾路线规划
	public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
		List<DrivingRouteLine> routeLines = drivingRouteResult.getRouteLines();
		for (DrivingRouteLine routeLine : routeLines) {
			String title = routeLine.getTitle();
			Log.d("=======>", title);
		}
	}

	//获取驾车路线
	public void btnRoute_onclick(View view) {
		PlanNode startNode = PlanNode.withCityNameAndPlaceName("北京", "宝盛里小区");
		PlanNode endNode = PlanNode.withCityNameAndPlaceName("北京", "五棵松");
		search.drivingSearch(new DrivingRoutePlanOption()
				.from(startNode).to(endNode));
		Log.d("=======>", "onGetDrivingRouteResult");
	}
}