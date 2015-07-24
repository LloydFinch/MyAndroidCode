package com.example.android_day0708_viewpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.example.android_day0708_viewpage.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity implements View.OnClickListener {
	private ViewPager viewPager;
	private List<Integer> images;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		viewPager = (ViewPager) this.findViewById(R.id.pager_content);
		images = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			images.add(R.drawable.dragonball);
		}
		GuideAdapter adapter = new GuideAdapter(this, images);
		adapter.setOnClickListener(this);
		viewPager.setAdapter(adapter);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.btn) {
			Intent intent = new Intent(this, EnterActivity.class);
			startActivity(intent);
			finish();
		}
	}
}
