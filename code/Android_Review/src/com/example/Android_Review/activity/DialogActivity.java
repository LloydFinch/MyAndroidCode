package com.example.Android_Review.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.Android_Review.R;

/**
 * Created by VennUser on 2015/7/23.
 */
public class DialogActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
	}

	public void btnAlertDialog(View view) {
		ProgressDialog dialog = new ProgressDialog(this);

		dialog.setTitle("Progress Dialog");
		dialog.setMax(100);
		dialog.setProgress(50);

		dialog.show();
	}

	public void btnProgressDialog(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("AlertDialog");

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.d("--------->", "OK");
			}
		});

		builder.show();
	}
}