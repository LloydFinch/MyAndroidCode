package com.example.Android_Review.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.example.Android_Review.R;

/**
 * Created by VennUser on 2015/7/23.
 */
public class ToastActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast);
	}

	public void btnToastOnclick(View view) {

		Toast toast = new Toast(this);

		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(LayoutInflater.from(this).inflate(R.layout.view_toast, null));
		toast.show();
	}
}