package com.example.Android_Review.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import com.example.Android_Review.R;
import com.example.Android_Review.service.AppService;

/**
 * Created by VennUser on 2015/7/23.
 */
public class ServiceActivity extends Activity implements ServiceConnection {


	private Messenger messenger, messenger2;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

		Intent intent = new Intent(this, AppService.class);
		intent.putExtra("key", "value");
		startService(intent);

		bindService(intent, this, BIND_AUTO_CREATE);
	}

	public void onServiceConnected(ComponentName name, IBinder service) {
		AppService.MyBinder myBinder = (AppService.MyBinder) service;

		messenger = new Messenger(service);

		Message message = new Message();

		messenger2 = new Messenger(handler);
		message.replyTo = messenger2;
		try {
			messenger.send(message);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void onServiceDisconnected(ComponentName name) {

	}
}