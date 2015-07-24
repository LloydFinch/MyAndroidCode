package com.example.android_day0711_homework.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import com.example.android_day0711_homework.R;
import com.example.android_day0711_homework.service.DownLoadService;
import com.example.android_day0711_homework.tool.DownLoad;
import com.example.android_day0711_homework.tool.PutFile;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MyActivity extends Activity implements ServiceConnection {

	private EditText editText;
	private DownLoadService.DownLoadBinder binder;
	private String path;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editText = (EditText) this.findViewById(R.id.txt_uri);
	}

	public void btnDownLoad_onclick(View view) {

		path = editText.getText().toString().trim();

		Intent intent = new Intent(this, DownLoadService.class);
		startService(intent);
		bindService(intent, this, BIND_AUTO_CREATE);

		Intent nextIntent = new Intent(this, ProgressActivity.class);
		startActivity(nextIntent);
	}

	public void onServiceConnected(ComponentName name, IBinder service) {
		binder = (DownLoadService.DownLoadBinder) service;
		//path = path == null ? "http://e.hiphotos.baidu.com/image/pic/item/e824b899a9014c0882033236097b02087bf4f46d.jpg" : path;
		path = "http://a.hiphotos.baidu.com/image/pic/item/5243fbf2b21193138038f82366380cd791238d2d.jpg";
		binder.init(path);
		binder.download();
	}

	public void onServiceDisconnected(ComponentName name) {

	}
}
