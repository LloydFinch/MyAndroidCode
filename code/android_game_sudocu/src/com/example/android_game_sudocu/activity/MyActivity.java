package com.example.android_game_sudocu.activity;

import android.app.Activity;
import android.os.Bundle;
import com.example.android_game_sudocu.R;
import com.example.android_game_sudocu.view.MyView;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);

		setContentView(new MyView(this));
	}
}
