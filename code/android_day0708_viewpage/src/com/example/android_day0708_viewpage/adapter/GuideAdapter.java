package com.example.android_day0708_viewpage.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.android_day0708_viewpage.R;

import java.util.List;

/**
 * project:com.example.android_day0708_viewpage.adapter
 * user:VennUser
 * date:2015/7/8
 */

//PagerAdapter用于程序第一次启动的教程展示
public class GuideAdapter extends PagerAdapter {
	private Context context;
	private List<Integer> images;
	private View.OnClickListener onClickListener;

	public GuideAdapter(Context context, List<Integer> images) {
		this.context = context;
		this.images = images;
	}

	public void setOnClickListener(View.OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	//返回显示的页数
	public int getCount() {
		int count = images.size();
		if (count != 0) {
			return count;
		}
		Log.d("---->","getCount:-->"+count);
		return 0;
	}

	//用于检查参数view是否是从o转换过来的对象
	public boolean isViewFromObject(View view, Object o) {

		return view == o;
	}

	//PagerAdapter必须要重写的两个方法,并且不能使用super()方法
	//1 创建视图的方法 2 销毁视图的方法

	//创建视图的方法,返回的通常是创建的View
	public Object instantiateItem(ViewGroup container, int position) {
		View view = null;

		//view的加载：两种方式:
		//1 代码创建,必须使用带一个上下文参数的构造方法
		//2 LayoutInflater加载

		//实现最后一页包含按钮
		if (position == images.size() - 1) {

			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.page_guide_last, container, false);
			ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
			imageView.setImageResource(images.get(position));
			Button button = (Button) view.findViewById(R.id.btn);
			button.setText("开始体验");
			button.setOnClickListener(onClickListener);

		} else {
			ImageView imageView = new ImageView(context);
			int imageID = images.get(position);

			imageView.setImageResource(imageID);
			view = imageView;
		}
		//imageView必须手动添加到container中,否则不能显示
		container.addView(view);
		Log.d("---->", "instantiateItem:-->");
		return view;
	}

	//销毁视图的方法,销毁指定位置的object或者View
	//param :object就是instantiateItem()的返回值
	public void destroyItem(ViewGroup container, int position, Object object) {

		//移除View，相当于从PagerView内部移除UI
		container.removeView((View) object);
		Log.d("---->", "destroyItem:-->");
	}
}
