package com.venn.viewselfapp.app.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.venn.viewselfapp.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VennUser on 2015/7/20.
 */

//自定义的组合View
public class FallStreamView extends ScrollView {

	private List<LinearLayout> linearLayoutList;
	private int count;
	private int columnNum = 3;
	private LinearLayout waterPool;
	private OnLoadMoreListener onLoadMoreListener;

	public FallStreamView(Context context) {
		super(context);
		init(context, null);
	}

	public FallStreamView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, null);
	}

	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		this.onLoadMoreListener = onLoadMoreListener;
	}

	//初始化方法
	private void init(Context context, AttributeSet attrs) {

		linearLayoutList = new ArrayList<LinearLayout>();

		//TODO 瀑布流外层布局的创建
		//1 ScrollView内部第一个布局
		waterPool = new LinearLayout(context);

		//设置方向
		waterPool.setOrientation(LinearLayout.HORIZONTAL);

		//设置哪个容器就用那个容器的参数
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		//设置宽高,使用setLayoutParams设置
		waterPool.setLayoutParams(layoutParams);

		//制定控件的背景色
		//waterPool.setBackgroundColor(Color.GREEN);

		//将定义的LinearLayout添加到自身
		addView(waterPool);

		//自定义列数:columnNum
		for (int i = 0; i < columnNum; i++) {
			LinearLayout linearLayout0 = new LinearLayout(context);
			linearLayout0.setOrientation(LinearLayout.VERTICAL);
			LinearLayout.LayoutParams layoutParams0 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams
					.WRAP_CONTENT, 1);
			linearLayout0.setLayoutParams(layoutParams0);
			waterPool.addView(linearLayout0);
			linearLayoutList.add(linearLayout0);
		}

//		//TODO 第二层布局的创建
//		//列0
//		LinearLayout linearLayout0 = new LinearLayout(context);
//		linearLayout0.setOrientation(LinearLayout.VERTICAL);
//		LinearLayout.LayoutParams layoutParams0 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams
//				.WRAP_CONTENT, 1);
//		linearLayout0.setLayoutParams(layoutParams0);
//		linearLayout0.setBackgroundColor(Color.RED);
//		waterPool.addView(linearLayout0);
//
//		//列1
//		LinearLayout linearLayout1 = new LinearLayout(context);
//		linearLayout1.setOrientation(LinearLayout.VERTICAL);
//		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams
//				.WRAP_CONTENT, 1);
//		linearLayout1.setLayoutParams(layoutParams1);
//		linearLayout1.setBackgroundColor(Color.GREEN);
//		waterPool.addView(linearLayout1);
//
//		//列2
//		LinearLayout linearLayout2 = new LinearLayout(context);
//		linearLayout2.setOrientation(LinearLayout.VERTICAL);
//		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams
//				.WRAP_CONTENT, 1);
//		linearLayout2.setLayoutParams(layoutParams2);
//		linearLayout2.setBackgroundColor(Color.BLUE);
//		waterPool.addView(linearLayout2);


//		//TODO 最内层布局的创建
//		ImageView imageView0 = new ImageView(context);
//		imageView0.setImageResource(R.mipmap.ic_launcher);
//		linearLayout0.addView(imageView0);
//
//		ImageView imageView1 = new ImageView(context);
//		imageView1.setImageResource(R.mipmap.ic_launcher);
//		linearLayout1.addView(imageView1);
//
//		ImageView imageView2 = new ImageView(context);
//		imageView2.setImageResource(R.mipmap.ic_launcher);
//		linearLayout2.addView(imageView2);
	}


	//添加子块
	public void addSubView(View view) {

		if (view != null) {
			if (!linearLayoutList.isEmpty()) {

				//利用求余添加到适合的列
				int index = count % linearLayoutList.size();
				linearLayoutList.get(index).addView(view);
				count++;
			}
		}
	}


	//屏幕触摸事件的重写,用来检查是否滑到底部
	public boolean onTouchEvent(MotionEvent ev) {

		//MotionEvent包含触摸事件的各种信息,包含按,抬起或者拖拽
		//以及屏幕的位置

		//获取事件的动作类型
		int action = ev.getAction();
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				Log.d("=============>", "按下");
				break;

			case MotionEvent.ACTION_MOVE:
				Log.d("=============>", "移动");
				break;

			case MotionEvent.ACTION_UP:
				Log.d("=============>", "抬起");

				//检查当前移动出去的高度与自身高度的总和是否就是内容的高度

				//Scroll的高度
				int ScrollHeight = getHeight();

				//waterPool的高度
				int waterPoolHeight = waterPool.getHeight();

				//获取滚出屏幕的高度
				int scrollY = getScrollY();

				//判断是否滑到地步了
				if (ScrollHeight + scrollY >= waterPoolHeight) {
					Log.d("=======>", "滑到底部了");

					//TODO 加载更多,主线程调用
					if (onLoadMoreListener != null) {
						onLoadMoreListener.onBottom(this);
					}
				}
				break;
		}
		return super.onTouchEvent(ev);
	}

	//滑到底部时调用,加载更多内容
	public interface OnLoadMoreListener {

		void onBottom(FallStreamView view);
	}
}
