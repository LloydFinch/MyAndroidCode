package com.venn.myapplication5.app;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class FramDrawableActivity extends ActionBarActivity {

	private ImageView imageView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fram_drawable);

		imageView = (ImageView) this.findViewById(R.id.image_elector);
		if (imageView != null) {

			//逐帧动画的获取,获取的是src属性
			Drawable drawable = imageView.getDrawable();

			//animation-list对应AnimationDrawable
			if (drawable instanceof AnimationDrawable) {

				AnimationDrawable animationDrawable = (AnimationDrawable) drawable;

				//开始运行
				animationDrawable.start();
			}
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_fram_drawable, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void imageSelect_onclick(View view) {

	}
}
