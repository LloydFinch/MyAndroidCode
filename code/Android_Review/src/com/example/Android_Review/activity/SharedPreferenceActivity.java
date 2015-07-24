package com.example.Android_Review.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.Android_Review.R;

/**
 * Created by VennUser on 2015/7/23.
 */
public class SharedPreferenceActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharedpreference);

		SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt("cao", 3);
		editor.commit();
	}

	public void btnSharedPreference_onclick(View view) {
		SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
		int cao = sharedPreferences.getInt("cao", 0);
		Log.d("------------>", "cao: " + cao);
	}
}